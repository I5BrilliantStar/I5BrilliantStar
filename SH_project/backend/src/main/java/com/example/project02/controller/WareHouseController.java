package com.example.project02.controller;

import com.example.project02.DTO.ProductDTO;
import com.example.project02.DTO.ProductInBoxDTO;
import com.example.project02.DTO.WareHouseDTO;
import com.example.project02.entity.WareHouse;
import com.example.project02.service.ProductInBoxService;
import com.example.project02.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/warehouse")
public class WareHouseController {

    private final WareHouseService wareHouseService;

    @Autowired
    public WareHouseController(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    @GetMapping
    public ResponseEntity<List<WareHouseDTO>> getAllWareHouses() {
        List<WareHouseDTO> wareHouses = wareHouseService.getAllWareHouses();
        return new ResponseEntity<>(wareHouses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WareHouseDTO> getWareHouseById(@PathVariable Long id) {
        WareHouseDTO wareHouse = wareHouseService.getWareHouseById(id);
        if (wareHouse != null) {
            return new ResponseEntity<>(wareHouse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name={name}")
    public ResponseEntity<WareHouseDTO> getWareHouseByName(@PathVariable String name) {
        WareHouseDTO wareHouse = wareHouseService.getWareHouseByName(name);
        if (wareHouse != null) {
            return new ResponseEntity<>(wareHouse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<WareHouseDTO> createWareHouse(@RequestBody WareHouseDTO wareHouseDTO) {
        WareHouseDTO createdWareHouse = wareHouseService.createWareHouse(wareHouseDTO);
        return new ResponseEntity<>(createdWareHouse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WareHouseDTO> updateProduct(@PathVariable Long id, @RequestBody WareHouseDTO wareHouseDTO) {
        WareHouseDTO updatedWareHouse = wareHouseService.updateWareHouse(id, wareHouseDTO);
        if (updatedWareHouse != null) {
            return new ResponseEntity<>(updatedWareHouse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWareHouse(@PathVariable Long id) {
        wareHouseService.deleteWareHouse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add more endpoints as needed
}
