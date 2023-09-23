package com.example.project02.service;

import com.example.project02.DTO.ProductDTO;
import com.example.project02.DTO.WareHouseDTO;

import java.util.List;

public interface WareHouseService {
    List<WareHouseDTO> getAllWareHouses();
    WareHouseDTO getWareHouseById(Long id);
    WareHouseDTO getWareHouseByName(String name);
    WareHouseDTO createWareHouse(WareHouseDTO wareHouseDTO);
    WareHouseDTO updateWareHouse(Long id, WareHouseDTO wareHouseDTO);
    void deleteWareHouse(Long id);
}
