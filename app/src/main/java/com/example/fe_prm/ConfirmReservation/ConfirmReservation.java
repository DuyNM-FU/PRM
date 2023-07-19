package com.example.fe_prm.ConfirmReservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fe_prm.FoodOrder.Activity.CartListActivity;
import com.example.fe_prm.FoodOrder.Domain.OrderDomain;
import com.example.fe_prm.FoodOrder.Helper.TinyDB;
import com.example.fe_prm.Loading;
import com.example.fe_prm.MainActivity;
import com.example.fe_prm.Payment.Payment;
import com.example.fe_prm.R;
import com.example.fe_prm.TableReservationActivity;
import com.example.fe_prm.models.DesiredReservation;
import com.example.fe_prm.view_your_reservation.ViewYourReservationActivity;
import com.example.fe_prm.view_your_reservation.api.ReservationRepository;
import com.example.fe_prm.view_your_reservation.dto.ReservationInformationDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmReservation extends AppCompatActivity {
    OrderDomain orderDomain;
    Button btn_selection;
    ImageView iv_rollBack;
    TextView tv_orderFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);

        init();
        initControl();
    }

    public void initControl(){
        btn_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmReservation.this, Payment.class);
                startActivity(intent);
            }
        });
        tv_orderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(ConfirmReservation.this, CartListActivity.class);
                startActivity(cartIntent);
            }
        });

        iv_rollBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(ConfirmReservation.this, CartListActivity.class);
                startActivity(cartIntent);
            }
        });


    }

    public void init(){
        TinyDB tinyDB = new TinyDB(ConfirmReservation.this);
        ArrayList<DesiredReservation> reservationList = tinyDB.getReservationObject("TableReservation");
        DesiredReservation reservation = reservationList.get(reservationList.size()-1);

        btn_selection = findViewById(R.id.btn_selection);
        btn_selection.setText("CONFIRM RESERVATION");
        iv_rollBack = findViewById(R.id.iv_rollBack);

        tv_orderFood = findViewById(R.id.tv_orderFood);
        SpannableString id = new SpannableString("000001");
        id.setSpan(new UnderlineSpan(), 0, id.length(), 0);
        tv_orderFood.setText(id);
    }
}