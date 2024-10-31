package com.xa.business_exercise.controller.rest;

import com.xa.business_exercise.dto.request.OrderHeadersRequestDTO;
import com.xa.business_exercise.dto.response.OrderDetailsResponseDTO;
import com.xa.business_exercise.dto.response.OrderHeadersResponseDTO;
import com.xa.business_exercise.model.OrderHeaders;
import com.xa.business_exercise.payload.ApiResponse;
import com.xa.business_exercise.service.OrderHeadersService;
import com.xa.business_exercise.utils.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order-headers")
@CrossOrigin("*")
public class OrderHeadersController {
    @Autowired
    private OrderHeadersService orderHeadersService;


    @GetMapping("")
    public ResponseEntity<ApiResponse<List<OrderHeadersResponseDTO>>> getOrderHeaders() {
        try {
            List<OrderHeaders> orderHeaders = orderHeadersService.findAll();
            List<OrderHeadersResponseDTO> orderHeadersResponseDTOS = new ArrayList<>();

            for (OrderHeaders orderHeader : orderHeaders) {
                OrderHeadersResponseDTO orderHeadersResponseDTO = OrderHeadersResponseDTO.convertToResponseDTO(orderHeader);
                orderHeadersResponseDTOS.add(orderHeadersResponseDTO);
            }

            ApiResponse<List<OrderHeadersResponseDTO>> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), orderHeadersResponseDTOS);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<List<OrderHeadersResponseDTO>> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/new-transaction")
    public ResponseEntity<ApiResponse<OrderHeadersRequestDTO>> saveOrderHeader() {
        try {
            OrderHeadersRequestDTO orderHeadersRequestDTO = new OrderHeadersRequestDTO();
            orderHeadersRequestDTO.setAmount(0.0);
            orderHeadersRequestDTO.setReference(String.valueOf(System.currentTimeMillis()));

            OrderHeaders orderHeaders = new OrderHeaders();
            orderHeaders.setAmount(orderHeadersRequestDTO.getAmount());
            orderHeaders.setReference(orderHeadersRequestDTO.getReference());

            orderHeadersService.save(orderHeaders);
            ApiResponse<OrderHeadersRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), orderHeadersRequestDTO);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<OrderHeadersRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderHeadersRequestDTO>> updateOrderHeader(@PathVariable("id") Long id, @RequestBody OrderHeadersRequestDTO orderHeadersRequestDTO) {
        OrderHeaders orderHeaders;
        try {
            Optional<OrderHeaders> optionalOrderHeaders = orderHeadersService.findById(id);

            if (optionalOrderHeaders.isPresent()) {
                orderHeaders = optionalOrderHeaders.get();
                orderHeaders.setAmount(orderHeadersRequestDTO.getAmount());
            } else {
                ApiResponse<OrderHeadersRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }

            OrderHeaders savedOrderHeaders = orderHeadersService.save(orderHeaders);
            OrderHeadersRequestDTO savedOrderHeadersRequestDTO = OrderHeadersRequestDTO.convertToRequestDTO(savedOrderHeaders);

            ApiResponse<OrderHeadersRequestDTO> successResponse =  new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), savedOrderHeadersRequestDTO);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<OrderHeadersRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
