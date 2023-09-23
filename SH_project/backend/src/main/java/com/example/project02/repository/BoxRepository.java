package com.example.project02.repository;

import com.example.project02.entity.Box;
import com.example.project02.entity.ProductInBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
    // Add custom query methods if needed
}
