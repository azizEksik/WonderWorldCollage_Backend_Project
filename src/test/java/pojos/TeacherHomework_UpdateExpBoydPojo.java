package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherHomework_UpdateExpBoydPojo {

    /*
    {
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 842,
    "updatedId": "220"
}
     */

    int status;
    String message;
    String updatedId;
}
