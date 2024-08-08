package com.example.myproject;

public class orders {
    String phone;
    int id;
    int  pices;

    public orders(String phone, int id, int pices) {
        this.phone = phone;
        this.id = id;
        this.pices = pices;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPices() {
        return pices;
    }

    public void setPices(int pices) {
        this.pices = pices;
    }
}
