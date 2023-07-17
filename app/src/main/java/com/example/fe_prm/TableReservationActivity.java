package com.example.fe_prm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fe_prm.adapters.ButtonAdapter;
import com.example.fe_prm.apiservices.bookingreservations.BookingReservationRepo;
import com.example.fe_prm.models.DesiredReservation;
import com.example.fe_prm.models.VacantTable;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TableReservationActivity extends AppCompatActivity {

    DayScrollDatePicker dpk_reserveDate;
    RecyclerView rv_NumOfPeople, rv_tableType, rv_reserveTime;
    ButtonAdapter numOfPeopleAdapter, tableTypeAdapter, reserveTimeAdapter;
    DesiredReservation desiredReservationModel = new DesiredReservation();

    TextView tv_numOfPeople, tv_tableType, tv_reserveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_reservation);

        dpk_reserveDate = findViewById(R.id.dpk_reserveDate);
        LocalDate today = new LocalDate();
        dpk_reserveDate.setStartDate(today.getDayOfMonth(), today.getMonthOfYear(), today.getYear());
        dpk_reserveDate.getSelectedDate(date -> {
            TextView tv_reserveDate = findViewById(R.id.tv_reserveDate);
            tv_reserveDate.setText(date.toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Format the date using the SimpleDateFormat object
            String formattedDate = dateFormat.format(date);
            desiredReservationModel.setDesiredDate(formattedDate);
            DisplayTimeSelection();
        });

        tv_tableType = findViewById(R.id.tv_tableType);
        tv_reserveTime = findViewById(R.id.tv_reserveTime);
        tv_numOfPeople = findViewById(R.id.tv_numOfPeople);
        ConfigRecyclerView();
        ConfigToolbar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedItem = item.getItemId();
        if (selectedItem == R.id.menu_location) {
            // Go to location activity
        }
        if (selectedItem == R.id.menu_reservations) {
            // Go to reservations activity
        }
        if (selectedItem == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConfigToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void ConfigRecyclerView() {

        rv_NumOfPeople = findViewById(R.id.rv_NumOfPeople);
        numOfPeopleAdapter = new ButtonAdapter(new ArrayList<String>() {{
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
        }}, "numOfPeople");
        numOfPeopleAdapter.setOnOptionSelected(option -> {
            desiredReservationModel.setSeat(Integer.parseInt(option.toString()));
            tv_numOfPeople.setText(desiredReservationModel.getSeat() + "");

            DisplayTimeSelection();
        });

        rv_NumOfPeople.setAdapter(numOfPeopleAdapter);
        rv_NumOfPeople.setLayoutManager(new LinearLayoutManager(
                TableReservationActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false));

        rv_tableType = findViewById(R.id.rv_tableType);
        tableTypeAdapter = new ButtonAdapter(new ArrayList<String>() {{
            add("Public");
            add("Private");
        }}, "tableType");
        tableTypeAdapter.setOnOptionSelected(option -> {
            boolean isPrivate = option.equals("Private");
            desiredReservationModel.setPrivate(isPrivate);
            tv_tableType.setText(desiredReservationModel.getPrivate() + "");

            DisplayTimeSelection();
        });
        rv_tableType.setAdapter(tableTypeAdapter);
        rv_tableType.setLayoutManager(new LinearLayoutManager(
                TableReservationActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false));

        rv_reserveTime = findViewById(R.id.rv_reserveTime);


        tv_reserveTime.setVisibility(View.GONE);
        rv_reserveTime.setVisibility(View.GONE);
    }

    private void DisplayTimeSelection() {
        boolean isEnoughProperty =
                desiredReservationModel.getPrivate() != null
                        && desiredReservationModel.getDesiredDate() != null
                        && desiredReservationModel.getSeat() != null;

        if (isEnoughProperty) {
            List<VacantTable> vacantTables = BookingReservationRepo.getVacantTables(desiredReservationModel);
            setVacantTimeListView(vacantTables);
            tv_reserveTime.setVisibility(View.VISIBLE);
            rv_reserveTime.setVisibility(View.VISIBLE);
        }
    }

    private void setVacantTimeListView(List<VacantTable> vacantTables) {
        List<String> availiableTimes = vacantTables.stream()
                .map(VacantTable::getTime)
                .collect(Collectors.toList());
        reserveTimeAdapter = new ButtonAdapter(availiableTimes, "reserveTime");
        reserveTimeAdapter.setOnOptionSelected(option -> {
            desiredReservationModel.setDesiredTime(option);
            tv_reserveTime.setText(desiredReservationModel.getDesiredTime() + "");
        });
        rv_reserveTime.setAdapter(reserveTimeAdapter);
        rv_reserveTime.setLayoutManager(new LinearLayoutManager(
                TableReservationActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false));
    }
}