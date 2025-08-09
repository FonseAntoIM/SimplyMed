package com.simplymed.client.util;

import com.simplymed.client.model.Recipe;

import java.io.*;
import java.util.List;

/**
 * Clase de utilidad para la persistencia de objetos mediante serializacion binaria.
 * <p>
 * Proporciona metodos estaticos para guardar y cargar listas de objetos {@link Recipe}
 * en archivos binarios. Esta utilidad es ideal para la persistencia simple de datos
 * en disco duro.
 * <p>
 * Para que esta clase funcione correctamente, la clase {@link Recipe} y todas las
 * clases que contiene (como {@link com.simplymed.client.model.Medication}) deben
 * implementar la interfaz {@link Serializable}.
 * </p>
 *
 * @author SimplyMed
 * @version 1.0
 * @since 2025-08-09
 */
public final class IOUtil {

    /**
     * Constructor privado para evitar la instanciacion de la clase de utilidad.
     */
    private IOUtil() {}

    /**
     * Guarda una lista de objetos {@link Recipe} en un archivo binario.
     * <p>
     * Este metodo utiliza {@link ObjectOutputStream} para serializar la lista de objetos
     * y escribirla en el archivo especificado. La lista de objetos completa se
     * guarda en una sola operacion.
     * </p>
     *
     * @param file El objeto {@link File} que representa el archivo de destino.
     * @param data La {@link List} de objetos {@link Recipe} que se guardaran.
     * @throws IOException Si ocurre un error de entrada/salida durante la escritura del archivo.
     */
    public static void saveBinary(File file, List<Recipe> data) throws IOException {
        // El try-with-resources asegura que los streams se cierren automaticamente.
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(data);
        }
    }

    /**
     * Carga una lista de objetos {@link Recipe} desde un archivo binario.
     * <p>
     * Este metodo utiliza {@link ObjectInputStream} para deserializar los datos
     * del archivo y reconstruir la lista de objetos.
     * </p>
     *
     * @param file El objeto {@link File} que representa el archivo de origen.
     * @return Una {@link List} de objetos {@link Recipe} cargados del archivo.
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura del archivo.
     * @throws ClassNotFoundException Si la clase de los objetos serializados no se encuentra en el classpath.
     */
    @SuppressWarnings("unchecked")
    public static List<Recipe> loadBinary(File file) throws IOException, ClassNotFoundException {
        // El try-with-resources asegura que los streams se cierren automaticamente.
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // Se lee el objeto y se hace un cast a List<Recipe>.
            return (List<Recipe>) ois.readObject();
        }
    }
}
