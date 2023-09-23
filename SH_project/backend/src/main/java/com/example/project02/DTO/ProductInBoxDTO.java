package com.example.project02.DTO;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ProductInBoxDTO {
    private final Long id;
    private Long boxId; // 상자의 ID
    private Long productId; // 상품의 ID
    private int quantity; // 박스 내의 상품 개수
    private int fquantity; // 박스 내의 불량품 개수

}