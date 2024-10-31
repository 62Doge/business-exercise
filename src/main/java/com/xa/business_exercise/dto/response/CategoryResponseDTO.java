package com.xa.business_exercise.dto.response;

import com.xa.business_exercise.model.Category;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String initial;
    private String name;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;


    public static CategoryResponseDTO convertToResponseDTO(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setInitial(category.getInitial());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setActive(category.getActive());
        categoryResponseDTO.setCreatedAt(category.getCreatedAt());
        categoryResponseDTO.setUpdatedAt(category.getUpdatedAt());
        return categoryResponseDTO;
    }
}
