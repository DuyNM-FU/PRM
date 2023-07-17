package com.example.fe_prm.apiservices.bookingreservations;

import com.example.fe_prm.models.DesiredReservation;
import com.example.fe_prm.models.VacantTable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BookingReservationService {
    String BOOKING_RESERVATION = "/api/booking-reservation"; // API endpoint


    //@GET(BOOKING_RESERVATION + )
    @POST(BOOKING_RESERVATION + "/vacant-amount")
    Call<VacantTable[]> getVacantTables(@Body DesiredReservation desiredReservation, @Header("Authorization") String authHeader);


}
