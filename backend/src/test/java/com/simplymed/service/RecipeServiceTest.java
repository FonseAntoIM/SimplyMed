package com.simplymed.service;

import com.simplymed.domain.Recipe;
import com.simplymed.repository.RecipeRepository;
import com.simplymed.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

public class RecipeServiceTest {
    RecipeRepository repo = Mockito.mock(RecipeRepository.class);
    RecipeService service;

    @BeforeEach
    void setup() {
        service = new RecipeServiceImpl(repo);
        Mockito.when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));
    }

    @Test
    void save_returns_entity() {
        Recipe r = new Recipe();
        r.setPatientName("Juan");
        r.setDoctorName("Dra. Ana");
        assertThat(service.save(r)).isNotNull();
    }
}