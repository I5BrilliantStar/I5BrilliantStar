package com.example.project02.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "productInBoxes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInBox extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(columnDefinition = "integer default 0")
    private int quantity; // 박스 내의 상품 개수

    @Column(columnDefinition = "integer default 0")
    private int fquantity; // 박스 내의 불량품 개수

    // 생성자, getter 및 setter는 Lombok이 자동으로 생성합니다.

    // 추가적인 메서드나 필드를 필요에 따라 추가할 수 있습니다.
}
