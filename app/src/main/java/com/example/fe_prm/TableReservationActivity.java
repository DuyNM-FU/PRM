package com.example.fe_prm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fe_prm.adapters.ButtonAdapter;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;

import org.joda.time.LocalDate;

import java.util.ArrayList;


public class TableReservationActivity extends AppCompatActivity   {

    DayScrollDatePicker dpk_reserveDate;
    RecyclerView rv_NumOfPeople, rv_seatType, rv_reserveTime;
    ButtonAdapter numOfPeopleAdapter, seatTypeAdapter, reserveTimeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_reservation);

        dpk_reserveDate = findViewById(R.id.dpk_reserveDate);
        LocalDate today = new LocalDate();
        dpk_reserveDate.setStartDate(today.getDayOfMonth(),today.getMonthOfYear(),today.getYear());

        rv_NumOfPeople = findViewById(R.id.rv_NumOfPeople);

        numOfPeopleAdapter = new ButtonAdapter(new ArrayList<String>(){{
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
        }});

        rv_NumOfPeople.setAdapter(numOfPeopleAdapter);
        LinearLayoutManager horizontalLayout
                = new LinearLayoutManager(
                TableReservationActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);
        rv_NumOfPeople.setLayoutManager(horizontalLayout);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_location) {
            // Go to location activity
        }
        if (item.getItemId() == R.id.menu_reservations) {
            // Go to reservations activity
        }
        return super.onOptionsItemSelected(item);
    }
}