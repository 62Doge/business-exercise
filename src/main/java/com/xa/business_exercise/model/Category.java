package com.xa.business_exercise.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Category extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, unique = true, nullable = false)
    private String initial;

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean active = true;

//    @OneToMany(mappedBy ="category", cascade = CascadeType.ALL)
//    @JsonBackReference
//    private List<Product> products;

}
