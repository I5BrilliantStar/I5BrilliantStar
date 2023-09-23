package com.example.project02.controller;

import com.example.project02.DTO.BoxDTO;
import com.example.project02.DTO.ProductInBoxDTO;
import com.example.project02.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxController {

    private final BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping
    public ResponseEntity<List<BoxDTO>> getAllBoxes() {
        List<BoxDTO> boxes = boxService.getAllBoxes();
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoxDTO> getBoxById(@PathVariable Long id) {
        BoxDTO box = boxService.getBoxById(id);
        if (box != null) {
            return new ResponseEntity<>(box, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BoxDTO> createBox(@RequestBody BoxDTO boxDTO) {
        BoxDTO createdBox = boxService.createBox(boxDTO);
        return new ResponseEntity<>(createdBox, HttpStatus.CREATED);
    }

    @PostMapping("/warehouse={id}")
    public ResponseEntity<BoxDTO> createBox2(@PathVariable Long id,@RequestBody BoxDTO boxDTO) {
        BoxDTO createdBox = boxService.createBox2(id, boxDTO);
        return new ResponseEntity<>(createdBox, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoxDTO> updateBox(@PathVariable Long id, @RequestBody BoxDTO boxDTO) {
        BoxDTO updatedBox = boxService.updateBox(id, boxDTO);
        if (updatedBox != null) {
            return new ResponseEntity<>(updatedBox, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBox(@PathVariable Long id) {
        boolean deleted = boxService.deleteBox(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}