package com.xa.business_exercise.controller.mvc;

import com.xa.business_exercise.model.Category;
import com.xa.business_exercise.model.Product;
import com.xa.business_exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductControllerMVC {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public ModelAndView getProduct() {
        ModelAndView view = new ModelAndView("product/index");
        List<Product> products = productRepository.findByCategoryActiveTrue();
        view.addObject("products", products);
        view.addObject("title", "Master Product");
        return view;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView view = new ModelAndView("product/form");
        view.addObject("product", new Product());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Product product, BindingResult result) {
        if (!result.hasErrors()) {
            productRepository.save(product);
        }
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("product/form");
        Product product = productRepository.findById(id).orElse(null);
        view.addObject("product", product);
        return view;
    }

    @GetMapping("/deleteForm/{id}")
    public ModelAndView deleteForm(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("product/deleteForm");
        Product product = productRepository.findById(id).orElse(null);
        view.addObject("product", product);
        return view;
    }

    @GetMapping("/soft-delete/{id}")
    public ModelAndView softDelete(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            productRepository.softDeleteById(product.getId());
        }
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return new ModelAndView("redirect:/product");
    }
}
