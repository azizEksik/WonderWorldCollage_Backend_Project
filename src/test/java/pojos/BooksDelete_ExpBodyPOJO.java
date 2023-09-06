package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksDelete_ExpBodyPOJO {

    /*
    {
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 1001,
    "DeletedId": 114
}
     */

    private int status;
    private String message;
    private int DeletedId;
}
