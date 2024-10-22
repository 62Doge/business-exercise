package com.xa.business_exercise.controller.mvc;

import com.xa.business_exercise.model.Category;
import com.xa.business_exercise.model.Product;
import com.xa.business_exercise.model.Variant;
import com.xa.business_exercise.repository.CategoryRepository;
import com.xa.business_exercise.repository.ProductRepository;
import com.xa.business_exercise.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/variant")
public class VariantControllerMVC {
    @Autowired
    private VariantRepository variantRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public ModelAndView getVariant() {
        ModelAndView view = new ModelAndView("variant/index");
        List<Variant> variants = variantRepository.findAll();
        view.addObject("variants", variants);
        view.addObject("title", "Master Variant");
        return view;
    }

    @GetMapping("/product-category/{id}")
    @ResponseBody
    public List<Product> getProductCategory(@PathVariable Long id) {
        return productRepository.findByCategoryId(id);
    }

    @GetMapping("/product/{id}")
    public List<Variant> getProduct(@PathVariable Long id){
        return variantRepository.findByProductId(id);
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView view = new ModelAndView("variant/form");
        List<Category> categories = categoryRepository.findByActiveTrue();
        view.addObject("variant", new Variant());
        view.addObject("categories", categories);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Variant variant, BindingResult result) {
        if (!result.hasErrors()) {
            variantRepository.save(variant);
        }
        return new ModelAndView("redirect:/variant");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("variant/form");
        Variant variant = variantRepository.findById(id).orElse(null);
        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        Long categoryId = null;
        if (variant != null && variant.getProduct() != null) {
            categoryId = variant.getProduct().getCategory().getId();  // Get Category ID from Product
        }

        view.addObject("variant", variant);
        view.addObject("products", products);
        view.addObject("categoryId", categoryId);
        view.addObject("categories", categories);
        return view;
    }

    @GetMapping("/deleteForm/{id}")
    public ModelAndView deleteForm(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("variant/deleteForm");
        Variant variant = variantRepository.findById(id).orElse(null);
        view.addObject("variant", variant);
        return view;
    }

    @GetMapping("/soft-delete/{id}")
    public ModelAndView softDelete(@PathVariable("id") Long id) {
        Variant variant = variantRepository.findById(id).orElse(null);

        if (variant != null) {
            variantRepository.softDeleteById(variant.getId());
        }
        return new ModelAndView("redirect:/variant");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable("id") Long id) {
        variantRepository.deleteById(id);
        return new ModelAndView("redirect:/variant");
    }
}
