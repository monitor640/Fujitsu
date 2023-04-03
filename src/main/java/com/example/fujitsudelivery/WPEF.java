package com.example.fujitsudelivery;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/*
    * This class is used to create the WPEF table
    * It is used to store the additional cost for each phenomenon
 */

@Entity
@Table(name = "wpef")
public class WPEF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wpef_id;

    @OneToMany(mappedBy = "WPEF", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Phenomenon> phenomenon = new HashSet<>();

    @Column(name = "price")
    double price;


    public Set<Phenomenon> getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(Set<Phenomenon> phenomenon) {
        this.phenomenon = phenomenon;
        phenomenon.forEach(p -> p.setExtraFees(this));
    }
    public void addPhenomenon(Phenomenon phenomenon) {
        this.phenomenon.add(phenomenon);
        phenomenon.setExtraFees(this);

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}