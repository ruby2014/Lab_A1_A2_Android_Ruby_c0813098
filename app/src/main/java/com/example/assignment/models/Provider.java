package com.example.assignment.models;

public class Provider {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String lat;
    private String lng;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Provider(String id, String name, String email, String phone, String lat, String lng) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
