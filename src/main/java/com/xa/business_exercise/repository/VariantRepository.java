package com.xa.business_exercise.repository;

import com.xa.business_exercise.model.Variant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    List<Variant> findByActiveTrue();
    List<Variant> findByProductActiveTrue();
    List<Variant> findByProductCategoryActiveTrue();
    List<Variant> findByProductId(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Variant v SET v.active = false WHERE v.id = :id")
    void softDeleteById(@Param("id") Long id);

}
