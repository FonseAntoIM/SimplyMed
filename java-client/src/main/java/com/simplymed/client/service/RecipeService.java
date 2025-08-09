package com.simplymed.client.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simplymed.client.model.Medication;
import com.simplymed.client.model.Recipe;
import com.simplymed.client.util.CsvUtil;
import com.simplymed.client.util.IOUtil;
import com.simplymed.client.util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

/**
 * Clase de servicio que gestiona la logica de negocio relacionada con las recetas.
 * <p>
 * Este servicio actua como un repositorio en memoria para los objetos {@link Recipe}.
 * Proporciona metodos para manipular, importar, exportar y persistir las recetas
 * en diferentes formatos (JSON, CSV, binario), utilizando las clases de utilidad
 * de la aplicacion.
 * </p>
 *
 * @author SimplyMed
 * @version 2.0
 * @since 2025-08-09
 * @see com.simplymed.client.model.Recipe
 * @see com.simplymed.client.util.JsonUtil
 * @see com.simplymed.client.util.CsvUtil
 * @see com.simplymed.client.util.IOUtil
 */
public class RecipeService {

    /**
     * La lista interna de objetos {@link Recipe} que son gestionados por el servicio.
     * Esta lista actua como el "estado" de la aplicacion en memoria.
     */
    private final List<Recipe> data = new ArrayList<>();

    /**
     * Devuelve una vista inmodificable de la lista de recetas.
     * <p>
     * Esto protege la lista interna de modificaciones externas no deseadas,
     * garantizando que cualquier cambio de estado solo se realice a traves de los
     * metodos del servicio.
     * </p>
     *
     * @return Una {@link List} inmodificable de objetos {@link Recipe}.
     */
    public List<Recipe> list() {
        return Collections.unmodifiableList(data);
    }

    /**
     * Agrega todos los objetos de una coleccion a la lista de recetas.
     *
     * @param recipes Una {@link Collection} de objetos {@link Recipe} a anadir.
     */
    public void addAll(Collection<Recipe> recipes) {
        data.addAll(recipes);
    }

    /**
     * Fusiona una coleccion de recetas entrantes con la lista de recetas actual.
     * <p>
     * Este metodo utiliza el ID de la receta como clave para determinar si una
     * receta entrante ya existe en la lista.
     * <ul>
     * <li>Si una receta entrante tiene un ID que ya existe, la receta actual se reemplaza.</li>
     * <li>Si la receta entrante no tiene un ID existente, se anade a la lista.</li>
     * </ul>
     * La implementacion utiliza un {@link Map} temporal para optimizar la busqueda
     * por ID.
     * </p>
     *
     * @param incoming Una {@link Collection} de objetos {@link Recipe} a fusionar.
     */
    public void mergeById(Collection<Recipe> incoming) {
        Map<Long, Recipe> index = new HashMap<>();
        for (Recipe r : data) {
            index.put(r.getId(), r);
        }

        for (Recipe r : incoming) {
            if (index.containsKey(r.getId())) {
                index.put(r.getId(), r);
            } else {
                data.add(r);
                index.put(r.getId(), r);
            }
        }
    }

    /**
     * Importa recetas desde un archivo JSON con un formato especifico.
     * <p>
     * El archivo JSON debe tener una estructura con un objeto raiz que contenga
     * una clave "recetas" con un arreglo de objetos de receta. Este metodo
     * parsea el JSON y construye objetos {@link Recipe} y {@link Medication}.
     * </p>
     *
     * @param jsonFile La {@link Path} al archivo JSON a importar.
     * @return Una {@link List} de objetos {@link Recipe} importados.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    public List<Recipe> importFromJson(Path jsonFile) throws IOException {
        String json = new String(Files.readAllBytes(jsonFile), "UTF-8");
        JsonObject root = JsonUtil.gson().fromJson(json, JsonObject.class);
        JsonArray arr = root.getAsJsonArray("recetas");
        List<Recipe> imported = new ArrayList<>();

        for (JsonElement el : arr) {
            JsonObject o = el.getAsJsonObject();
            Recipe r = new Recipe();
            r.setId(o.get("id").getAsLong());
            r.setPaciente(o.get("paciente").getAsString());
            r.setFecha(LocalDate.parse(o.get("fecha").getAsString()));
            r.setDiagnostico(o.get("diagnostico").getAsString());

            List<Medication> meds = new ArrayList<>();
            for (JsonElement em : o.getAsJsonArray("medicamentos")) {
                JsonObject om = em.getAsJsonObject();
                Medication m = new Medication();
                m.setNombre(om.get("nombre").getAsString());
                m.setDosis(om.get("dosis").getAsString());
                if (om.has("unidad") && !om.get("unidad").isJsonNull()) {
                    m.setUnidad(om.get("unidad").getAsString());
                }
                m.setFrecuencia(om.get("frecuencia").getAsString());
                meds.add(m);
            }
            r.setMedicamentos(meds);
            imported.add(r);
        }
        return imported;
    }

    /**
     * Exporta la lista de recetas actual a un archivo JSON.
     * Este metodo delega la escritura del archivo al metodo de utilidad {@link JsonUtil#writeRecipesJson}.
     *
     * @param target El objeto {@link File} del archivo de destino.
     * @throws IOException Si ocurre un error de entrada/salida al escribir el archivo.
     */
    public void exportJson(File target) throws IOException {
        com.simplymed.client.util.JsonUtil.writeRecipesJson(target, data);
    }

    /**
     * Exporta la lista de recetas en memoria a un archivo CSV.
     * <p>
     * Este metodo utiliza la clase de utilidad {@link CsvUtil} para escribir
     * el archivo de manera formateada.
     * </p>
     *
     * @param target El objeto {@link File} del archivo de destino.
     * @throws IOException Si ocurre un error de entrada/salida al escribir el archivo.
     */
    public void exportCsv(File target) throws IOException {
        CsvUtil.writeCsv(target, data);
    }

    /**
     * Guarda el estado actual del servicio (la lista de recetas) en un archivo binario.
     * <p>
     * Utiliza la clase de utilidad {@link IOUtil} para serializar la lista de objetos.
     * </p>
     *
     * @param target El objeto {@link File} del archivo de destino.
     * @throws IOException Si ocurre un error de entrada/salida al escribir el archivo.
     */
    public void saveBinary(File target) throws IOException {
        IOUtil.saveBinary(target, data);
    }

    /**
     * Carga el estado del servicio desde un archivo binario si este existe.
     * <p>
     * Si el archivo no existe, el metodo no realiza ninguna operacion.
     * Si el archivo existe, carga los datos y reemplaza el estado actual.
     * Los posibles errores de carga se capturan y se imprimen en la consola,
     * pero no detienen la ejecucion del programa.
     * </p>
     *
     * @param source El objeto {@link File} del archivo de origen.
     */
    public void loadBinaryIfExists(File source) {
        if (!source.exists()) return;
        try {
            List<Recipe> loaded = IOUtil.loadBinary(source);
            data.clear();
            data.addAll(loaded);
        } catch (Exception e) {
            System.out.println("No se pudo cargar estado: " + e.getMessage());
        }
    }
}
