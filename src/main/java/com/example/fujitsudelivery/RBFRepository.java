package com.example.fujitsudelivery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RBFRepository extends JpaRepository<RBF, Integer> {
    RBF findByCityAndVehicle(String city, String vehicle);
    //RBF save(RBF entities);
}