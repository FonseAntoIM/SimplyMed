// MVC — RecipeController.java (Thymeleaf)
package com.simplymed.web.mvc;

import com.simplymed.domain.Recipe;
import com.simplymed.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("recipes", service.findAll());
        return "recipe-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe-form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult br) {
        if (br.hasErrors())
            return "recipe-form";
        service.save(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/recipes";
    }
}