package com.intervale.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.Date;

public class Card {
    private Brand brand;
    private long number;
    private String ownerName;
    private long validity;

    public Card() {
    }

    public Card(Brand brand, long number, String ownerName, long validity) {
        this.brand = brand;
        this.number = number;
        this.ownerName = ownerName;
        this.validity = validity;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setValidity(long validity) {
        this.validity = validity;
    }

    public Brand getBrand() {
        return brand;
    }

    public long getNumber() {
        return number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getValidity() {
        return validity;
    }
}
