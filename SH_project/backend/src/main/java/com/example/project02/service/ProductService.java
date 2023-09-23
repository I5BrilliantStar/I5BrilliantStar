package com.example.project02.service;

import com.example.project02.DTO.ProductDTO;
import com.example.project02.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO getProductByName(String name);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
