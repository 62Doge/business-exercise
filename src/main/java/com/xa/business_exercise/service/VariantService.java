package com.xa.business_exercise.service;

import com.xa.business_exercise.model.Category;
import com.xa.business_exercise.model.Variant;
import com.xa.business_exercise.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepository;

    public List<Variant> findAll(){
        return variantRepository.findAll();
    }

    public List<Variant> findByActiveTrue(){
        return variantRepository.findByActiveTrue();
    }

    public List<Variant> findByProductActiveTrue(){
        return variantRepository.findByProductActiveTrue();
    }

    public Optional<Variant> findById(Long id){
        return variantRepository.findById(id);
    }

    public Variant save(Variant variant){
        return variantRepository.save(variant);
    }

    public void deleteById(Long id){
        variantRepository.deleteById(id);
    }
}
