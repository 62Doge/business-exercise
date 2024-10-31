package com.xa.business_exercise.repository;

import com.xa.business_exercise.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByActiveTrue();
    Category findById(long id);

    @Transactional
    @Modifying
    @Query("UPDATE Category c SET c.active = false WHERE c.id = :id")
    void softDeleteById(@Param("id") Long id);

}
