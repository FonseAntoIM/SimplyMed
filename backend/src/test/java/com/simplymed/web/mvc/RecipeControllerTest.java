package com.simplymed.web.mvc;

import com.simplymed.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecipeController.class)
class RecipeControllerTest {
    @Autowired
    MockMvc mvc;

    @MockitoBean
    RecipeService service;

    @Test
    void list_endpoint_ok() throws Exception {
        mvc.perform(get("/recipes"))
                .andExpect(status().isOk());
    }
}