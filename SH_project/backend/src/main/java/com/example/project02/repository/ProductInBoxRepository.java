package com.example.project02.repository;

import com.example.project02.DTO.ProductInBoxDTO;
import com.example.project02.entity.ProductInBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInBoxRepository extends JpaRepository<ProductInBox, Long> {
}
