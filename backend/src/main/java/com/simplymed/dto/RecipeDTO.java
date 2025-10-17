package com.simplymed.dto;

import java.time.LocalDate;
import java.util.List;

public class RecipeDTO {
    public Long id;
    public String patientName;
    public String doctorName;
    public LocalDate date;
    public List<MedicationDTO> medications;
    public String notes;
}