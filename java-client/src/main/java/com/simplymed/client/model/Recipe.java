package com.simplymed.client.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de modelo de datos (POJO) que representa una receta medica.
 * <p>
 * Esta clase almacena la informacion completa de una receta, incluyendo
 * su identificador, los datos del paciente, la fecha de emision, el diagnostico
 * y una lista de medicamentos recetados.
 * </p>
 * <p>
 * Implementa {@link Serializable} para permitir la persistencia de sus objetos,
 * y {@link Comparable} para que los objetos {@link Recipe} puedan ser ordenados
 * de forma natural. La implementacion de {@code compareTo} ordena las recetas
 * por su fecha.
 * </p>
 *
 * @author SimplyMed
 * @version 1.0
 * @since 2025-08-09
 * @see java.io.Serializable
 * @see java.lang.Comparable
 * @see com.simplymed.client.util.IOUtil
 */
public class Recipe implements Serializable, Comparable<Recipe> {

    /**
     * El identificador unico de la receta.
     */
    private long id;

    /**
     * El nombre completo del paciente.
     */
    private String paciente;

    /**
     * La fecha de emision de la receta.
     */
    private LocalDate fecha;

    /**
     * El diagnostico del paciente.
     */
    private String diagnostico;

    /**
     * Una lista de objetos {@link Medication} que corresponden a los
     * medicamentos recetados. Se inicializa como una lista vacia para
     * evitar {@link NullPointerException}.
     */
    private List<Medication> medicamentos = new ArrayList<>();

    /**
     * Constructor por defecto. Crea una nueva instancia de Recipe.
     */
    public Recipe() {}

    /**
     * Obtiene el identificador unico de la receta.
     *
     * @return El ID de la receta como un valor {@code long}.
     */
    public long getId() { return id; }

    /**
     * Establece el identificador unico de la receta.
     *
     * @param id El ID a establecer.
     */
    public void setId(long id) { this.id = id; }

    /**
     * Obtiene el nombre del paciente.
     *
     * @return El nombre del paciente como una cadena.
     */
    public String getPaciente() { return paciente; }

    /**
     * Establece el nombre del paciente.
     *
     * @param paciente El nombre del paciente a establecer.
     */
    public void setPaciente(String paciente) { this.paciente = paciente; }

    /**
     * Obtiene la fecha de emision de la receta.
     *
     * @return La fecha de la receta como un objeto {@link LocalDate}.
     */
    public LocalDate getFecha() { return fecha; }

    /**
     * Establece la fecha de emision de la receta.
     *
     * @param fecha La fecha a establecer.
     */
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    /**
     * Obtiene el diagnostico del paciente.
     *
     * @return El diagnostico como una cadena.
     */
    public String getDiagnostico() { return diagnostico; }

    /**
     * Establece el diagnostico del paciente.
     *
     * @param diagnostico El diagnostico a establecer.
     */
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    /**
     * Obtiene la lista de medicamentos recetados.
     *
     * @return Una {@link List} de objetos {@link Medication}.
     */
    public List<Medication> getMedicamentos() { return medicamentos; }

    /**
     * Establece la lista de medicamentos recetados.
     *
     * @param medicamentos La lista de medicamentos a establecer.
     */
    public void setMedicamentos(List<Medication> medicamentos) { this.medicamentos = medicamentos; }

    /**
     * Compara este objeto {@link Recipe} con otro para establecer un orden natural.
     * La comparacion se basa en la fecha de la receta.
     *
     * @param o El objeto {@link Recipe} a comparar.
     * @return Un valor negativo, cero o positivo si la fecha de esta receta
     * es anterior, igual o posterior a la del objeto especificado.
     */
    @Override public int compareTo(Recipe o) {
        return this.fecha.compareTo(o.fecha);
    }

    /**
     * Sobreescribe el metodo {@code toString()} para proporcionar una
     * representacion textual legible del objeto {@link Recipe}.
     * <p>
     * El formato de la cadena resultante es: "id | paciente | fecha | diagnostico | cantidad med(s)".
     * </p>
     *
     * @return Una cadena que representa el objeto Recipe.
     */
    @Override public String toString() {
        return id + " | " + paciente + " | " + fecha + " | " + diagnostico + " | " + medicamentos.size() + " med(s)";
    }
}
