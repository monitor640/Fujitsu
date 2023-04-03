package com.example.fujitsudelivery;

import jakarta.persistence.*;
/*
    * This class is used to create the table "phenomenon" in the database
    * It is used to store the different types of phenomena that can occur
    * It is linked to the table "wpef" through the foreign key "wpef_id"
 */
@Entity(name = "phenomenon")
@Table(name = "phenomenon")
public class Phenomenon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private WPEF WPEF;

    @Column(name = "phenomenon")
    String phenomenon;


    public Phenomenon() {
    }

    public WPEF getExtraFees() {
        return WPEF;
    }

    public void setExtraFees(WPEF WPEF) {
        this.WPEF = WPEF;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }
}