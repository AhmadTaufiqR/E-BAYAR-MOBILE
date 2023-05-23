package com.taufiq.e_bayar.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
import com.taufiq.e_bayar.Adapter.MyAdapter;
import com.taufiq.e_bayar.BuildConfig;
import com.taufiq.e_bayar.Model.ModelTagihan;
import com.taufiq.e_bayar.R;

import java.util.ArrayList;

public class DetailSPPActivity extends AppCompatActivity implements TransactionFinishedCallback {
    Toolbar toolbar;
    Button btn;
    StringBuffer harga;
    StringBuffer id;
    StringBuffer jumlah;
    StringBuffer name;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<ItemDetails> itemDetails;
    ArrayList<ModelTagihan> list;
    TextView totalamount;
    int jumlahtotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sppactivity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar = findViewById(R.id.toolbardetailspp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn = findViewById(R.id.bayarSpp);
        recyclerView = findViewById(R.id.recyclerSPP);
        totalamount = findViewById(R.id.totalSPP);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                harga = new StringBuffer();
                jumlah = new StringBuffer();
                id = new StringBuffer();
                name = new StringBuffer();
                itemDetails = new ArrayList<>();
                    for (ModelTagihan mt : myAdapter.checkedmodelTagihan) {
                         itemDetails.add(new ItemDetails(String.valueOf(mt.getId()), Double.valueOf(mt.getHarga()),
                                 mt.getJumlah(), String.valueOf(mt.getBulan())));
                        jumlahtotal += mt.getHarga();
                    }
                clickPay();
                jumlahtotal=0;
            }
        });

        myAdapter = new MyAdapter(getApplicationContext(), getTagihan(), totalamount);
        recyclerView.setAdapter(myAdapter);
        makePayment();
    }



    private ArrayList<ModelTagihan> getTagihan() {
        list = new ArrayList<>();
        ModelTagihan item = new ModelTagihan( "1", 300000, 1, "Februari");
        ModelTagihan item1 = new ModelTagihan("2", 300000, 1, "Maret");
        ModelTagihan item2 = new ModelTagihan("3", 300000, 1, "April");

        list.add(item);
        list.add(item1);
        list.add(item2);

        return list;
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

    @NonNull
    public static CustomerDetails customerDetails(){
        CustomerDetails cd = new CustomerDetails();
        cd.setFirstName("Taufiq");
        cd.setLastName("Ridho");
        cd.setEmail("email@gmail.com");
        cd.setPhone("Nope");
        return cd;
    }

    private void makePayment(){
        SdkUIFlowBuilder.init()
                .setContext(this)
                .setMerchantBaseUrl(BuildConfig.BASE_URL)
                .setClientKey(BuildConfig.CLIENT_KEY)
                .setTransactionFinishedCallback(this)
                .enableLog(true)
                .setColorTheme(new CustomColorTheme("#010C42","#010C42" , "#3f0d0d"))
                .buildSDK();
    }

    private void clickPay(){
        MidtransSDK.getInstance().setTransactionRequest(transactionRequest(itemDetails, jumlahtotal));
        MidtransSDK.getInstance().startPaymentUiFlow(DetailSPPActivity.this);
    }

    @NonNull
    public static TransactionRequest transactionRequest(ArrayList<ItemDetails> itemDetails, int hargaTot){
        TransactionRequest request =  new TransactionRequest(System.currentTimeMillis() + " " , hargaTot );
        request.setCustomerDetails(customerDetails());
        request.setItemDetails(itemDetails);
        CreditCard creditCard = new CreditCard();
        creditCard.setSaveCard(false);
        creditCard.setAuthentication(CreditCard.RBA);

        request.setCreditCard(creditCard);
        return request;
    }

    @Override
    public void onTransactionFinished(TransactionResult result) {
        if(result.getResponse() != null){
            switch (result.getStatus()){
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
        }else if(result.isTransactionCanceled()){
            Toast.makeText(this, "Transaction Failed", Toast.LENGTH_LONG).show();
        }else{
            if(result.getStatus().equalsIgnoreCase((TransactionResult.STATUS_INVALID))){
                Toast.makeText(this, "Transaction Invalid" + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

}