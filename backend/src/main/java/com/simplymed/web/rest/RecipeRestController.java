package com.simplymed.web.rest;

import com.simplymed.domain.Recipe;
import com.simplymed.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeRestController {
    private final RecipeService service;

    public RecipeRestController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Recipe> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> byId(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe r) {
        return service.save(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable Long id, @RequestBody Recipe r) {
        return service.findById(id)
                .map(existing -> {
                    r.setId(id);
                    return ResponseEntity.ok(service.save(r));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- Import/merge masivo (JSON envelope {"recipes": [...]})
    static class RecipeEnvelope {
        public List<Recipe> recipes;
    }

    @PostMapping("/import")
    public List<Recipe> importAll(@RequestBody RecipeEnvelope env) {
        return service.merge(env != null && env.recipes != null ? env.recipes : List.of());
    }

    @PostMapping("/merge")
    public List<Recipe> merge(@RequestBody List<Recipe> list) {
        return service.merge(list);
    }

    // --- Export JSON en formato compatible con el front/CLI
    @GetMapping("/export")
    public Map<String, Object> exportAll() {
        return Map.of("recipes", service.findAll());
    }
}