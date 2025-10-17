// RecipeServiceMergeRegressionTest.java (@DataJpaTest + Service real)
/* 
Tipo: @DataJpaTest + @Import(RecipeServiceImpl) para usar servicio real con H2.
Casos:
    Actualiza receta existente y reemplaza medicamentos (regresión del merge).
    Crea receta nueva cuando id == null.
 */
package com.simplymed.service;

import com.simplymed.domain.Medication;
import com.simplymed.domain.Recipe;
import com.simplymed.repository.RecipeRepository;
import com.simplymed.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import java.time.LocalDate;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(RecipeServiceImpl.class)
class RecipeServiceMergeRegressionTest {


    @Autowired RecipeRepository repo;
    @Autowired RecipeService service;


    @Test
    void merge_updates_existing_recipe_and_replaces_medications() {
// Arrange: receta existente con 1 medicamento
        Recipe existing = new Recipe();
        existing.setPatientName("Juan");
        existing.setDoctorName("Dra. Ana");
        existing.setDate(LocalDate.now());
        Medication m1 = new Medication();
        m1.setName("Paracetamol"); m1.setDosage("500 mg"); m1.setFrequencyPerDay(2);
        existing.addMedication(m1);
        existing = repo.save(existing);


// Incoming: misma receta (por id) pero con medicamentos distintos
        Recipe incoming = new Recipe();
        incoming.setId(existing.getId());
        incoming.setPatientName("Juan");
        incoming.setDoctorName("Dra. Ana");
        incoming.setDate(existing.getDate());
        Medication m2 = new Medication();
        m2.setName("Ibuprofeno"); m2.setDosage("400 mg"); m2.setFrequencyPerDay(3);
        incoming.addMedication(m2);


// Act
        List<Recipe> merged = service.merge(List.of(incoming));


// Assert: hay 1 receta, con medicamentos reemplazados
        assertThat(merged).hasSize(1);
        Recipe reloaded = repo.findById(existing.getId()).orElseThrow();
        assertThat(reloaded.getMedications()).hasSize(1);
        assertThat(reloaded.getMedications().get(0).getName()).isEqualTo("Ibuprofeno");
    }


    @Test
    void merge_creates_new_recipe_when_id_is_null() {
        Recipe incoming = new Recipe();
        incoming.setPatientName("Maria");
        incoming.setDoctorName("Dr. Luis");
        incoming.setDate(LocalDate.now());
        Medication m = new Medication();
        m.setName("Amoxicilina"); m.setDosage("500 mg"); m.setFrequencyPerDay(3);
        incoming.addMedication(m);


        List<Recipe> merged = service.merge(List.of(incoming));


        assertThat(merged).hasSize(1);
        assertThat(merged.get(0).getId()).isNotNull();
        assertThat(repo.findAll()).hasSize(1);
    }
}