package com.example.fe_prm.view_your_reservation.api;

public class ReservationRepository {
    public static String API_KEY;
    public static ReservationService getReservationService(){
        return APIClient.getClient().create(ReservationService.class);
    };
}
