package com.example.fujitsudelivery;

import jakarta.persistence.*;

/*
    * Class to represent the ATEF table in the database
    * Lets you set different additional prices for different temperature ranges
    */
@Entity
@Table(name = "atef")
public class ATEF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    //upper temperature limit for the range
    @Column(name = "upper")
    private double upper;
    //lower temperature limit for the range
    @Column(name = "lower")
    private double lower;
    @Column(name = "price")
    private double price;

    public ATEF() {
    }


    public double getUpper() {
        return upper;
    }

    public double getPrice() {
        return price;
    }

    public double getLower() {
        return lower;
    }

    public void setUpper(double upper) {
        this.upper = upper;
    }

    public void setLower(double lower) {
        this.lower = lower;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}