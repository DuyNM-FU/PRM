package com.example.fe_prm.view_your_reservation.confirm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fe_prm.R;
import com.example.fe_prm.view_your_reservation.dto.ReservationInformationDto;

public class ConfirmReservation extends AppCompatActivity {

    ReservationInformationDto reservationInformationDto;
    Button btn_selection;
    ImageView iv_rollBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);
        init();

    }

    public void init(){
        btn_selection = findViewById(R.id.btn_selection);
        btn_selection.setText("CONFIRM RESERVATION");
        iv_rollBack = findViewById(R.id.iv_rollBack);
    }
}