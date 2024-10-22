package com.xa.business_exercise.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryResponseDTO {
    private String initial;
    private String name;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;
}
