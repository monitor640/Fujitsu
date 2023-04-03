package com.example.fujitsudelivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WPEFRepository extends JpaRepository<WPEF, Integer> {
    WPEF save(WPEF entities);
}