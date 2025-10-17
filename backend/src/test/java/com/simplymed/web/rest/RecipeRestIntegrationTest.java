//RecipeRestIntegrationTest.java (@SpringBootTest + TestRestTemplate)
/*
Tipo: @SpringBootTest(webEnvironment = RANDOM_PORT) con TestRestTemplate.
Flujo end-to-end:
    POST /api/recipes/import con envelope { "recipes": [...] }.
    GET /api/recipes/export y validación de estructura.
*/
package com.simplymed.web.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplymed.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeRestIntegrationTest {

    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate rest;
    @Autowired
    RecipeRepository repo;
    ObjectMapper mapper = new ObjectMapper();

    String url(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    void import_and_export_flow_works() throws Exception {
// Import (merge) usando envelope {"recipes": [...]}
        String body = "{" +
                "\"recipes\":[{" +
                "\"patientName\":\"Carla\",\"doctorName\":\"Dra. Ana\",\"date\":\"2025-10-16\",\"notes\":\"demo\",\"medications\":[{" +
                "\"name\":\"Paracetamol\",\"dosage\":\"500 mg\",\"frequencyPerDay\":2}]}]}";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> importResp = rest.exchange(url("/api/recipes/import"), HttpMethod.POST, new HttpEntity<>(body, headers), String.class);
        assertThat(importResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(repo.findAll()).isNotEmpty();


// Export y validar estructura {"recipes": [...]}
        ResponseEntity<String> exportResp = rest.getForEntity(url("/api/recipes/export"), String.class);
        assertThat(exportResp.getStatusCode()).isEqualTo(HttpStatus.OK);


        JsonNode root = mapper.readTree(exportResp.getBody());
        assertThat(root.has("recipes")).isTrue();
        assertThat(root.get("recipes").isArray()).isTrue();
        assertThat(root.get("recipes").size()).isGreaterThan(0);


// Sanity check: primer elemento tiene campos clave
        JsonNode first = root.get("recipes").get(0);
        assertThat(first.has("patientName")).isTrue();
        assertThat(first.has("doctorName")).isTrue();
        assertThat(first.has("medications")).isTrue();
    }
}