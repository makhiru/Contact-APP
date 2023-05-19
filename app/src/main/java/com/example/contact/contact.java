package com.example.contact;

import java.io.Serializable;

public class contact implements Serializable {

    int img;
    String name, number;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public contact(int img, String name, String number, int id) {
        this.img = img;
        this.name = name;
        this.number = number;
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    contact(int img, String name, String number) {
        this.img = img;
        this.name = name;
        this.number = number;
    }

}
