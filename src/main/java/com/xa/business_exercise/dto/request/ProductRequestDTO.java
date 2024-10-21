package com.xa.business_exercise.dto.request;

import com.xa.business_exercise.model.Category;
import lombok.Data;

@Data
public class ProductRequestDTO {
    private Long categoryId;
    private String initial;
    private String name;
    private boolean active;
}
