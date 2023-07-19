package com.example.fe_prm.Done;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fe_prm.HomePage;
import com.example.fe_prm.LocationsActivity;
import com.example.fe_prm.R;
import com.example.fe_prm.view_your_reservation.ViewYourReservationActivity;

public class DoneActivity extends AppCompatActivity {

    Button btn_tracker;
    ImageView iv_success, iv_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        init();
        initControl();

    }

    public void initControl(){
        btn_tracker.setText("Tracking the way");
        btn_tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(DoneActivity.this, LocationsActivity.class);
                startActivity(mapIntent);
            }
        });

        //iv_success.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoneActivity.this, ViewYourReservationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        btn_tracker = findViewById(R.id.btn_tracker);
        iv_success = findViewById(R.id.iv_success);
        iv_close = findViewById(R.id.iv_close);
    }
}