package com.simplymed.service.impl;

import com.simplymed.domain.Medication;
import com.simplymed.domain.Recipe;
import com.simplymed.repository.RecipeRepository;
import com.simplymed.service.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository repo;

    public RecipeServiceImpl(RecipeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Recipe> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Recipe save(Recipe recipe) {
        if (recipe.getMedications() != null) {
            for (Medication m : recipe.getMedications()) {
                m.setRecipe(recipe);
            }
        }
        return repo.save(recipe);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Recipe> merge(List<Recipe> incoming) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe r : incoming) {
            Optional<Recipe> existingOpt = Optional.empty();
            if (r.getId() != null) {
                existingOpt = repo.findById(r.getId());
            }
            if (existingOpt.isEmpty()) {
                existingOpt = repo.findByPatientNameAndDoctorNameAndDate(
                        r.getPatientName(), r.getDoctorName(), r.getDate());
            }
            if (existingOpt.isPresent()) {
                Recipe existing = existingOpt.get();
                existing.setPatientName(r.getPatientName());
                existing.setDoctorName(r.getDoctorName());
                existing.setDate(r.getDate());
                existing.setNotes(r.getNotes());
                existing.getMedications().clear();
                if (r.getMedications() != null) {
                    for (Medication m : r.getMedications()) {
                        existing.addMedication(m);
                    }
                }
                result.add(repo.save(existing));
            } else {
                r.setId(null);
                result.add(save(r));
            }
        }
        return result;
    }
}
