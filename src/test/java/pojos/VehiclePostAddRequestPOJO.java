package pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehiclePostAddRequestPOJO {

    private String vehicle_no, vehicle_model, vehicle_photo, manufacture_year, registration_number, chasis_number, max_seating_capacity,
            driver_name, driver_licence, driver_contact, created_at;



}
