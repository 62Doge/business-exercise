package com.xa.business_exercise.dto.request;

import com.xa.business_exercise.model.OrderHeaders;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderHeadersRequestDTO {
    private String reference;
    private Double amount;

    public static OrderHeadersRequestDTO convertToRequestDTO(OrderHeaders orderHeaders) {
        OrderHeadersRequestDTO requestDTO = new OrderHeadersRequestDTO();
        requestDTO.setReference(orderHeaders.getReference());
        requestDTO.setAmount(orderHeaders.getAmount());

        return requestDTO;
    }
}
