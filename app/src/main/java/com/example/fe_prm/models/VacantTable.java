package com.example.fe_prm.models;

public class VacantTable {
    private int amount;
    private String time;

    public VacantTable() {
    }

    public VacantTable(int amount, String time) {
        this.amount = amount;
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
