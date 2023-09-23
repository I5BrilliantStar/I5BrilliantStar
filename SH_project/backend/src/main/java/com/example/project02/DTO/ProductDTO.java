package com.example.project02.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ProductDTO {
    private final Long id;

    private String name;
    private String description;
    private double price;
    private String code;
    private int quantity;
    private int fquantity;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
