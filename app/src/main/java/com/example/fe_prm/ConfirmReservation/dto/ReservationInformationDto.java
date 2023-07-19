package com.example.fe_prm.ConfirmReservation.dto;

public class ReservationInformationDto {
    private int id;
    private String email;
    private int seat;
    private boolean isPrivate ;
    private String date;
    private String time;

    private String modifiedDate;
    private String note;
    private String status;
    private String orderFoodID;
    private int table;


    public ReservationInformationDto(int id, String email, String date, String time, int seat, int table, boolean isPrivate, String orderFoodID) {
        this.id = id;
        this.email = email;
        this.seat = seat;
        this.isPrivate = isPrivate;
        this.date = date;
        this.time = time;
        this.orderFoodID = orderFoodID;
        this.table = table;
    }

    public ReservationInformationDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderFoodID() {
        return orderFoodID;
    }

    public void setOrderFoodID(String orderFoodID) {
        this.orderFoodID = orderFoodID;
    }


    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }
}
