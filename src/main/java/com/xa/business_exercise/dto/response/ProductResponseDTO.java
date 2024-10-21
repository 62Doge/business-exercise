package com.xa.business_exercise.dto.response;

import com.xa.business_exercise.model.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {
    private Category category;
    private String initial;
    private String name;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
