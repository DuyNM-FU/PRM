package com.example.fe_prm.view_your_reservation.api;

public class ReservationModel {
    private int id;
    private String email;
    private int seat;
    private boolean isPrivate ;
    private String date;
    private String time;
    private String modifiedDate;
    private String note;
    private int assignedTableId;
    private String status;

    public ReservationModel() {
    }

    public ReservationModel(int id, String email, int seat, boolean isPrivate, String date, String time, String modifiedDate, String note, int assignedTableId, String status) {
        this.id = id;
        this.email = email;
        this.seat = seat;
        this.isPrivate = isPrivate;
        this.date = date;
        this.time = time;
        this.modifiedDate = modifiedDate;
        this.note = note;
        this.assignedTableId = assignedTableId;
        this.status = status;
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

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAssignedTableId() {
        return assignedTableId;
    }

    public void setAssignedTableId(int assignedTableId) {
        this.assignedTableId = assignedTableId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
