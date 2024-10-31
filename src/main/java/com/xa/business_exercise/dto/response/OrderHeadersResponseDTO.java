package com.xa.business_exercise.dto.response;

import com.xa.business_exercise.model.OrderHeaders;
import lombok.Data;

import java.util.Date;

@Data
public class OrderHeadersResponseDTO {
    private Long id;
    private String reference;
    private Double amount;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;

    public static OrderHeadersResponseDTO convertToResponseDTO(OrderHeaders orderHeaders) {
        OrderHeadersResponseDTO orderHeadersResponseDTO = new OrderHeadersResponseDTO();
        orderHeadersResponseDTO.setId(orderHeaders.getId());
        orderHeadersResponseDTO.setReference(orderHeaders.getReference());
        orderHeadersResponseDTO.setAmount(orderHeaders.getAmount());
        orderHeadersResponseDTO.setActive(orderHeaders.getActive());
        orderHeadersResponseDTO.setCreatedAt(orderHeaders.getCreatedAt());
        orderHeadersResponseDTO.setUpdatedAt(orderHeaders.getUpdatedAt());
        return orderHeadersResponseDTO;
    }
}
