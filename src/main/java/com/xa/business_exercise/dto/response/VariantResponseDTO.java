package com.xa.business_exercise.dto.response;

import com.xa.business_exercise.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VariantResponseDTO {
    private Product product;
    private String initial;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
