package com.xa.business_exercise.dto.request;

import com.xa.business_exercise.model.Variant;
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

    public static VariantRequestDTO convertToRequestDTO(Variant variant) {
        VariantRequestDTO variantRequestDTO = new VariantRequestDTO();
        variantRequestDTO.setProductId(variant.getProductId());
        variantRequestDTO.setInitial(variant.getInitial());
        variantRequestDTO.setName(variant.getName());
        variantRequestDTO.setDescription(variant.getDescription());
        variantRequestDTO.setPrice(variant.getPrice());
        variantRequestDTO.setStock(variant.getStock());
        variantRequestDTO.setActive(variant.getActive());
        return variantRequestDTO;
    }
}
