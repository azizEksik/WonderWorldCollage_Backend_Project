package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle_dataPOJO {


    /*
            "id": "1",
            "vehicle_no": "VH1001",
            "vehicle_model": "Volvo Bus",
            "vehicle_photo": "1677502387-149436744063fca7b3a1796!fd.png",
            "manufacture_year": "2017",
            "registration_number": "FVFF-08797865",
            "chasis_number": "45453",
            "max_seating_capacity": "50",
            "driver_name": "Michel",
            "driver_licence": "R534534",
            "driver_contact": "8667777869",
            "note": "",
            "created_at": "2023-02-27 07:53:07"
        }
     */

    private int id;
    private String vehicle_no, vehicle_model, vehicle_photo, manufacture_year, registration_number, chasis_number, max_seating_capacity,
    driver_name, driver_licence, driver_contact, created_at;




}
