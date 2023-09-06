package pojos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlumniUpdate_ExpBodyPOJO {
    /*
    {
        "id": created id
        "student_id": "52",
        "current_email": "frkc@gmail.com",
        "current_phone": "5535525956",
        "occupation": "Software/",
        "address": "Turkey/",
        "photo": "no photo"
}
     */

    private int id;
    private int student_id;
    private String current_email;
    private String current_phone;
    private String occupation;
    private String address;
    private String photo;





}
