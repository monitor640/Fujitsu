package com.example.fujitsudelivery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhenomenonRepository extends JpaRepository<Phenomenon, Integer> {
    //find by extratype
    //find by phenomenon

    Phenomenon findByPhenomenon(String phenomenon);

    void deleteByWPEF(WPEF id);
}