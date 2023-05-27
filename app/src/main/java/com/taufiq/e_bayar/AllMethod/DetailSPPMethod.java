package com.taufiq.e_bayar.AllMethod;


import static com.taufiq.e_bayar.Activities.DetailSPPActivity.customerDetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.CreditCard;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;
import com.taufiq.e_bayar.Activities.DetailSPPActivity;
import com.taufiq.e_bayar.BuildConfig;

import java.util.ArrayList;

public class DetailSPPMethod {

    public void clickPay(ArrayList<ItemDetails> itemDetails, int jumlahtotal, Context context){
        MidtransSDK.getInstance().setTransactionRequest(transactionRequest(itemDetails, jumlahtotal));
        MidtransSDK.getInstance().startPaymentUiFlow(context);
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


}
