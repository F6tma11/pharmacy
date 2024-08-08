package com.example.myproject;

import java.io.Serializable;
import java.util.Arrays;

public class card implements Serializable {

    private String name;
    private String price,id;
    private byte[] img;
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public card(String name, String price, byte[] img, String category, String id) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.img = img;
        this.category = category;
    }

    @Override
    public String toString() {
        return "card{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", id='" + id + '\'' +
                ", img=" + Arrays.toString(img) +
                ", category='" + category + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}