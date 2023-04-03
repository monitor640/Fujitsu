package com.example.fujitsudelivery;

import jakarta.persistence.*;
/*
    * This class is used to create the WSEF table
    * It is used to store the additional cost based on wind speed
    *
     */
@Entity
@Table(name = "wsef")
public class WSEF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "lower")
    private double lower;
    @Column(name = "upper")
    private double upper;
    @Column(name = "price")
    private double price;


    public WSEF() {
    }
    public Integer getId() {
        return id;
    }

    public double getLower() {
        return lower;
    }

    public void setLower(double lower) {
        this.lower = lower;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getUpper() {
        return upper;
    }

    public void setUpper(double upper) {
        this.upper = upper;
    }
}