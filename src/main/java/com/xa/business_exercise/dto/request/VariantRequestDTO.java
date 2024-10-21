package com.xa.business_exercise.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VariantRequestDTO {
    private Long productId;
    private String initial;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;
    private Boolean active;
}
