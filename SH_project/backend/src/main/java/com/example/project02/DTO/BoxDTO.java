package com.example.project02.DTO;

import com.example.project02.entity.WareHouse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoxDTO {
    private final Long id;
    private String name;
    private String size;
    private boolean aiCheckStatus;
    private String aiCheckTime;
    private boolean inspectionStatus;
    private String inspectionTime;
    private List<ProductInBoxDTO> productInBoxes; // Assuming ProductInBoxDTO exists
    private WareHouse wareHouse;
}
