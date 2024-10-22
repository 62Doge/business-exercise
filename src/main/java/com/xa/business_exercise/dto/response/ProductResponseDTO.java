package com.xa.business_exercise.dto.response;

import com.xa.business_exercise.model.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ProductResponseDTO {
    private Category category;
    private String initial;
    private String name;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
}
