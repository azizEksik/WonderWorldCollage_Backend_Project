package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alumni_ExpBodyPOJO {
        /*
        "status": 200,
        "message": "Success",
        "Token_remaining_time": 906,
        "lists": [
        */

    private int status;
    private String message;
    private List<Alumni_DataPOJO> lists;



}
