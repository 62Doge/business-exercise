package com.xa.business_exercise.service;

import com.xa.business_exercise.model.OrderDetails;
import com.xa.business_exercise.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> findAll(){
        return orderDetailsRepository.findAll();
    }

    public Optional<OrderDetails> findById(Long id){
        return orderDetailsRepository.findById(id);
    }

    public List<OrderDetails> findByOrderHeaderId(Long id){
        return orderDetailsRepository.findByOrderHeadersId(id);
    }

    public OrderDetails save(OrderDetails orderDetails){
        return orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> saveAllOrders(List<OrderDetails> orderDetails){
        return orderDetailsRepository.saveAll(orderDetails);
    }

    public void deleteById(Long id){
        orderDetailsRepository.deleteById(id);
    }
}
