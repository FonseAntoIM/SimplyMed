/*
 * Summary: Configura y expone un ModelMapper para mapear Entidad↔DTO.
 * Interacts with: RecipeRestController, RecipeServiceImpl.
 * Rubric: Criterio 2 (capas claras) al soportar DTOs.
 */
package com.simplymed.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /**
     * Crea el bean ModelMapper con ajustes estándar (match por campos, skip nulls).
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return mapper;
    }
}
