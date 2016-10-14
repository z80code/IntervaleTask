package com.intervale.models;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.NONE)
public class Commission {
    @XmlAttribute
    private int id;
    @XmlElement
    private Brand brand;
    @XmlElement
    private Currency currency;
    @XmlElement
    private double value;

    public Commission() {
    }

    public Commission(int id, Brand brand, Currency currency, double value) {
        this.id = id;
        this.brand = brand;
        this.currency = currency;
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }
}
