package com.xa.business_exercise.controller.rest;

import com.xa.business_exercise.model.OrderDetails;
import com.xa.business_exercise.model.OrderHeaders;
import com.xa.business_exercise.service.OrderDetailsService;
import com.xa.business_exercise.service.OrderHeadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    OrderHeadersService orderHeadersService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @PostMapping("/{reference}")
    public ResponseEntity<?> saveNewOrder(@PathVariable Long reference, @RequestBody List<OrderDetails> orderDetails) {
        Double totalPrice = 0.0;
        OrderHeaders orderHeaders = new OrderHeaders();
        orderHeaders.setReference(String.valueOf(reference));
        orderHeaders.setAmount(0.0);
        OrderHeaders savedOrderHeaders = orderHeadersService.save(orderHeaders);

        for (OrderDetails orderDetail : orderDetails) {
            orderDetail.setHeadersId(savedOrderHeaders.getId());
            totalPrice += orderDetail.getPrice();
            orderHeaders.setAmount(totalPrice);
        }
        orderDetailsService.saveAllOrders(orderDetails);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

}
