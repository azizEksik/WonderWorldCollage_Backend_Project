package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleAddExpPOJO {
    /*
     "status": 200,
  "message": "Success",
  "Token_remaining_time": 193,
  "addId": 5
     */

    private int status;
    private String message;
    private int addId;

}
