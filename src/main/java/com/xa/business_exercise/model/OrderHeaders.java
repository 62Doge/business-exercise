package com.xa.business_exercise.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class OrderHeaders extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //SLS-YYMM-####
    private String reference;

    private Double amount;

    @Column(nullable = false)
    private Boolean active = true;

}
