package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class TeacherHomeworkAdd_ExpBodyPojo {
/*
{
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 514,
    "addId": 0
}
 */

 int status;
 String message;
 int addId;

}
