/*
 * Summary: API REST consumida por el frontend React para CRUD/import/export.
 * Interacts with: RecipeService, ModelMapper, GlobalExceptionHandler.
 * Rubric: Criterio 2/3/4/5 (MVC avanzado, UI conectada, JPA, estabilidad).
 */
package com.simplymed.web.rest;

import com.simplymed.domain.Recipe;
import com.simplymed.dto.RecipeDTO;
import com.simplymed.service.RecipeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /** Devuelve todas las recetas en formato DTO. */
    @GetMapping
    public List<RecipeDTO> all() {
        return service.findAll().stream().map(this::toDto).toList();
    }

    /** Busca una receta por id o lanza 404. */
    @GetMapping("/{id}")
    public RecipeDTO byId(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NoSuchElementException("Recipe " + id + " not found"));
    }

    /** Crea una receta nueva a partir del DTO. */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDTO create(@Valid @RequestBody RecipeDTO dto) {
        Recipe saved = service.save(toEntity(dto));
        return toDto(saved);
    }

    /** Actualiza una receta existente (PUT). */
    @PutMapping("/{id}")
    public RecipeDTO update(@PathVariable Long id, @Valid @RequestBody RecipeDTO dto) {
        service.findById(id).orElseThrow(() -> new NoSuchElementException("Recipe " + id + " not found"));
        Recipe entity = toEntity(dto);
        entity.setId(id);
        Recipe saved = service.save(entity);
        return toDto(saved);
    }

    /** Elimina la receta y retorna 204. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.findById(id).orElseThrow(() -> new NoSuchElementException("Recipe " + id + " not found"));
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** Envelope usado para import bulk (compatibilidad con front/CLI). */
    public static class RecipeEnvelope {
        @Valid
        public List<RecipeDTO> recipes;
    }

    /** Importa/mergea recetas masivamente. */
    @PostMapping("/import")
    public List<RecipeDTO> importAll(@Valid @RequestBody RecipeEnvelope env) {
        List<RecipeDTO> payload = env != null && env.recipes != null ? env.recipes : List.of();
        return toDtoList(service.merge(toEntityList(payload)));
    }

    /** Merge directo cuando se recibe solo un array de RecipeDTO. */
    @PostMapping("/merge")
    public List<RecipeDTO> merge(@Valid @RequestBody List<@Valid RecipeDTO> list) {
        return toDtoList(service.merge(toEntityList(list)));
    }

    /** Exporta todas las recetas en un mapa { "recipes": [...] }. */
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
