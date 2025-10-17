/*
    Intenta persistir entidades inválidas y confirma que se lanza una excepción
    de validación y no se guarda nada (rollback).
 */
package com.simplymed.service;

import com.simplymed.domain.Medication;
import com.simplymed.domain.Recipe;
import com.simplymed.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class TransactionRollbackTest {

    @Autowired
    RecipeService service;
    @Autowired
    RecipeRepository repo;

    @Test
    void saving_invalid_entity_triggers_validation_and_rolls_back() {
        long before = repo.count();

        Recipe r = new Recipe();
        r.setPatientName(""); // NotBlank -> inválido
        r.setDoctorName("Dra. Test");
        r.setDate(LocalDate.now());

        Medication m = new Medication();
        m.setName(""); // NotBlank -> inválido
        m.setDosage(null); // NotBlank -> inválido
        m.setFrequencyPerDay(null); // NotNull -> inválido
        r.addMedication(m);

        assertThatThrownBy(() -> service.save(r))
                .isInstanceOfAny(TransactionSystemException.class, jakarta.validation.ConstraintViolationException.class);

        assertThat(repo.count()).isEqualTo(before); // nada quedó persistido
    }
}