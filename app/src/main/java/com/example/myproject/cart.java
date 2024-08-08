package com.example.myproject;

import java.io.Serializable;
import java.util.Arrays;

public class cart implements Serializable {
    private String name;
    private String price,id;
    private byte[] img;
    private String phone,count;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "cart{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", id='" + id + '\'' +
                ", img=" + Arrays.toString(img) +
                ", phone='" + phone + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public cart(String name, String price, String id, byte[] img, String phone, String count) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.img = img;
        this.phone = phone;
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
