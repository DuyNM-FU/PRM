package com.example.fe_prm.ConfirmReservation.api;

public class ReservationRepository {
    public static String API_KEY;
    public static ReservationService getReservationService(){
        return APIClient.getClient().create(ReservationService.class);
    };
}
