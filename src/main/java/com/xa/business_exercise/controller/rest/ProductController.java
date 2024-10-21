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

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> findAllProducts() {
        try {
            List<Product> products = productService.findByCategoryActiveTrue();
            List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
            for (Product product : products) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO();
                productResponseDTO.setCategory(product.getCategory());
                productResponseDTO.setInitial(product.getInitial());
                productResponseDTO.setName(product.getName());
                productResponseDTO.setActive(product.getActive());
                productResponseDTO.setCreatedAt(product.getCreatedAt());
                productResponseDTO.setUpdatedAt(product.getUpdatedAt());
                productResponseDTOS.add(productResponseDTO);
            }
            ApiResponse<List<ProductResponseDTO>> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productResponseDTOS );
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<List<ProductResponseDTO>> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
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
                ProductRequestDTO productRequestDTO = convertToDTO(savedProduct);

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

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<ProductRequestDTO>> saveProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        try {
            Product product = new Product();
            product.setCategoryId(productRequestDTO.getCategoryId());
            product.setInitial(productRequestDTO.getInitial());
            product.setName(productRequestDTO.getName());
            product.setActive(productRequestDTO.isActive());

            Product savedProduct = productService.save(product);
            ProductRequestDTO savedProductRequestDTO = convertToDTO(savedProduct);

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
            ProductRequestDTO savedProductRequestDTO = convertToDTO(savedProduct);

            ApiResponse<ProductRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), savedProductRequestDTO);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<ProductRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
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

    private ProductRequestDTO convertToDTO(Product product) {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setCategoryId(product.getCategoryId());
        productRequestDTO.setInitial(product.getInitial());
        productRequestDTO.setName(product.getName());
        productRequestDTO.setActive(product.getActive());
        return productRequestDTO;
    }


}
