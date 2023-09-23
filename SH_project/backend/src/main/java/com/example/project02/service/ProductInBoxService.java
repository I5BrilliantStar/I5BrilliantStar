package com.example.project02.service;

import com.example.project02.DTO.ProductInBoxDTO;
import com.example.project02.entity.ProductInBox;

import java.util.List;

public interface ProductInBoxService {
    List<ProductInBoxDTO> getAllProductInBoxes();
    ProductInBoxDTO getProductInBoxById(Long id);
    ProductInBoxDTO createProductInBox(ProductInBoxDTO productInBoxDTO);
    ProductInBoxDTO updateProductInBox(Long id, ProductInBoxDTO productInBoxDTO);
    boolean deleteProductInBox(Long id);
}
