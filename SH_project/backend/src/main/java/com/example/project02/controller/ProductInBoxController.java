package com.example.project02.controller;

import com.example.project02.DTO.ProductInBoxDTO;
import com.example.project02.service.ProductInBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productinbox")
public class ProductInBoxController {

    private final ProductInBoxService productInBoxService;

    @Autowired
    public ProductInBoxController(ProductInBoxService productInBoxService) {
        this.productInBoxService = productInBoxService;
    }

    @GetMapping
    public ResponseEntity<List<ProductInBoxDTO>> getAllProductInBoxes() {
        List<ProductInBoxDTO> productInBoxes = productInBoxService.getAllProductInBoxes();
        return new ResponseEntity<>(productInBoxes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductInBoxDTO> getProductInBoxById(@PathVariable Long id) {
        ProductInBoxDTO productInBox = productInBoxService.getProductInBoxById(id);
        if (productInBox != null) {
            return new ResponseEntity<>(productInBox, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductInBoxDTO> createProductInBox(@RequestBody ProductInBoxDTO productInBoxDTO) {
        ProductInBoxDTO createdProductInBox = productInBoxService.createProductInBox(productInBoxDTO);
        return new ResponseEntity<>(createdProductInBox, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductInBoxDTO> updateProductInBox(@PathVariable Long id, @RequestBody ProductInBoxDTO productInBoxDTO) {
        ProductInBoxDTO updatedProductInBox = productInBoxService.updateProductInBox(id, productInBoxDTO);
        if (updatedProductInBox != null) {
            return new ResponseEntity<>(updatedProductInBox, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductInBox(@PathVariable Long id) {
        boolean deleted = productInBoxService.deleteProductInBox(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
