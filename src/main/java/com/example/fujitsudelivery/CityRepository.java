package com.example.fujitsudelivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findAll();
    City save(City entities);
    Optional<City> findById(Integer integer);
    List<City> findBystation(String stationName);


}