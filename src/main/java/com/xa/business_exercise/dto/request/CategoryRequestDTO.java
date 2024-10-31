package com.xa.business_exercise.dto.request;

import com.xa.business_exercise.model.Category;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    private String initial;
    private String name;
    private Boolean active;

    public static CategoryRequestDTO convertToRequestDTO(Category category) {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
        categoryRequestDTO.setInitial(category.getInitial());
        categoryRequestDTO.setName(category.getName());
        categoryRequestDTO.setActive(category.getActive());
        return categoryRequestDTO;
    }
}
