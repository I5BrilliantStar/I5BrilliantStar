package com.example.project02.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, unique = true)
    private String code;

    // 전체 상품 개수
    @Column(columnDefinition = "integer default 0")
    private int quantity;

    // 전체 불량품 개수
    @Column(columnDefinition = "integer default 0")
    private int fquantity;

    // Add more fields and relationships as needed
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<ProductInBox> productsInBox;
}