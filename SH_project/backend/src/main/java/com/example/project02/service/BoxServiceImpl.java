package com.example.project02.service;

import com.example.project02.DTO.BoxDTO;
import com.example.project02.DTO.ProductInBoxDTO;
import com.example.project02.entity.Box;
import com.example.project02.entity.Product;
import com.example.project02.entity.ProductInBox;
import com.example.project02.entity.WareHouse;
import com.example.project02.repository.BoxRepository;
import com.example.project02.repository.ProductInBoxRepository;
import com.example.project02.repository.ProductRepository;
import com.example.project02.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoxServiceImpl implements BoxService {
    private final BoxRepository boxRepository;
    private final WareHouseRepository wareHouseRepository;
    private final ProductInBoxRepository productInBoxRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BoxServiceImpl(BoxRepository boxRepository, WareHouseRepository wareHouseRepository, ProductInBoxRepository productInBoxRepository, ProductRepository productRepository) {
        this.boxRepository = boxRepository;
        this.wareHouseRepository = wareHouseRepository;
        this.productInBoxRepository = productInBoxRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<BoxDTO> getAllBoxes() {
        List<Box> boxes = boxRepository.findAll();
        return boxes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BoxDTO getBoxById(Long id) {
        Optional<Box> boxOptional = boxRepository.findById(id);
        if (boxOptional.isPresent()) {
            return convertToDTO(boxOptional.get());
        }
        return null;
    }

    @Override
    public BoxDTO createBox(BoxDTO boxDTO) {
        Box box = convertToEntity(boxDTO);
        box = boxRepository.save(box);
        return convertToDTO(box);
    }

    @Override
    public BoxDTO createBox2(Long id, BoxDTO boxDTO) {
        WareHouse wareHouse = wareHouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found"));

        // Warehouse의 Boxes 컬렉션을 순회하여 초기화
        wareHouse.getBoxes().size();

        Box box = convertToEntity(boxDTO);
        box.setWareHouse(wareHouse);
        box = boxRepository.save(box);
        return convertToDTO(box);
    }

    @Override
    public BoxDTO updateBox(Long id, BoxDTO boxDTO) {
        Optional<Box> boxOptional = boxRepository.findById(id);
        if (boxOptional.isPresent()) {
            Box existingBox = boxOptional.get();
            existingBox.setName(boxDTO.getName());
            existingBox.setSize(boxDTO.getSize());
            existingBox.setAiCheckStatus(boxDTO.isAiCheckStatus());
            existingBox.setAiCheckTime(boxDTO.getAiCheckTime());
            existingBox.setInspectionStatus(boxDTO.isInspectionStatus());
            existingBox.setInspectionTime(boxDTO.getInspectionTime());

            // You may need to update productInBoxes as well if needed.

            existingBox = boxRepository.save(existingBox);
            return convertToDTO(existingBox);
        }
        return null;
    }

    @Override
    public boolean deleteBox(Long id) {
        Optional<Box> boxOptional = boxRepository.findById(id);
        if (boxOptional.isPresent()) {
            boxRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private BoxDTO convertToDTO(Box box) {
        if (box == null) {
            return null;
        }

        return BoxDTO.builder()
                .id(box.getId())
                .name(box.getName())
                .size(box.getSize())
                .aiCheckStatus(box.isAiCheckStatus())
                .aiCheckTime(box.getAiCheckTime())
                .inspectionStatus(box.isInspectionStatus())
                .inspectionTime(box.getInspectionTime())
                .productInBoxes(convertProductInBoxListToDTOList(box.getProductInBoxes()))
                .wareHouse(box.getWareHouse())
                .build();
    }

    private List<ProductInBoxDTO> convertProductInBoxListToDTOList(List<ProductInBox> productInBoxes) {
        return productInBoxes.stream()
                .map(productInBox -> ProductInBoxDTO.builder()
                        .id(productInBox.getId())
                        .boxId(productInBox.getBox().getId())
                        .productId(productInBox.getProduct().getId())
                        .quantity(productInBox.getQuantity())
                        .fquantity(productInBox.getFquantity())
                        .build())
                .collect(Collectors.toList());
    }

    private Box convertToEntity(BoxDTO boxDTO) {
        Box box = new Box();
        box.setId(boxDTO.getId()); // Assuming id is generated by the database, this may not be necessary for create operations
        box.setName(boxDTO.getName());
        box.setSize(boxDTO.getSize());
        box.setAiCheckStatus(boxDTO.isAiCheckStatus());
        box.setAiCheckTime(boxDTO.getAiCheckTime());
        box.setInspectionStatus(boxDTO.isInspectionStatus());
        box.setInspectionTime(boxDTO.getInspectionTime());
        box.setWareHouse(boxDTO.getWareHouse());

        return box;
    }

    public Box findById(Long id) {
        // Use the BoxRepository to find a Box entity by its id
        Optional<Box> boxOptional = boxRepository.findById(id);

        if (boxOptional.isPresent()) {
            return boxOptional.get();
        } else {
            // Handle the case where no Box entity with the given id is found
            throw new EntityNotFoundException("Box with id " + id + " not found");
        }
    }
}