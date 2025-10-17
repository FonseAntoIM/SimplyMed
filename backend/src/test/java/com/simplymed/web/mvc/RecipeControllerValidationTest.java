/* 
    Verifica que el formulario MVC regrese a recipe-form cuando faltan 
    campos requeridos (@Valid + BindingResult).
 */
package com.simplymed.web.mvc;

import com.simplymed.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = RecipeController.class)
class RecipeControllerValidationTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    RecipeService service; // no se llamará si hay errores de validación

    @Test
    void create_with_missing_required_fields_returns_form_view() throws Exception {
        // patientName y doctorName son @NotBlank, date es @NotNull
        mvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("patientName", "")
                .param("doctorName", "")
        // .param("date", "") // omitido para provocar error de @NotNull
        )
                .andExpect(status().isOk()) // vuelve a la misma vista con errores
                .andExpect(view().name("recipe-form"));
    }
}