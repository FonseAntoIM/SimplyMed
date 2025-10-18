package com.simplymed.web.rest;

import com.simplymed.domain.Recipe;
import com.simplymed.dto.RecipeDTO;
import com.simplymed.service.RecipeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/recipes")
@Validated
public class RecipeRestController {
    private final RecipeService service;
    private final ModelMapper mapper;

    public RecipeRestController(RecipeService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<RecipeDTO> all() {
        return service.findAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public RecipeDTO byId(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NoSuchElementException("Recipe " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDTO create(@Valid @RequestBody RecipeDTO dto) {
        Recipe saved = service.save(toEntity(dto));
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public RecipeDTO update(@PathVariable Long id, @Valid @RequestBody RecipeDTO dto) {
        service.findById(id).orElseThrow(() -> new NoSuchElementException("Recipe " + id + " not found"));
        Recipe entity = toEntity(dto);
        entity.setId(id);
        Recipe saved = service.save(entity);
        return toDto(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.findById(id).orElseThrow(() -> new NoSuchElementException("Recipe " + id + " not found"));
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    public static class RecipeEnvelope {
        @Valid
        public List<RecipeDTO> recipes;
    }

    @PostMapping("/import")
    public List<RecipeDTO> importAll(@Valid @RequestBody RecipeEnvelope env) {
        List<RecipeDTO> payload = env != null && env.recipes != null ? env.recipes : List.of();
        return toDtoList(service.merge(toEntityList(payload)));
    }

    @PostMapping("/merge")
    public List<RecipeDTO> merge(@Valid @RequestBody List<@Valid RecipeDTO> list) {
        return toDtoList(service.merge(toEntityList(list)));
    }

    @GetMapping("/export")
    public Map<String, Object> exportAll() {
        return Map.of("recipes", all());
    }

    private RecipeDTO toDto(Recipe recipe) {
        return mapper.map(recipe, RecipeDTO.class);
    }

    private Recipe toEntity(RecipeDTO dto) {
        return mapper.map(dto, Recipe.class);
    }

    private List<Recipe> toEntityList(List<RecipeDTO> list) {
        if (list == null || list.isEmpty()) {
            return List.of();
        }
        return list.stream().map(this::toEntity).toList();
    }

    private List<RecipeDTO> toDtoList(List<Recipe> list) {
        if (list == null || list.isEmpty()) {
            return List.of();
        }
        return list.stream().map(this::toDto).toList();
    }
}
