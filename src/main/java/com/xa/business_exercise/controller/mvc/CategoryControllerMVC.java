package com.xa.business_exercise.controller.mvc;

import com.xa.business_exercise.model.Category;
import com.xa.business_exercise.repository.CategoryRepository;
import com.xa.business_exercise.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryControllerMVC {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public ModelAndView getCategory() {
        ModelAndView view = new ModelAndView("category/index");
        List<Category> categories = categoryRepository.findByActiveTrue();
        view.addObject("categories", categories);
        view.addObject("title", "Master Category");
        return view;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView view = new ModelAndView("category/form");
        view.addObject("category", new Category());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Category category, BindingResult result) {
        if (!result.hasErrors()) {
            categoryRepository.save(category);
        }
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("category/form");
        Category category = categoryRepository.findById(id).orElse(null);
        view.addObject("category", category);
        return view;
    }

    @GetMapping("/deleteForm/{id}")
    public ModelAndView deleteForm(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("category/deleteForm");
        Category category = categoryRepository.findById(id).orElse(null);
        view.addObject("category", category);
        return view;
    }

    @GetMapping("/soft-delete/{id}")
    public ModelAndView softDelete(@PathVariable("id") Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            categoryRepository.softDeleteById(category.getId());
        }
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable("id") Long id) {
        categoryRepository.deleteById(id);
        return new ModelAndView("redirect:/category");
    }


}
