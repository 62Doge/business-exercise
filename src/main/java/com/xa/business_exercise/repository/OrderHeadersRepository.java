package com.xa.business_exercise.repository;

import com.xa.business_exercise.model.OrderHeaders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHeadersRepository extends JpaRepository<OrderHeaders, Long> {
}
