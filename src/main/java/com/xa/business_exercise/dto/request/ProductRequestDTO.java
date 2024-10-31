package com.xa.business_exercise.dto.request;

import com.xa.business_exercise.model.Product;
import lombok.Data;

@Data
public class ProductRequestDTO {
    private Long categoryId;
    private String initial;
    private String name;
    private boolean active;

    public static ProductRequestDTO convertToRequestDTO(Product product) {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setCategoryId(product.getCategoryId());
        productRequestDTO.setInitial(product.getInitial());
        productRequestDTO.setName(product.getName());
        productRequestDTO.setActive(product.getActive());
        return productRequestDTO;
    }
}
