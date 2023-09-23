package com.example.project02.service;

import com.example.project02.DTO.ProductDTO;
import com.example.project02.DTO.ProductInBoxDTO;
import com.example.project02.entity.Box;
import com.example.project02.entity.Product;
import com.example.project02.entity.ProductInBox;
import com.example.project02.repository.BoxRepository;
import com.example.project02.repository.ProductInBoxRepository;
import com.example.project02.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductInBoxServiceImpl implements ProductInBoxService {

    private final ProductInBoxRepository productInBoxRepository;
    private final BoxRepository boxRepository;
    private final ProductRepository productRepository;
    private final BoxServiceImpl boxService;
    private final ProductServiceImpl productService;

    @Autowired
    public ProductInBoxServiceImpl(ProductInBoxRepository productInBoxRepository, BoxRepository boxRepository, ProductRepository productRepository, BoxServiceImpl boxService, ProductServiceImpl productService) {
        this.productInBoxRepository = productInBoxRepository;
        this.boxRepository = boxRepository;
        this.productRepository = productRepository;
        this.boxService = boxService;
        this.productService = productService;
    }

    @Override
    public List<ProductInBoxDTO> getAllProductInBoxes() {
        List<ProductInBox> productInBoxes = productInBoxRepository.findAll();
        return productInBoxes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductInBoxDTO getProductInBoxById(Long id) {
        Optional<ProductInBox> productInBoxOptional = productInBoxRepository.findById(id);
        if (productInBoxOptional.isPresent()) {
            return convertToDTO(productInBoxOptional.get());
        }
        return null;
    }

    @Override
    public ProductInBoxDTO createProductInBox(ProductInBoxDTO productInBoxDTO) {
        ProductInBox productInBox = convertToEntity(productInBoxDTO);
        productInBox = productInBoxRepository.save(productInBox);
        // 해당 Product의 quantity 증가
        Long productId = productInBoxDTO.getProductId();
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setQuantity(product.getQuantity() + productInBoxDTO.getQuantity());
            product.setFquantity(product.getFquantity() + productInBoxDTO.getFquantity());
            productRepository.save(product);
        }
        return convertToDTO(productInBox);
    }

    @Override
    public ProductInBoxDTO updateProductInBox(Long id, ProductInBoxDTO productInBoxDTO) {
        Optional<ProductInBox> productInBoxOptional = productInBoxRepository.findById(id);
        if (productInBoxOptional.isPresent()) {
            ProductInBox existingProductInBox = productInBoxOptional.get();

            // 해당 Product의 quantity 변동
            Long productId = productInBoxDTO.getProductId();
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                product.setQuantity(product.getQuantity() + productInBoxDTO.getQuantity() - existingProductInBox.getQuantity());
                product.setFquantity(product.getFquantity() + productInBoxDTO.getFquantity() - existingProductInBox.getFquantity());
                productRepository.save(product);
            }

            // Update the fields of existingProductInBox with values from productInBoxDTO
            existingProductInBox.setBox(boxRepository.findById(productInBoxDTO.getBoxId()).orElse(null));
            existingProductInBox.setProduct(productRepository.findById(productInBoxDTO.getProductId()).orElse(null));
            existingProductInBox.setQuantity(productInBoxDTO.getQuantity());
            existingProductInBox.setFquantity(productInBoxDTO.getFquantity());

            // Save the updated ProductInBox entity
            existingProductInBox = productInBoxRepository.save(existingProductInBox);



            // Convert the updated entity back to DTO and return it
            return convertToDTO(existingProductInBox);
        }
        return null; // Handle the case where the entity with the given id is not found
    }

    @Override
    public boolean deleteProductInBox(Long id) {
        Optional<ProductInBox> productInBoxOptional = productInBoxRepository.findById(id);
        if (productInBoxOptional.isPresent()) {
            ProductInBox existingProductInBox = productInBoxOptional.get();
            Product product = productRepository.findById(id).orElse(null);
            if (product != null) {
                product.setQuantity(product.getQuantity() - existingProductInBox.getQuantity());
                product.setFquantity(product.getFquantity() - existingProductInBox.getFquantity());
                productRepository.save(product);
            }
            productInBoxRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductInBoxDTO convertToDTO(ProductInBox productInBox) {
        return ProductInBoxDTO.builder()
                .id(productInBox.getId())
                .boxId(productInBox.getBox().getId())
                .productId(productInBox.getProduct().getId())
                .quantity(productInBox.getQuantity())
                .fquantity(productInBox.getFquantity())
                .build();
    }


    public ProductInBox convertToEntity(ProductInBoxDTO productInBoxDTO) {
        ProductInBox productInBox = new ProductInBox();
        productInBox.setId(productInBoxDTO.getId());
        Box boxEntity = boxService.findById(productInBoxDTO.getBoxId());
        Product productEntity = productService.findById(productInBoxDTO.getProductId());
        productInBox.setBox(boxEntity);
        productInBox.setProduct(productEntity);
        return productInBox;
    }
}
