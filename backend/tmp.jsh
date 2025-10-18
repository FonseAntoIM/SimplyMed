import com.simplymed.dto.*;
import com.simplymed.domain.*;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;

RecipeDTO dto = new RecipeDTO();
dto.patientName = "Carla";
dto.doctorName = "Ana";
dto.date = LocalDate.of(2025,10,16);
MedicationDTO mdto = new MedicationDTO();
mdto.name = "Paracetamol";
mdto.dosage = "500 mg";
mdto.frequencyPerDay = 2;
dto.medications = java.util.List.of(mdto);
ModelMapper mapper = new ModelMapper();
Recipe recipe = mapper.map(dto, Recipe.class);
System.out.println(recipe.getDate());
System.out.println(recipe.getPatientName());
System.out.println(recipe.getMedications());
