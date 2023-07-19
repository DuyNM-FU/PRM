package com.example.fe_prm.view_your_reservation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_prm.Loading;
import com.example.fe_prm.MainActivity;
import com.example.fe_prm.R;
import com.example.fe_prm.TableReservationActivity;
import com.example.fe_prm.profile.ProfileActivity;
import com.example.fe_prm.view_your_reservation.adapter.ReservationRecycleViewAdapter;
import com.example.fe_prm.view_your_reservation.api.ReservationRepository;
import com.example.fe_prm.view_your_reservation.dto.ReservationInformationDto;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewYourReservationActivity extends AppCompatActivity {
    List<ReservationInformationDto> reservationDtos;
    //list of reservations
    RecyclerView recyclerView;

    //on toolbar text
    TextView userTitleText, userNameText;
    ImageView backButton, viewMapButton, viewNotificationButton;
    CircleImageView userAvatar;

    //on Reservation textview
    TextView reservationHelloText,reservationTitleText; //before RecyclerView
    ImageView addButton;
    ReservationRecycleViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_reservation);
//        requestPermissions(new String[]{Re});
        init();
        renderRecyclerView();
        findViewById(R.id.ll_userInformation).setOnClickListener(v->{
            startActivity(new Intent(this, ProfileActivity.class));
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                recyclerView.setMinimumHeight(500);
            });
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewYourReservationActivity.this, TableReservationActivity.class);
                startActivity(intent);
            }
        });
    }
    public void renderRecyclerView(){
        reservationDtos = new ArrayList<>();
        adapter = new ReservationRecycleViewAdapter(reservationDtos, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (adapter.getItemCount() > 0) {
            reservationTitleText.setText("Your reservations");
        } else reservationTitleText.setText("No Reservation yet");
        loadReservation();
    }
    public void loadReservation(){
        Call<ReservationInformationDto[]> call = ReservationRepository.getReservationService().getHistory(MainActivity.AUTORIZATION_KEY);
        Loading.setLoading(this,true);
        call.enqueue(new Callback<ReservationInformationDto[]>() {
            @Override
            public void onResponse(Call<ReservationInformationDto[]> call, Response<ReservationInformationDto[]> response) {
                ReservationInformationDto[] dtos = response.body();
                Log.d("TAG", "onResponse: OK " + response.body() + " dto: " + dtos.length);
                if (dtos != null)
                    for (ReservationInformationDto dto:
                            dtos) {
                        reservationDtos.add(dto);
                        adapter.notifyDataSetChanged();
                        reservationTitleText.setText("Your reservations");
                    } else {
                    Toast.makeText(ViewYourReservationActivity.this,
                            "Load okey but ...", Toast.LENGTH_SHORT).show();
                }
                Loading.setLoading(ViewYourReservationActivity.this,false);
            }
            @Override
            public void onFailure(Call<ReservationInformationDto[]> call, Throwable t) {
                Toast.makeText(ViewYourReservationActivity.this, "Failed to load", Toast.LENGTH_SHORT).show();
                Loading.setLoading(ViewYourReservationActivity.this,false);
            }
        });

        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewYourReservationActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
    public void init(){
        recyclerView = findViewById(R.id.rv_listReservation);
        userTitleText = findViewById(R.id.tv_userTitle);
        userNameText = findViewById(R.id.tv_userName);
        reservationHelloText = findViewById(R.id.tv_helloUserAtReservation);
        reservationTitleText = findViewById(R.id.tv_reservationTitle);
        addButton = findViewById(R.id.iv_addNewReservation);
        backButton = findViewById(R.id.iv_rollBack);
        viewMapButton = findViewById(R.id.iv_reservationMap);
        viewNotificationButton = findViewById(R.id.iv_notification);
        userAvatar = findViewById(R.id.iv_userAvatar);
        userAvatar.setImageDrawable(getDrawable(R.drawable.quang_avatar));
    }
}
