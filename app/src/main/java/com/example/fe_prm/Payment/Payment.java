package com.example.fe_prm.Payment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fe_prm.ConfirmReservation.ConfirmReservation;
import com.example.fe_prm.FoodOrder.Activity.CartListActivity;
import com.example.fe_prm.FoodOrder.Domain.FoodDomain;
import com.example.fe_prm.FoodOrder.Domain.OrderDomain;
import com.example.fe_prm.FoodOrder.Helper.ManagementCart;
import com.example.fe_prm.MainActivity;
import com.example.fe_prm.R;

import org.json.JSONObject;

import java.util.List;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class Payment extends AppCompatActivity {
    CartListActivity cartListActivity;
    ImageView iv_rollBack, iv_zlp;
    TextView tv_price;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //zalo pay
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);

        init();
        initControl();
    }

    public void initControl(){
        iv_zlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManagementCart managementCart = new ManagementCart(Payment.this);
                List<FoodDomain> cartList = managementCart.getListCart();

                OrderDomain newCart = new OrderDomain(cartList, 1);

                requestZaloPay();
            }
        });

        iv_rollBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(Payment.this, ConfirmReservation.class);
                startActivity(cartIntent);
            }
        });
        price = cartListActivity.CalculateCart2();
        tv_price.setText(price);
    }

    private void requestZaloPay(){
        CreateOrder oder = new CreateOrder();
        price = cartListActivity.CalculateCart2();
        try {
            //**********
            JSONObject data = oder.createOrder(price);
            //**********
            String code = data.getString("return_code");

            if (code.equals("1")){
                String token = data.getString("zp_trans_token");
                ZaloPaySDK.getInstance().payOrder(Payment.this, token, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(Payment.this)
                                        .setTitle("Payment Success")
                                        .setMessage(String.format("TransactionId: %s - TransToken: %s", transactionId, transToken))
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        })
                                        .setNegativeButton("Cancel", null).show();
                            }

                        });

                    }

                    @Override
                    public void onPaymentCanceled(String zpTransToken, String appTransID) {
                        new AlertDialog.Builder(Payment.this)
                                .setTitle("User Cancel Payment")
                                .setMessage(String.format("zpTransToken: %s \n", zpTransToken))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                        new AlertDialog.Builder(Payment.this)
                                .setTitle("Payment Fail")
                                .setMessage(String.format("ZaloPayErrorCode: %s \nTransToken: %s", zaloPayError.toString(), zpTransToken))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void init(){
        iv_rollBack = findViewById(R.id.iv_rollBack);
        iv_zlp = findViewById(R.id.iv_zlp);
        tv_price = findViewById(R.id.tv_price);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}