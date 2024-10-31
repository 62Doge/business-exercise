package com.xa.business_exercise.controller.rest;

import com.xa.business_exercise.dto.request.OrderDetailsRequestDTO;
import com.xa.business_exercise.dto.response.OrderDetailsResponseDTO;
import com.xa.business_exercise.model.OrderDetails;
import com.xa.business_exercise.payload.ApiResponse;
import com.xa.business_exercise.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-detail")
@CrossOrigin("*")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<OrderDetails>>> getOrderDetailsByOrderHeaderId(@PathVariable Long id) {
        try {
            List<OrderDetails> orderDetails = orderDetailsService.findByOrderHeaderId(id);

            ApiResponse<List<OrderDetails>> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), orderDetails);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<List<OrderDetails>> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDetails>> saveNewOrderDetails(@PathVariable Long id, @RequestBody OrderDetails orderDetails) {
        try {
            orderDetailsService.save(orderDetails);

            ApiResponse<OrderDetails> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), orderDetails);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<OrderDetails> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
