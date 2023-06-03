package com.taufiq.e_bayar.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;
import com.taufiq.e_bayar.Adapter.Adapter;
import com.taufiq.e_bayar.AllMethod.DetailSPPMethod;
import com.taufiq.e_bayar.BuildConfig;
import com.taufiq.e_bayar.Model.ModelTahun.DataTahun;
import com.taufiq.e_bayar.Model.ModelTahun.ModelTahun;
import com.taufiq.e_bayar.R;
import com.taufiq.e_bayar.Request.Services;
import com.taufiq.e_bayar.Utils.ApiMethod;
import com.taufiq.e_bayar.Model.spp.DataItem;
import com.taufiq.e_bayar.Model.spp.Tagihan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSPPActivity extends AppCompatActivity implements TransactionFinishedCallback {
    Toolbar toolbar;
    Button btn;
    RecyclerView recyclerView;
    Adapter myAdapter;
    ArrayList<ItemDetails> itemDetails;
    ArrayList<DataItem> list;
    ApiMethod tagihanApiMethod;
    TextView totalamount, detailName;
    int jumlahtotal = 0;
    static SharedPreferences sharedPreferences;
    DetailSPPMethod detailSPPMethod;
    AutoCompleteTextView completeTextView;
    ArrayAdapter<String> adapter;
    ArrayList<String> dataDropdown;
    String selectedTahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sppactivity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar = findViewById(R.id.toolbardetailspp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailName = findViewById(R.id.namaDetailSpp);
        btn = findViewById(R.id.bayarSpp);
        recyclerView = findViewById(R.id.recyclerSPP);
        totalamount = findViewById(R.id.totalSPP);
        completeTextView = findViewById(R.id.auto_complite_text);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        sharedPreferences = getSharedPreferences("Ebayar", MODE_PRIVATE);
        String nama = sharedPreferences.getString("nama", "");
        detailName.setText(nama);
        detailSPPMethod = new DetailSPPMethod();
        getTahunSpp();

        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTahun = (String) parent.getItemAtPosition(position);
                int tahun = Integer.parseInt(selectedTahun);
                getAllTagihan(tahun);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDetails = new ArrayList<>();
                for (DataItem mt : myAdapter.checkedmodelTagihan) {
                    itemDetails.add(new ItemDetails(String.valueOf(mt.getId()), (double) mt.getJumlahBayar(),
                            mt.getQuantity(), String.valueOf(mt.getBulan())));
                    jumlahtotal += mt.getJumlahBayar();
                }
                detailSPPMethod.clickPay(itemDetails, jumlahtotal, getApplicationContext());
                jumlahtotal = 0;
            }
        });

        makePayment();
    }

    private void getAllTagihan(int tahun) {
        tagihanApiMethod = Services.getRetrofit().create(ApiMethod.class);
        Call<Tagihan> call = tagihanApiMethod.getTagihanSPP(tahun);
        call.enqueue(new Callback<Tagihan>() {
            @Override
            public void onResponse(Call<Tagihan> call, Response<Tagihan> response) {
                try {
                    if (response.isSuccessful()) {
                        Tagihan tagihanResponse = response.body();
                        String message = tagihanResponse.getMessage();
                        int code = tagihanResponse.getCode();
                        ArrayList<DataItem> siswaList = tagihanResponse.getData();
                        list = new ArrayList<>();
                        for (DataItem siswa : siswaList) {
                            list.add(siswa);
                        }
                        myAdapter = new Adapter(getApplicationContext(), list, totalamount);
                        recyclerView.setAdapter(myAdapter);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "onResponse: " + e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<Tagihan> call, Throwable t) {
                Toast.makeText(DetailSPPActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTahunSpp() {
        tagihanApiMethod = Services.getRetrofit().create(ApiMethod.class);
        Call<ModelTahun> call = tagihanApiMethod.getTahunTagihan();
        call.enqueue(new Callback<ModelTahun>() {
            @Override
            public void onResponse(Call<ModelTahun> call, Response<ModelTahun> response) {
                if (response.isSuccessful()) {
                    ModelTahun tagihanResponse = response.body();
                    List<DataTahun> tahunList = tagihanResponse.getData();
                    dataDropdown = new ArrayList<>();
                    for (DataTahun item : tahunList) {
                        dataDropdown.add(item.getTahun());
                    }
                    adapter.clear();
                    adapter.addAll(dataDropdown);
                    completeTextView.setAdapter(adapter);
                } else {
                    Toast.makeText(DetailSPPActivity.this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelTahun> call, Throwable t) {
                Toast.makeText(DetailSPPActivity.this, "Data error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private void makePayment() {
        SdkUIFlowBuilder.init()
                .setContext(this)
                .setMerchantBaseUrl(BuildConfig.BASE_URL)
                .setClientKey(BuildConfig.CLIENT_KEY)
                .setTransactionFinishedCallback(this)
                .enableLog(true)
                .setColorTheme(new CustomColorTheme("#010C42", "#010C42", "#3f0d0d"))
                .buildSDK();
    }

    @NonNull
    public static CustomerDetails customerDetails() {
        CustomerDetails cd = new CustomerDetails();
        cd.setFirstName(sharedPreferences.getString("nama", null));
        cd.setEmail(sharedPreferences.getString("email", null));
        cd.setPhone(sharedPreferences.getString("no_hp", null));
        return cd;
    }

    @Override
    public void onTransactionFinished(TransactionResult result) {
        if (result.getResponse() != null) {
            switch (result.getStatus()) {
                case TransactionResult.STATUS_SUCCESS:
                    Toast.makeText(this, "Transaction Berhasil " + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
                    break;
                case TransactionResult.STATUS_PENDING:
                    Toast.makeText(this, "Transaction Pending " + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
                    break;
                case TransactionResult.STATUS_FAILED:
                    Toast.makeText(this, "Transaction Failed" + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
                    break;
            }
            result.getResponse().getStatusMessage();
        } else if (result.isTransactionCanceled()) {
            Toast.makeText(this, "Transaction Failed", Toast.LENGTH_LONG).show();
        } else {
            if (result.getStatus().equalsIgnoreCase((TransactionResult.STATUS_INVALID))) {
                Toast.makeText(this, "Transaction Invalid" + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
