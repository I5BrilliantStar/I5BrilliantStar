package com.example.project02.repository;

import com.example.project02.entity.Product;
import com.example.project02.entity.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {

    // Add custom query methods if needed
    Optional<WareHouse> findByName(String name);
}
