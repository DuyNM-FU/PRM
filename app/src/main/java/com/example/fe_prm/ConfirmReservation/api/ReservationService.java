package com.example.fe_prm.ConfirmReservation.api;

import com.example.fe_prm.ConfirmReservation.dto.ReservationInformationDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ReservationService {
    final String RESERVATION_PATH = "api/booking-reservation";

    @Headers({"Accept: application/json"})
    @GET(RESERVATION_PATH + "/history")
    Call<ReservationInformationDto[]> getHistory(@Header("Authorization") String api_key);
}
