package com.example.fe_prm.models;

import java.util.Date;

public class DesiredReservation {
    private Integer seat;
    private Boolean isPrivate;
    private String desiredDate;
    private String desiredTime;

    public DesiredReservation() {
    }

    public DesiredReservation(Integer seat, Boolean isPrivate, String desiredDate, String desiredTime) {
        this.seat = seat;
        this.isPrivate = isPrivate;
        this.desiredDate = desiredDate;
        this.desiredTime = desiredTime;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(String desiredDate) {
        this.desiredDate = desiredDate;
    }

    public String getDesiredTime() {
        return desiredTime;
    }

    public void setDesiredTime(String desiredTime) {
        this.desiredTime = desiredTime;
    }
}
