package pojos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlumniAdd_PostExpBodyPOJO {
    /*
    {
        "student_id": "51",
        "current_email": "frk@gmail.com",
        "current_phone": "5535525956",
        "occupation": "Software",
        "address": "Turkey",
        "photo": "no photo"
}
     */
    private int student_id;
    private String current_email;
    private String current_phone;
    private String occupation;
    private String address;
    private String photo;





}
