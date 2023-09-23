package com.example.project02.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WareHouseDTO {
    private final Long id;
    private String name;
    private String location;
    private final int capacity;
    private String status;
    private List<BoxDTO> boxes;
}
