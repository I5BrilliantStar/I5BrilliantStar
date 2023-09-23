package com.example.project02.service;

import com.example.project02.DTO.BoxDTO;
import com.example.project02.DTO.ProductInBoxDTO;
import com.example.project02.entity.ProductInBox;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BoxService {
    List<BoxDTO> getAllBoxes();
    BoxDTO getBoxById(Long id);
    BoxDTO createBox(BoxDTO boxDTO);
    BoxDTO createBox2(Long id, BoxDTO boxDTO);
    BoxDTO updateBox(Long id, BoxDTO boxDTO);
    boolean deleteBox(Long id);
}
