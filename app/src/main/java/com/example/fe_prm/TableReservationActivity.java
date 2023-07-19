package com.example.fe_prm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fe_prm.FoodOrder.FoodOrder;
import com.example.fe_prm.FoodOrder.Helper.TinyDB;
import com.example.fe_prm.adapters.ButtonAdapter;
import com.example.fe_prm.apiservices.bookingreservations.BookingReservationRepo;
import com.example.fe_prm.models.DesiredReservation;
import com.example.fe_prm.models.VacantTable;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TableReservationActivity extends AppCompatActivity {

    DayScrollDatePicker dpk_reserveDate;
    RecyclerView rv_NumOfPeople, rv_tableType, rv_reserveTime;
    ButtonAdapter numOfPeopleAdapter, tableTypeAdapter, reserveTimeAdapter;
    Button btn_ReservationNext;
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Format the date using the SimpleDateFormat object
            String formattedDate = dateFormat.format(date);
            desiredReservationModel.setDesiredDate(formattedDate);
            DisplayTimeSelection();
        });

        btn_ReservationNext = findViewById(R.id.btn_ReservationNext);
        btn_ReservationNext.setVisibility(View.VISIBLE);
        btn_ReservationNext.setOnClickListener(v -> {
            TinyDB tinyDB = new TinyDB(TableReservationActivity.this);
            ArrayList<DesiredReservation> reservationList = tinyDB.getReservationObject("TableReservation");
            reservationList.add(desiredReservationModel);
            tinyDB.putListReservation("TableReservation",reservationList);
            startActivity(new Intent(TableReservationActivity.this, FoodOrder.class));
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
            Intent intent = new Intent(TableReservationActivity.this, LocationsActivity.class);
            startActivity(intent);
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
            desiredReservationModel.setSeat(Integer.parseInt(option));

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
            TinyDB tinyDB = new TinyDB(TableReservationActivity.this);
            String tempJWT = "Bearer " +
                    tinyDB.getString("bearerKey").substring(1);

            Call<VacantTable[]> call = BookingReservationRepo.getBookingReservationService().getVacantTables(desiredReservationModel,tempJWT);
            call.enqueue(new Callback<VacantTable[]>() {
                @Override
                public void onResponse(Call<VacantTable[]> call, Response<VacantTable[]> response) {
                    List<VacantTable> vacantTables;
                    VacantTable[] vacantResponse = response.body();
                    Log.i("API code", response.code()+"");
                    Log.i("API message", response.message());
                    if (vacantResponse == null) {
                        return;
                    }

                    vacantTables = new ArrayList<>(Arrays.asList(vacantResponse));
                    setVacantTimeListView(vacantTables);
                }

                @Override
                public void onFailure(Call<VacantTable[]> call, Throwable t) {
                    Log.e("API Error", t.toString());
                }
            });
            tv_reserveTime.setVisibility(View.VISIBLE);
            rv_reserveTime.setVisibility(View.VISIBLE);
        }

    }

    private void setVacantTimeListView(List<VacantTable> vacantTables) {
        List<String> availiableTimes = vacantTables.stream()
                .filter(s -> s.getAmount() > 0)
                .map(VacantTable::getTime)
                .collect(Collectors.toList());
        reserveTimeAdapter = new ButtonAdapter(availiableTimes, "reserveTime");
        reserveTimeAdapter.setOnOptionSelected(option -> {
            desiredReservationModel.setDesiredTime(option);
            displayNextButton();
        });
        rv_reserveTime.setAdapter(reserveTimeAdapter);
        rv_reserveTime.setLayoutManager(new LinearLayoutManager(
                TableReservationActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false));
    }

    private void displayNextButton() {
        boolean isEnoughProperty =
                desiredReservationModel.getPrivate() != null
                        && desiredReservationModel.getDesiredDate() != null
                        && desiredReservationModel.getSeat() != null;

        boolean isReadyForNextPage = isEnoughProperty && desiredReservationModel.getDesiredTime() != null;

        if (isReadyForNextPage) {
            btn_ReservationNext.setVisibility(View.VISIBLE);
            btn_ReservationNext.setOnClickListener(v -> {
                TinyDB tinyDB = new TinyDB(TableReservationActivity.this);
                ArrayList<DesiredReservation> reservationList = tinyDB.getReservationObject("TableReservation");
                reservationList.add(desiredReservationModel);
                tinyDB.putListReservation("TableReservation",reservationList);
                startActivity(new Intent(TableReservationActivity.this, FoodOrder.class));
            });
        }
    }
}