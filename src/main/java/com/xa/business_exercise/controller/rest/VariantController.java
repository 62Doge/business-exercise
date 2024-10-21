package com.xa.business_exercise.controller.rest;

import com.xa.business_exercise.dto.request.VariantRequestDTO;
import com.xa.business_exercise.dto.response.VariantResponseDTO;
import com.xa.business_exercise.model.Variant;
import com.xa.business_exercise.payload.ApiResponse;
import com.xa.business_exercise.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/variant")
public class VariantController {
    @Autowired
    private VariantService variantService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<VariantResponseDTO>>> findAllVariants() {
        try {
            List<Variant> variants = variantService.findByProductActiveTrue();
            List<VariantResponseDTO> variantResponseDTOS = new ArrayList<>();
            for (Variant variant : variants) {
                VariantResponseDTO variantResponseDTO = new VariantResponseDTO();
                variantResponseDTO.setProduct(variant.getProduct());
                variantResponseDTO.setInitial(variant.getInitial());
                variantResponseDTO.setName(variant.getName());
                variantResponseDTO.setDescription(variant.getDescription());
                variantResponseDTO.setPrice(variant.getPrice());
                variantResponseDTO.setStock(variant.getStock());
                variantResponseDTOS.add(variantResponseDTO);
            }
            ApiResponse<List<VariantResponseDTO>> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), variantResponseDTOS);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<List<VariantResponseDTO>> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<ApiResponse<VariantRequestDTO>> softDelete(@PathVariable("id") Long id){
        try {
            Optional<Variant> optionalVariant = variantService.findById(id);
            if (optionalVariant.isPresent()) {
                Variant variant = optionalVariant.get();
                variant.setActive(false);

                Variant savedVariant = variantService.save(variant);
                VariantRequestDTO variantRequestDTO = convertToDTO(savedVariant);

                ApiResponse<VariantRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), variantRequestDTO);
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
            } else {
                ApiResponse<VariantRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            ApiResponse<VariantRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<VariantRequestDTO>> saveVariant(@RequestBody VariantRequestDTO variantRequestDTO) {
        try {
            Variant variant = new Variant();
            variant.setProductId(variantRequestDTO.getProductId());
            variant.setInitial(variantRequestDTO.getInitial());
            variant.setName(variantRequestDTO.getName());
            variant.setDescription(variantRequestDTO.getDescription());
            variant.setPrice(variantRequestDTO.getPrice());
            variant.setStock(variantRequestDTO.getStock());
            variant.setActive(variantRequestDTO.getActive());

            Variant savedVariant = variantService.save(variant);
            VariantRequestDTO savedVariantRequestDTO = convertToDTO(savedVariant);

            ApiResponse<VariantRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), savedVariantRequestDTO);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<VariantRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<VariantRequestDTO>> updateVariant(@PathVariable("id") Long id, @RequestBody VariantRequestDTO variantRequestDTO) {
        Variant variant;
        try {
            Optional<Variant> optionalVariant = variantService.findById(id);

            if (optionalVariant.isPresent()) {
                variant = optionalVariant.get();
                variant.setProductId(variantRequestDTO.getProductId());
                variant.setInitial(variantRequestDTO.getInitial());
                variant.setName(variantRequestDTO.getName());
                variant.setDescription(variantRequestDTO.getDescription());
                variant.setPrice(variantRequestDTO.getPrice());
                variant.setStock(variantRequestDTO.getStock());
                variant.setActive(variantRequestDTO.getActive());
            } else {
                ApiResponse<VariantRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }

            Variant savedVariant = variantService.save(variant);
            VariantRequestDTO savedVariantRequestDTO = convertToDTO(savedVariant);

            ApiResponse<VariantRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), savedVariantRequestDTO);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }catch (Exception e) {
            ApiResponse<VariantRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<VariantRequestDTO>> deleteVariant(@PathVariable("id") Long id) {
        try {
            Optional<Variant> optionalVariant = variantService.findById(id);

            if (optionalVariant.isEmpty()) {
                ApiResponse<VariantRequestDTO> notFoundResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
                return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
            }

            variantService.deleteById(id);

            ApiResponse<VariantRequestDTO> successResponse = new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<VariantRequestDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private VariantRequestDTO convertToDTO(Variant variant) {
        VariantRequestDTO variantRequestDTO = new VariantRequestDTO();
        variantRequestDTO.setProductId(variant.getProductId());
        variantRequestDTO.setInitial(variant.getInitial());
        variantRequestDTO.setName(variant.getName());
        variantRequestDTO.setDescription(variant.getDescription());
        variantRequestDTO.setPrice(variant.getPrice());
        variantRequestDTO.setStock(variant.getStock());
        variantRequestDTO.setActive(variant.getActive());
        return variantRequestDTO;
    }
}
