package com.example.fe_prm.apiservices.bookingreservations;

import com.example.fe_prm.models.DesiredReservation;
import com.example.fe_prm.models.VacantTable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface BookingReservationService {
    String BOOKING_RESERVATION = "/api/booking-reservation"; // API endpoint
    String tempJWT = "Bearer abc";

    @HTTP(method = "GET", path = BOOKING_RESERVATION, hasBody = true)
    @Headers("Authorization: " + tempJWT)
    Call<VacantTable[]> getVacantTables(@Body DesiredReservation desiredReservation);


}
