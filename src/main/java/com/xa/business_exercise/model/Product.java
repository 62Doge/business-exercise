package com.xa.business_exercise.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Product extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @JsonManagedReference
    private Category category;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(length = 10, nullable = false, unique = true)
    private String initial;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Variant> variants;

}
