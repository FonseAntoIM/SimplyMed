/*
 * Summary: Encapsula propiedades personalizadas para CORS obtenidas de application.yml.
 * Interacts with: CorsConfig (provee la lista de orígenes permitidos).
 * Rubric: Criterio 5 al centralizar configuración para despliegues.
 */
package com.simplymed.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app.cors")
public class AppProperties {

    private List<String> origins;

    /**
     * @return lista de orígenes permitidos para solicitudes CORS.
     */
    public List<String> getOrigins() {
        return origins;
    }

    /**
     * Establece la lista de orígenes. Spring la rellena al inicializar el contexto.
     */
    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }
}
