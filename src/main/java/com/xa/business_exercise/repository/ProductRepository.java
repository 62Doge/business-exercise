package com.xa.business_exercise.repository;

import com.xa.business_exercise.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();
    List<Product> findByCategoryActiveTrue();
    List<Product> findByCategoryId(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.active = false WHERE p.id = :id")
    void softDeleteById(@Param("id") Long id);
}
