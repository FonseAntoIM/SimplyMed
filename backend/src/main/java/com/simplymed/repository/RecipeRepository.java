package com.simplymed.repository;

import com.simplymed.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByPatientNameAndDoctorNameAndDate(String patientName, String doctorName, LocalDate date);
}
