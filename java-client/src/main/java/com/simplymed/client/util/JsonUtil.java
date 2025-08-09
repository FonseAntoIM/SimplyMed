package com.simplymed.client.util;

import com.google.gson.*;
import com.simplymed.client.model.Recipe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase de utilidad para la serializacion y deserializacion JSON utilizando la libreria Gson.
 * Esta clase proporciona una instancia de Gson preconfigurada para manejar correctamente
 * los objetos {@link LocalDate} de Java 8, serializandolos y deserializandolos
 * en formato ISO 8601.
 * <p>
 * Se ha anadido un metodo de ayuda estatico para exportar una lista de objetos {@link Recipe}
 * a un archivo JSON con una estructura especifica.
 * </p>
 *
 * @author SimplyMed
 * @version 2.0
 * @since 2025-08-09
 */
public final class JsonUtil {

    /**
     * Constructor privado para evitar la instanciacion de la clase de utilidad.
     */
    private JsonUtil() {}

    /**
     * Formateador de fecha en formato ISO 8601 (yyyy-MM-dd).
     * Se utiliza para serializar y deserializar los objetos {@link LocalDate}.
     */
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * {@link JsonSerializer} que se encarga de convertir un objeto {@link LocalDate}
     * en una cadena de texto en formato ISO 8601 para su serializacion.
     */
    private static final JsonSerializer<LocalDate> LD_SER = (src, typeOfSrc, ctx) ->
            new JsonPrimitive(src.format(ISO));

    /**
     * {@link JsonDeserializer} que se encarga de convertir una cadena de texto en
     * un objeto {@link LocalDate}, utilizando el formato ISO 8601.
     */
    private static final JsonDeserializer<LocalDate> LD_DES = (json, typeOfT, ctx) ->
            LocalDate.parse(json.getAsString(), ISO);

    /**
     * Instancia estatica y final de {@link Gson} que se inicializa con el
     * {@link GsonBuilder} para registrar los adaptadores personalizados de {@link LocalDate}.
     * Se configura para imprimir JSON con formato legible (pretty printing).
     */
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, LD_SER)
            .registerTypeAdapter(LocalDate.class, LD_DES)
            .create();

    /**
     * Proporciona la instancia de {@link Gson} preconfigurada con los adaptadores
     * para {@link LocalDate}.
     *
     * @return Una instancia de {@link Gson} lista para ser utilizada en la serializacion
     * o deserializacion.
     */
    public static Gson gson() {
        return GSON;
    }

    /**
     * Escribe una lista de objetos {@link Recipe} en un archivo JSON.
     * <p>
     * El formato de salida es un objeto JSON que contiene la clave "recetas",
     * cuyo valor es un arreglo (array) de objetos de receta.
     * </p>
     *
     * @param file    El archivo de destino donde se escribira el JSON.
     * @param recipes La lista de objetos {@link Recipe} a serializar.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    public static void writeRecipesJson(File file, List<Recipe> recipes) throws IOException {
        // Nunca debe ser null; si por alguna razón lo fuera, escribimos array vacío
        if (recipes == null) {
            recipes = java.util.Collections.emptyList();
        }
        // Se utiliza un Map para crear el objeto raiz del JSON de manera mas fiable
        // y evitar el problema de serializacion que puede ocurrir con una clase interna.
        java.util.Map<String, List<Recipe>> root = new HashMap<>();
        root.put("recetas", recipes);
        try (Writer w = new FileWriter(file)) {
            // Serializa el objeto raiz a JSON con la instancia de Gson.
            gson().toJson(root, w); // { "recetas": [...] }
        }
    }
}
