package com.xa.business_exercise.service;

import com.xa.business_exercise.model.OrderHeaders;
import com.xa.business_exercise.repository.OrderHeadersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderHeadersService {

    @Autowired
    private OrderHeadersRepository orderHeadersRepository;

    public List<OrderHeaders> findAll(){
        return orderHeadersRepository.findAll();
    }

    public Optional<OrderHeaders> findById(Long id){
        return orderHeadersRepository.findById(id);
    }

    public OrderHeaders save(OrderHeaders orderHeaders){
        return orderHeadersRepository.save(orderHeaders);
    }

    public void deleteById(Long id){
        orderHeadersRepository.deleteById(id);
    }
}
