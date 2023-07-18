package com.example.fe_prm.apiservices.bookingreservations;


import com.example.fe_prm.apiservices.APIClient;

public class BookingReservationRepo {
    public static BookingReservationService getBookingReservationService() {
        return APIClient.getClient().create(BookingReservationService.class);
    }
}
