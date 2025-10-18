package com.simplymed.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class RecipeDTO {
    public Long id;
    @NotBlank public String patientName;
    @NotBlank public String doctorName;
    @NotNull public LocalDate date;
    @Valid public List<MedicationDTO> medications;
    public String notes;
}
