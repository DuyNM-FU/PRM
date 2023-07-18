package com.example.fe_prm.apiservices.bookingreservations;

import android.util.Log;

import com.example.fe_prm.apiservices.APIClient;
import com.example.fe_prm.models.DesiredReservation;
import com.example.fe_prm.models.VacantTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingReservationRepo {
    public static BookingReservationService getBookingReservationService() {
        return APIClient.getClient().create(BookingReservationService.class);
    }

    public static List<VacantTable> getVacantTables(DesiredReservation desiredReservation) {
        Call<VacantTable[]> call = getBookingReservationService().getVacantTables(desiredReservation);
        final List<VacantTable>[] vacantTables = new List[]{new ArrayList<>()};
        call.enqueue(new Callback<VacantTable[]>() {
            @Override
            public void onResponse(Call<VacantTable[]> call, Response<VacantTable[]> response) {
                VacantTable[] vacantResponse = response.body();
                if (vacantResponse == null) {
                    return;
                }

                vacantTables[0] =new ArrayList<>(Arrays.asList(vacantResponse));
            }

            @Override
            public void onFailure(Call<VacantTable[]> call, Throwable t) {
                Log.e("API Error", t.toString());
            }
        });

        return vacantTables[0];
    }
}
