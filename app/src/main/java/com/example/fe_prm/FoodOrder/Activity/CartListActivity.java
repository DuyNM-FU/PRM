package com.example.fe_prm.FoodOrder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fe_prm.ConfirmReservation.ConfirmReservation;
import com.example.fe_prm.FoodOrder.Adapter.CartListAdapter;
import com.example.fe_prm.FoodOrder.Helper.ManagementCart;
import com.example.fe_prm.FoodOrder.Interface.ChangeNumberItemListener;
import com.example.fe_prm.Payment.Payment;
import com.example.fe_prm.R;

public class CartListActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewList;
private static ManagementCart managementCart;
static TextView totalFeeTxt;
    static TextView taxTxt;
    static TextView deliveryTxt;
    static TextView totalTxt;
    TextView emptyTxt;
    TextView textView6;
public static double tax;
public String price;
private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart2();
        initControl();
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);
        recyclerViewList = findViewById(R.id.cartView);
        textView6 = findViewById(R.id.textView6);
    }

    private void initControl(){
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartListActivity.this, ConfirmReservation.class);
                startActivity(intent);
            }
        });
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this , new ChangeNumberItemListener() {
            @Override
            public void change() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    public void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 2;

        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        double total = Math.round((managementCart.getTotalFee()*tax + delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
        price=Double.toString(total);
    }
    public static String CalculateCart2(){
        double percentTax = 0.02;
        double delivery = 2;

        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        double itemTotal = Math.round((managementCart.getTotalFee()*tax + delivery)*100)/100;
        double total = Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
        int i = (int) total;
        return i+"";
    }
}