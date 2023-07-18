package com.example.fe_prm.profile.api;

public class ProfileModel {
    private String email;
    private String role;
    private String phone;

    public ProfileModel() {
    }
    public ProfileModel(String phone) {
        this.phone =phone;
    }
    public ProfileModel(String email, String role, String phone) {
        this.email = email;
        this.role = role;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
