package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlumniId_DataPOJO {

    /*
        "id": "3",
        "student_id": "29",
        "current_email": "deneme@deneme.com",
        "current_phone": "9809967867",
        "occupation": "",
        "address": "ne roma ne paris",
        "photo": "",
        "created_at": "2023-08-11 16:25:16"
     */

    private int id;
    private String student_id;
    private String current_email;
    private String current_phone;
    private String occupation;
    private String address;
    private String photo;
    private String created_at;
}
