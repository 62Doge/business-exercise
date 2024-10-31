package com.xa.business_exercise.dto.response;

import com.xa.business_exercise.model.Category;
import com.xa.business_exercise.model.Product;
import lombok.Data;

import java.util.Date;

@Data
public class ProductResponseDTO {
    private Long id;
    private Category category;
    private String initial;
    private String name;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;

    public static ProductResponseDTO convertToResponseDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setCategory(product.getCategory());
        productResponseDTO.setInitial(product.getInitial());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setActive(product.getActive());
        productResponseDTO.setCreatedAt(product.getCreatedAt());
        productResponseDTO.setUpdatedAt(product.getUpdatedAt());
        return productResponseDTO;
    }
}
