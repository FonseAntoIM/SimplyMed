/*
 * Summary: Entidad JPA hija que representa un medicamento dentro de una receta.
 * Interacts with: Recipe (ManyToOne), RecipeServiceImpl, ModelMapper.
 * Rubric: Criterio 4 (JPA avanzado).
 */
package com.simplymed.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String dosage;

    @NotNull
    private Integer frequencyPerDay;

    private String instructions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Integer getFrequencyPerDay() {
        return frequencyPerDay;
    }

    public void setFrequencyPerDay(Integer frequencyPerDay) {
        this.frequencyPerDay = frequencyPerDay;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
