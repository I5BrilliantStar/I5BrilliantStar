package com.example.project02.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "warehouses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer capacity;

    private String status;

    // Add more fields and relationships as needed
    @JsonManagedReference
    @OneToMany(mappedBy = "wareHouse", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Box> Boxes = new ArrayList<>();
}
