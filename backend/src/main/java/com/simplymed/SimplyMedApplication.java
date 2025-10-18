/*
 * Summary: Entry point for SimplyMed backend. Arranca Spring Boot y registra
 * todas las capas (config, web, service, repository, domain).
 * Interacts with: configuraciones en com.simplymed.config y controladores web.
 * Rubric: Criterio 5 (estabilidad) al proveer aplicación ejecutable.
 */
package com.simplymed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimplyMedApplication {
    /**
     * Método main que arranca el servidor embebido de Spring Boot.
     *
     * @param args argumentos de línea de comando propagados a SpringApplication
     */
    public static void main(String[] args) {
        SpringApplication.run(SimplyMedApplication.class, args);
    }
}
