package com.example.project02.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "boxes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Box extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private boolean  aiCheckStatus; // AI 검사 결과 - 정상 : True, 검수 필요 : False

    @Column(nullable = false)
    private String aiCheckTime; // AI 검사 시간

    private boolean  inspectionStatus; // 검수 상태

    private String inspectionTime; // 검수 시간


    @JsonManagedReference
    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductInBox> productInBoxes = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse wareHouse;




    // Add more fields and relationships as needed

}
