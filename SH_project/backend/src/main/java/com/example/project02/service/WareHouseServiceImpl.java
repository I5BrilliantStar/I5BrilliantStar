package com.example.project02.service;

import com.example.project02.DTO.BoxDTO;
import com.example.project02.DTO.ProductDTO;
import com.example.project02.DTO.ProductInBoxDTO;
import com.example.project02.DTO.WareHouseDTO;
import com.example.project02.entity.Box;
import com.example.project02.entity.Product;
import com.example.project02.entity.ProductInBox;
import com.example.project02.entity.WareHouse;
import com.example.project02.repository.ProductRepository;
import com.example.project02.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WareHouseServiceImpl implements WareHouseService{
    private final WareHouseRepository wareHouseRepository;

    @Autowired
    public WareHouseServiceImpl(WareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }

    @Override
    public List<WareHouseDTO> getAllWareHouses() {
        return wareHouseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WareHouseDTO getWareHouseById(Long id) {
        Optional<WareHouse> wareHouse = wareHouseRepository.findById(id);
        return wareHouse.map(this::convertToDTO).orElse(null);
    }

    @Override
    public WareHouseDTO getWareHouseByName(String name) {
        Optional<WareHouse> wareHouse = wareHouseRepository.findByName(name);
        return wareHouse.map(this::convertToDTO).orElse(null);
    }

    @Override
    public WareHouseDTO createWareHouse(WareHouseDTO wareHouseDTO) {
        WareHouse wareHouse = convertToEntity(wareHouseDTO);
        wareHouse = wareHouseRepository.save(wareHouse);
        return convertToDTO(wareHouse);
    }

    @Override
    public WareHouseDTO updateWareHouse(Long id, WareHouseDTO wareHouseDTO) {
        Optional<WareHouse> wareHouseOptional = wareHouseRepository.findById(id);
        if (wareHouseOptional.isPresent()) {
            WareHouse wareHouse = wareHouseOptional.get();

            wareHouse.setName(wareHouseDTO.getName());
            wareHouse.setLocation(wareHouseDTO.getLocation());
            wareHouse.setStatus(wareHouseDTO.getStatus());
            wareHouse.setCapacity(wareHouse.getCapacity());

            wareHouse = wareHouseRepository.save(wareHouse);

            return convertToDTO(wareHouse);
        }
        return null;
    }

    public void deleteWareHouse(Long wareHouseId) {
        if (wareHouseRepository.existsById(wareHouseId)) {
            wareHouseRepository.deleteById(wareHouseId);
        }
    }

    // Add more service methods as needed
    private WareHouseDTO convertToDTO(WareHouse wareHouse) {
        return WareHouseDTO.builder()
                .id(wareHouse.getId())
                .name(wareHouse.getName())
                .location(wareHouse.getLocation())
                .capacity(wareHouse.getCapacity())
                .status(wareHouse.getStatus())
                .boxes(convertBoxListToDTOList(wareHouse.getBoxes()))
                .build();
    }
    private List<BoxDTO> convertBoxListToDTOList(List<Box> Boxes) {
        return Boxes.stream()
                .map(Box -> BoxDTO.builder()
                        .id(Box.getId())
                        .name(Box.getName())
                        .size(Box.getSize())
                        .aiCheckTime(Box.getAiCheckTime())
                        .aiCheckStatus(Box.isAiCheckStatus())
                        .inspectionTime(Box.getInspectionTime())
                        .inspectionStatus(Box.isInspectionStatus())
                        .build())
                .collect(Collectors.toList());
    }

    private WareHouse convertToEntity(WareHouseDTO wareHouseDTO) {
        WareHouse wareHouse = new WareHouse();
        wareHouse.setId(wareHouseDTO.getId());
        wareHouse.setName(wareHouseDTO.getName());
        wareHouse.setLocation(wareHouseDTO.getLocation());
        wareHouse.setCapacity(wareHouseDTO.getCapacity());
        wareHouse.setStatus(wareHouseDTO.getStatus());
        return wareHouse;
    }
}
