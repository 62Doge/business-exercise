package com.xa.business_exercise.controller.rest;

import com.xa.business_exercise.dto.request.ProductRequestDTO;
import com.xa.business_exercise.dto.response.ProductResponseDTO;
import com.xa.business_exercise.model.Product;
import com.xa.business_exercise.payload.ApiResponse;
import com.xa.business_exercise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> findAllProducts() {
        try {
            List<Product> products = productService.findAll();
            List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

            for (Product product : products) {
                ProductResponseDTO productResponseDTO = ProductResponseDTO.convertToResponseDTO(product);
                productResponseDTOS.add(productResponseDTO);
            }

            ApiResponse<List<ProductResponseDTO>> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productResponseDTOS);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<List<ProductResponseDTO>> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> findProductsByCategory(@PathVariable Long categoryId) {
        try {
            List<Product> products = productService.findByCategoryId(categoryId);
            List<ProductResponseDTO> productResponseDTOS = products.stream()
                    .map(ProductResponseDTO::convertToResponseDTO)
                    .collect(Collectors.toList());

            ApiResponse<List<ProductResponseDTO>> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productResponseDTOS);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<ProductResponseDTO>> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> findProductById(@PathVariable("id") Long id){
        try {
            Optional<Product> product = productService.findById(id);

            if (product.isPresent()) {
                ProductResponseDTO productResponseDTO = ProductResponseDTO.convertToResponseDTO(product.get());
                ApiResponse<ProductResponseDTO> successResponse =  new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productResponseDTO);
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
            } else {
                ApiResponse<ProductResponseDTO> notFoundResponse =  new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            ApiResponse<ProductResponseDTO> errorResponse =  new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<ProductRequestDTO>> saveProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        try {
            Product product = new Product();
            product.setCategoryId(productRequestDTO.getCategoryId());
            product.setInitial(productRequestDTO.getInitial());
            product.setName(productRequestDTO.getName());
            product.setActive(productRequestDTO.isActive());

            Product savedProduct = productService.save(product);
            ProductRequestDTO savedProductRequestDTO = ProductRequestDTO.convertToRequestDTO(savedProduct);

            ApiResponse<ProductRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), savedProductRequestDTO);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<ProductRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ProductRequestDTO>> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        Product product;
        try {
            Optional<Product> optionalProduct = productService.findById(id);

            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
                product.setCategoryId(productRequestDTO.getCategoryId());
                product.setInitial(productRequestDTO.getInitial());
                product.setName(productRequestDTO.getName());
                product.setActive(productRequestDTO.isActive());
            } else {
                ApiResponse<ProductRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }

            Product savedProduct = productService.save(product);
            ProductRequestDTO savedProductRequestDTO = ProductRequestDTO.convertToRequestDTO(savedProduct);

            ApiResponse<ProductRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), savedProductRequestDTO);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<ProductRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<ApiResponse<ProductRequestDTO>> softDelete(@PathVariable("id") Long id){
        try {
            Optional<Product> optionalProduct = productService.findById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setActive(false);

                Product savedProduct = productService.save(product);
                ProductRequestDTO productRequestDTO = ProductRequestDTO.convertToRequestDTO(savedProduct);

                ApiResponse<ProductRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productRequestDTO);
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
            } else {
                ApiResponse<ProductRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            ApiResponse<ProductRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<ProductRequestDTO>> deleteProduct(@PathVariable("id") Long id) {
        try {
            Optional<Product> optionalProduct = productService.findById(id);

            if (optionalProduct.isEmpty()) {
                ApiResponse<ProductRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }

            productService.deleteById(id);

            ApiResponse<ProductRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<ProductRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
