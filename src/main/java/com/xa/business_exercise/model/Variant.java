package com.xa.business_exercise.model;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Variant extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "product_id")
    private Long productId;

    @Column(length = 10, nullable = false, unique = true)
    private String initial;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(precision = 18, scale = 2)
    private BigDecimal price;

    @Column(precision = 18, scale = 2)
    private BigDecimal stock;

    @Column(nullable = false)
    private Boolean active;

}
