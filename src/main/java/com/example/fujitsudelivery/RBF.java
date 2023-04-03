package com.example.fujitsudelivery;

import jakarta.persistence.*;
/*
    * This class is used to create the RBF table
    * It is used to store the RBF values for each city and vehicle
 */
@Entity
@Table(name = "rbf")
public class RBF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "city")
    String city;
    @Column(name = "vehicle")
    String vehicle;
    @Column(name = "RBF")
    double RBF;

    public RBF(String city, String vehicle, double RBF) {
        this.city = city;
        this.vehicle = vehicle;
        this.RBF = RBF;
    }
    public RBF() {
    }

    public Integer getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public double getRBF() {
        return RBF;
    }

    public void setRBF(double RBF) {
        this.RBF = RBF;
    }
}