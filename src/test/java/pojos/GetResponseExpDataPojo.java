package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetResponseExpDataPojo {
        /*
        {
            "status": 200,
            "message": "success"
        }
        */

    private int status;
    private String message;



}
