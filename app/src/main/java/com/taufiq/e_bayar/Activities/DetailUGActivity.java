package com.taufiq.e_bayar.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.CreditCard;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;
import com.taufiq.e_bayar.BuildConfig;
import com.taufiq.e_bayar.Model.spp.DataItem;
import com.taufiq.e_bayar.Model.spp.Tagihan;
import com.taufiq.e_bayar.R;
import com.taufiq.e_bayar.Request.Services;
import com.taufiq.e_bayar.Utils.ApiMethod;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUGActivity extends AppCompatActivity implements TransactionFinishedCallback {

    Toolbar toolbar;
    TextView nama_detailUg, nominal;
    Button btn;
    EditText jumlah_masukkan;
    static SharedPreferences sharedPreferences;
    ApiMethod tagihanApiMethod;
    int jumlah_bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_detail_ugactivity);
        toolbar = findViewById(R.id.toolbardetailUg);
        nama_detailUg = findViewById(R.id.namaDetailUg);
        nominal = findViewById(R.id.tgug);
        jumlah_masukkan = findViewById(R.id.jumlah_ug);
        btn = findViewById(R.id.bayarUg);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences("Ebayar", MODE_PRIVATE);
        String nama = sharedPreferences.getString("nama", "");
        nama_detailUg.setText(nama);
        getTotalUg(2023);
        setupEditTextListener();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah_bayar = Integer.parseInt(jumlah_masukkan.getText().toString());
//                Log.e(TAG, "onClick: " + jumlah_bayar);
                clickPay();
            }
        });
        makePayment();
    }

    private void getTotalUg(int tahun) {
        tagihanApiMethod = Services.getRetrofit().create(ApiMethod.class);
        Call<Tagihan> call = tagihanApiMethod.getTagihanUG(tahun);
        call.enqueue(new Callback<Tagihan>() {
            @Override
            public void onResponse(Call<Tagihan> call, Response<Tagihan> response) {
                if (response.isSuccessful()) {
                    Tagihan tagihan = response.body();
                    List<DataItem> dataItem = tagihan.getData();
                    if (!dataItem.isEmpty()) {
                        DataItem firstDataItem = dataItem.get(0);
                        String jumlahTagihan = String.valueOf(firstDataItem.getJumlahBayar());
                        TextView tagihanTextView = findViewById(R.id.tgug);
                        double total = Double.parseDouble(jumlahTagihan);
                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
                        String hasil = decimalFormat.format(total);
                        tagihanTextView.setText("Rp." + hasil);
                    } else {
                        Toast.makeText(DetailUGActivity.this, "Data tagihan kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailUGActivity.this, "Gagal mengambil data tagihan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Tagihan> call, Throwable t) {
                Toast.makeText(DetailUGActivity.this, "Gagal mengambil data tagihan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupEditTextListener() {
        EditText editText = findViewById(R.id.jumlah_ug);
        TextView textView = findViewById(R.id.totalUg);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textView.setText("Rp. 0");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double total = 0;
                try {
                    total = Double.parseDouble(s.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                String hasil = decimalFormat.format(total);
                textView.setText("Rp. " + hasil);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Tidak perlu implementasi
            }
        });
    }
    public void clickPay(){
//        Log.e("Tag", String.valueOf(jumlah_bayar));
        MidtransSDK.getInstance().setTransactionRequest(transactionRequest("1",jumlah_bayar, 1, "Uang Gedung"));
        MidtransSDK.getInstance().startPaymentUiFlow(DetailUGActivity.this);
    }

    @NonNull
    private static TransactionRequest transactionRequest(String id, int price, int qty, String name){
        TransactionRequest request =  new TransactionRequest(System.currentTimeMillis() + " " , price );
        request.setCustomerDetails(customerDetails());
        ItemDetails details = new ItemDetails(id, (double) price, qty, name);
        ArrayList<ItemDetails> itemDetails = new ArrayList<>();
        itemDetails.add(details);
        request.setItemDetails(itemDetails);
        CreditCard creditCard = new CreditCard();
        creditCard.setSaveCard(false);
        creditCard.setAuthentication(CreditCard.RBA);

        request.setCreditCard(creditCard);
        return request;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @NonNull
    public static CustomerDetails customerDetails() {
        CustomerDetails cd = new CustomerDetails();
        cd.setFirstName(sharedPreferences.getString("nama", null));
        cd.setEmail(sharedPreferences.getString("email", null));
        cd.setPhone(sharedPreferences.getString("no_hp", null));
        return cd;
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

    @Override
    public void onTransactionFinished(TransactionResult result) {
        if (result.getResponse() != null) {
            switch (result.getResponse().getTransactionStatus()) {
                case TransactionResult.STATUS_SUCCESS:
                    Toast.makeText(this, "Transaction Berhasil " + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(DetailUGActivity.this, DetailUGActivity.class));
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
            if (result.getStatus().equalsIgnoreCase(TransactionResult.STATUS_INVALID)) {
                Toast.makeText(this, "Transaction Invalid" + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
