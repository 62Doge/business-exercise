package com.xa.business_exercise.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class OrderDetails extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "headers_id")
    private Long headersId;

    @ManyToOne
    @JoinColumn(name = "headers_id", insertable = false, updatable = false)
    private OrderHeaders orderHeaders;

    @Column(name = "variant_id")
    private Long variantId;

    @ManyToOne
    @JoinColumn(name = "variant_id", insertable = false, updatable = false)
    private Variant variant;

    private Double quantity;

    private Double price;

    @Column(nullable = false)
    private Boolean active = true;

}
