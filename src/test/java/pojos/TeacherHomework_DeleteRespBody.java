package pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherHomework_DeleteRespBody {

    /*
    {
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 756,
    "DeletedId": 212
}
     */

    int status;
    String message;
    int DeletedId;

}
