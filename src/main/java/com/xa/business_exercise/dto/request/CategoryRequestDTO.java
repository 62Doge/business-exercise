package com.xa.business_exercise.dto.request;

import lombok.Data;

@Data
public class CategoryRequestDTO {
    private String initial;
    private String name;
    private Boolean active;
}
