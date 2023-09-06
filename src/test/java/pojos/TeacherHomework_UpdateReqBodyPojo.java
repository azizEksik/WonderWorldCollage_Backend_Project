package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherHomework_UpdateReqBodyPojo {
    /*
    {
         "id": "729",
        "class_id": "1",
        "section_id": "1",
        "session_id": "18",
        "subject_group_subject_id": "21",
        "subject_id": "1",
        "homework_date": "2022-07-05",
        "submit_date": "2022-07-08",
        "marks": 1.00,
        "description": "<p>changed description?</p>",
        "create_date": "2022-07-01",
        "evaluation_date": "0000-00-00",
        "document": "",
        "created_by": "5",
        "evaluated_by": "5"
}
     */
    String id;
    String class_id;
    String  section_id;
    String  session_id;
    String  subject_group_subject_id;
    String subject_id ;
    String  homework_date;
    String submit_date ;
    double marks ;
    String  description;
    String  create_date;
    String  evaluation_date;
    String  document;
    String  created_by;
    String evaluated_by ;

}
