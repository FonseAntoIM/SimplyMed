package com.simplymed.client.util;

import com.simplymed.client.model.Medication;
import com.simplymed.client.model.Recipe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase de utilidad para la escritura de datos en formato CSV.
 * Proporciona un método estático para convertir una colección de objetos
 * {@link Recipe} en un archivo de texto con formato de valores separados por coma.
 * <p>
 * Esta clase es {@code final} y su constructor es privado para evitar su instanciación,
 * ya que está diseñada para ser utilizada únicamente a través de sus métodos estáticos.
 * </p>
 *
 * @author SimplyMed
 * @version 1.0
 * @since 2025-08-09
 */
public final class CsvUtil {

    /**
     * Constructor privado para evitar la instanciación de la clase de utilidad.
     */
    private CsvUtil() {}

    /**
     * Escribe una colección de objetos {@link Recipe} en un archivo CSV.
     * <p>
     * El método genera un archivo CSV con las columnas: {@code id,paciente,fecha,diagnostico,medicamentos}.
     * La lista de medicamentos de cada receta se aplana en una sola columna, donde cada medicamento
     * se representa como una cadena y los medicamentos están separados por un punto y coma ({@code ;}).
     * </p>
     *
     * @param file    El objeto {@link File} que representa el archivo de destino donde se escribirá el CSV.
     * @param recipes Un objeto {@link Iterable} que contiene la lista de {@link Recipe} a serializar.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    public static void writeCsv(File file, Iterable<Recipe> recipes) throws IOException {
        // El try-with-resources asegura que el PrintWriter y el FileWriter se cierren automáticamente.
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            // Escribe la cabecera del archivo CSV.
            pw.println("id,paciente,fecha,diagnostico,medicamentos");

            // Itera sobre cada objeto Recipe en la colección.
            for (Recipe r : recipes) {
                // Utiliza un Stream API de Java 8 para procesar la lista de medicamentos.
                String meds = r.getMedicamentos().stream()
                        // Mapea cada objeto Medication a una cadena de texto plana.
                        .map(CsvUtil::toFlat)
                        // Reduce la colección de cadenas en una sola, separando los elementos con un ";".
                        .reduce((a, b) -> a + ";" + b)
                        // Si la lista de medicamentos está vacía, se devuelve una cadena vacía.
                        .orElse("");

                // Formatea y escribe la línea completa del CSV, aplicando sanitización simple a los campos.
                pw.printf("%d,%s,%s,%s,%s%n",
                        r.getId(),
                        safe(r.getPaciente()),
                        r.getFecha(),
                        safe(r.getDiagnostico()),
                        safe(meds));
            }
        }
    }

    /**
     * Convierte un objeto {@link Medication} en una cadena de texto "plana"
     * con los campos separados por el carácter de barra vertical ({@code |}).
     * <p>
     * El formato de la cadena resultante es: {@code nombre|dosis|unidad|frecuencia}.
     * </p>
     *
     * @param m El objeto {@link Medication} a convertir.
     * @return Una cadena de texto que representa el medicamento.
     */
    private static String toFlat(Medication m) {
        // Manejo de valores nulos para la unidad, usando una cadena vacía en su lugar.
        String u = (m.getUnidad() == null) ? "" : m.getUnidad();
        return m.getNombre() + "|" + m.getDosis() + "|" + u + "|" + m.getFrecuencia();
    }

    /**
     * Realiza una sanitización simple de una cadena de texto para asegurar que
     * no contenga comas, las cuales podrían romper el formato CSV.
     * <p>
     * Si la cadena es nula, devuelve una cadena vacía. De lo contrario, reemplaza
     * todas las comas por espacios.
     * </p>
     *
     * @param s La cadena de texto a sanitizar.
     * @return La cadena sanitizada sin comas, o una cadena vacía si el input es nulo.
     */
    private static String safe(String s) {
        if (s == null) return "";
        return s.replace(",", " "); // Es una sanitización simple para CSV.
    }
}
