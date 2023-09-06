package pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksAdd_ExpBodyPOJO {

    /*
    {
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 807,
    "addId": 113
}
     */

    private int status;
    private String message;
    private int addId;
}
