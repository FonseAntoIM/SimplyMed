/*
 * Summary: Interfaz de la capa de servicio para operar recetas.
 */
package com.simplymed.service;

import com.simplymed.domain.Recipe;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> findAll();
    Optional<Recipe> findById(Long id);
    Recipe save(Recipe recipe);
    void delete(Long id);
    List<Recipe> merge(List<Recipe> incoming);
}
