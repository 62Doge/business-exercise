package com.xa.business_exercise.dto.response;

import com.xa.business_exercise.model.OrderDetails;
import com.xa.business_exercise.model.OrderHeaders;
import com.xa.business_exercise.model.Variant;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDetailsResponseDTO {
    private Long id;
    private OrderHeaders orderHeaders;
    private Variant variant;
    private Double quantity;
    private Double price;
    private Boolean Active;
    private Date createdAt;
    private Date updatedAt;

    public static OrderDetailsResponseDTO convertToResponseDTO(OrderDetails orderDetails) {
        OrderDetailsResponseDTO orderDetailsResponseDTO = new OrderDetailsResponseDTO();
        orderDetailsResponseDTO.setId(orderDetails.getId());
        orderDetailsResponseDTO.setOrderHeaders(orderDetails.getOrderHeaders());
        orderDetailsResponseDTO.setVariant(orderDetails.getVariant());
        orderDetailsResponseDTO.setQuantity(orderDetails.getQuantity());
        orderDetailsResponseDTO.setPrice(orderDetails.getPrice());
        orderDetailsResponseDTO.setActive(orderDetails.getActive());
        orderDetailsResponseDTO.setCreatedAt(orderDetails.getCreatedAt());
        orderDetailsResponseDTO.setUpdatedAt(orderDetails.getUpdatedAt());
        return orderDetailsResponseDTO;
    }

}
