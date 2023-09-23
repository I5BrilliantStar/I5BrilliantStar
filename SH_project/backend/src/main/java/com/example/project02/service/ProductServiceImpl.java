package com.example.project02.service;

import com.example.project02.DTO.ProductDTO;
import com.example.project02.entity.Box;
import com.example.project02.entity.Product;
import com.example.project02.entity.ProductInBox;
import com.example.project02.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDTO).orElse(null);
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        return product.map(this::convertToDTO).orElse(null);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        product = productRepository.save(product);
        return convertToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setCode(productDTO.getCode());
            product.setQuantity(productDTO.getQuantity());
            product.setFquantity(productDTO.getFquantity());

            product = productRepository.save(product);

            return convertToDTO(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .code(product.getCode())
                .quantity(product.getQuantity())
                .fquantity(product.getFquantity())
                .createdAt(product.getCreatedAt())
                .modifiedAt(product.getUpdatedAt())
                .build();
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCode(productDTO.getCode());
        product.setQuantity(productDTO.getQuantity());
        product.setFquantity(productDTO.getFquantity());
        return product;
    }

    public Product findById(Long id) {
        // Use the BoxRepository to find a Box entity by its id
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            // Handle the case where no Box entity with the given id is found
            throw new EntityNotFoundException("Box with id " + id + " not found");
        }
    }
}