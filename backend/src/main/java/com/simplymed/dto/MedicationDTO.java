/*
 * Summary: DTO REST para un medicamento; evita exponer la entidad.
 */
package com.simplymed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicationDTO {
    public Long id;
    @NotBlank public String name;
    @NotBlank public String dosage;
    @NotNull public Integer frequencyPerDay;
    public String instructions;
}
