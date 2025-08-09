package com.simplymed.client.model;

import java.io.Serializable;

/**
 * Clase de modelo de datos (POJO) que representa un medicamento.
 * <p>
 * Esta clase almacena la informacion basica de un medicamento, como su nombre,
 * dosis, frecuencia y unidad. Implementa la interfaz {@link Serializable} para
 * permitir que los objetos de esta clase puedan ser serializados y persistidos
 * en archivos, lo cual es utilizado por la clase {@link com.simplymed.client.util.IOUtil}.
 * </p>
 *
 * @author SimplyMed
 * @version 1.0
 * @since 2025-08-09
 * @see java.io.Serializable
 */
public class Medication implements Serializable {

    /**
     * El nombre comercial o generico del medicamento.
     */
    private String nombre;

    /**
     * La dosis del medicamento, representada como una cadena.
     */
    private String dosis;

    /**
     * La frecuencia con la que se debe administrar el medicamento.
     */
    private String frecuencia;

    /**
     * La unidad de medida de la dosis (por ejemplo: "mg", "ml", "tab").
     * Este campo es opcional y puede ser nulo.
     */
    private String unidad; // mg|ml|tab|cap (opcional)

    /**
     * Constructor por defecto. Crea una nueva instancia de Medication
     * con todos sus atributos inicializados en nulo.
     */
    public Medication() {}

    /**
     * Constructor que inicializa un objeto {@link Medication} con todos sus atributos.
     *
     * @param nombre El nombre del medicamento.
     * @param dosis La dosis del medicamento.
     * @param frecuencia La frecuencia de administracion.
     * @param unidad La unidad de medida de la dosis.
     */
    public Medication(String nombre, String dosis, String frecuencia, String unidad) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.unidad = unidad;
    }

    /**
     * Obtiene el nombre del medicamento.
     *
     * @return El nombre del medicamento como una cadena.
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre del medicamento.
     *
     * @param nombre El nombre del medicamento a establecer.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene la dosis del medicamento.
     *
     * @return La dosis del medicamento como una cadena.
     */
    public String getDosis() { return dosis; }

    /**
     * Establece la dosis del medicamento.
     *
     * @param dosis La dosis del medicamento a establecer.
     */
    public void setDosis(String dosis) { this.dosis = dosis; }

    /**
     * Obtiene la frecuencia de administracion del medicamento.
     *
     * @return La frecuencia como una cadena.
     */
    public String getFrecuencia() { return frecuencia; }

    /**
     * Establece la frecuencia de administracion del medicamento.
     *
     * @param frecuencia La frecuencia a establecer.
     */
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    /**
     * Obtiene la unidad de medida de la dosis del medicamento.
     *
     * @return La unidad como una cadena, o nulo si no se ha especificado.
     */
    public String getUnidad() { return unidad; }

    /**
     * Establece la unidad de medida de la dosis del medicamento.
     *
     * @param unidad La unidad a establecer.
     */
    public void setUnidad(String unidad) { this.unidad = unidad; }

    /**
     * Sobreescribe el metodo {@code toString()} para proporcionar una
     * representacion textual legible del objeto {@link Medication}.
     * El formato de la cadena resultante es: "nombre dosis [unidad] - frecuencia".
     *
     * @return Una cadena que representa el objeto Medication.
     */
    @Override public String toString() {
        return nombre + " " + dosis + (unidad != null ? " " + unidad : "") + " - " + frecuencia;
    }
}
