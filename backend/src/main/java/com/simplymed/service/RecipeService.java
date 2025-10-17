package com.simplymed.service;

import com.simplymed.domain.Recipe;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> findAll();

    Optional<Recipe> findById(Long id);

    Recipe save(Recipe recipe);

    void delete(Long id);

    // Importa lista y hace merge por id (si id==null -> crea)
    List<Recipe> merge(List<Recipe> incoming);
}