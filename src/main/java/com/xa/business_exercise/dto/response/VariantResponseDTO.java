package com.xa.business_exercise.dto.response;

import com.xa.business_exercise.dto.request.VariantRequestDTO;
import com.xa.business_exercise.model.Product;
import com.xa.business_exercise.model.Variant;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VariantResponseDTO {
    private Long id;
    private Product product;
    private String initial;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;

    public static VariantResponseDTO convertToResponseDTO(Variant variant) {
        VariantResponseDTO variantResponseDTO = new VariantResponseDTO();
        variantResponseDTO.setId(variant.getId());
        variantResponseDTO.setProduct(variant.getProduct());
        variantResponseDTO.setInitial(variant.getInitial());
        variantResponseDTO.setName(variant.getName());
        variantResponseDTO.setDescription(variant.getDescription());
        variantResponseDTO.setPrice(variant.getPrice());
        variantResponseDTO.setStock(variant.getStock());
        variantResponseDTO.setActive(variant.getActive());
        return variantResponseDTO;
    }

}
