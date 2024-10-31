package com.xa.business_exercise.controller.rest;

import com.xa.business_exercise.dto.request.CategoryRequestDTO;
import com.xa.business_exercise.dto.response.CategoryResponseDTO;
import com.xa.business_exercise.model.Category;
import com.xa.business_exercise.payload.ApiResponse;
import com.xa.business_exercise.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> findAllCategories(){
        try {
            List<Category> categories = categoryService.findAll();
            List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();

            for (Category category : categories) {
                CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.convertToResponseDTO(category);
                categoryResponseDTOS.add(categoryResponseDTO);
            }

            ApiResponse<List<CategoryResponseDTO>> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), categoryResponseDTOS);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<CategoryResponseDTO>> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> findAllActiveCategories(){
        try {
            List<Category> categories = categoryService.findByActiveTrue();
            List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();

            for (Category category : categories) {
                CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.convertToResponseDTO(category);
                categoryResponseDTOS.add(categoryResponseDTO);
            }

            ApiResponse<List<CategoryResponseDTO>> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), categoryResponseDTOS);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<CategoryResponseDTO>> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> findCategoryById(@PathVariable("id") Long id){
        try {
            Optional<Category> category = categoryService.findById(id);

            if (category.isPresent()) {
                CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.convertToResponseDTO(category.get());
                ApiResponse<CategoryResponseDTO> successResponse =  new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), categoryResponseDTO);
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
            } else {
                ApiResponse<CategoryResponseDTO> notFoundResponse =  new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            ApiResponse<CategoryResponseDTO> errorResponse =  new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CategoryRequestDTO>> saveCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        try {
            Category category = new Category();
            category.setInitial(categoryRequestDTO.getInitial());
            category.setName(categoryRequestDTO.getName());
            category.setActive(categoryRequestDTO.getActive());

            Category savedCategory = categoryService.save(category);
            CategoryRequestDTO saveCategoryRequestDTO = CategoryRequestDTO.convertToRequestDTO(savedCategory);

            ApiResponse<CategoryRequestDTO> successResponse = new ApiResponse<>(HttpStatus.CREATED.value(), HttpStatus.OK.getReasonPhrase(), saveCategoryRequestDTO);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<CategoryRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CategoryRequestDTO>> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequestDTO categoryRequestDTO){
        Category category;
        try {
            Optional<Category> categoryOptional = categoryService.findById(id);

            if (categoryOptional.isPresent()) {
                category = categoryOptional.get();
                category.setInitial(categoryRequestDTO.getInitial());
                category.setName(categoryRequestDTO.getName());
                category.setActive(categoryRequestDTO.getActive());
            } else {
                ApiResponse<CategoryRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }

            Category savedCategory = categoryService.save(category);
            CategoryRequestDTO categoryRequestDTOSaved = CategoryRequestDTO.convertToRequestDTO(savedCategory);

            ApiResponse<CategoryRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), categoryRequestDTOSaved);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<CategoryRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<ApiResponse<CategoryRequestDTO>> softDelete(@PathVariable("id") Long id){
        try {
            Optional<Category> optionalCategory = categoryService.findById(id);

            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                category.setActive(false);

                Category savedCategory = categoryService.save(category);
                CategoryRequestDTO categoryResponseDTO = CategoryRequestDTO.convertToRequestDTO(savedCategory);

                ApiResponse<CategoryRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), categoryResponseDTO);
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
            } else {
                ApiResponse<CategoryRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            ApiResponse<CategoryRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<CategoryRequestDTO>> deleteCategory(@PathVariable("id") Long id){
        try {
            Optional<Category> categoryOptional = categoryService.findById(id);

            if (categoryOptional.isEmpty()) {
                ApiResponse<CategoryRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }

            categoryService.deleteById(id);

            ApiResponse<CategoryRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<CategoryRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}