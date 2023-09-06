package pojos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlumniUpdate_RespBodyPOJO {
    /*
    {
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 460,
    "updateId": 58
}
     */

    private int status;
    private String message;
    private int updateId;

    private AlumniId_ExpBodyPOJO lists;





}
