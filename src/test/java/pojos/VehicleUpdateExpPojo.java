package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleUpdateExpPojo {
    /*
    "status": 200,
  "message": "Success",
  "Token_remaining_time": 249,
  "updateId": 3
     */

    private int status;
    private String message;
    private int updateId;
}
