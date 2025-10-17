package com.simplymed.repository;

import com.simplymed.domain.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RecipeRepositoryTest {
    @Autowired
    RecipeRepository repo;

    @Test
    void save_and_find() {
        Recipe r = new Recipe();
        r.setPatientName("A");
        r.setDoctorName("B");
        r.setDate(LocalDate.now());

        repo.save(r);
        assertThat(repo.findAll()).isNotEmpty();
    }
}