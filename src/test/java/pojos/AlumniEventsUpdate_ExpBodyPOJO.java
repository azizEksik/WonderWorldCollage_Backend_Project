package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlumniEventsUpdate_ExpBodyPOJO {

    /*
     "status": 200,
    "message": "Success",
    "Token_remaining_time": 205,
    "updateId": 5
     */
    private int status;
    private String message;
    private int updateId;
}
