package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class TeacherHomeworkById_ExpBodyPojo {
/*
{
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 1125,
    "lists": {
        "id": "729",
        "class_id": "1",
        "section_id": "1",
        "session_id": "18",
        "staff_id": "131",
        "subject_group_subject_id": "21",
        "subject_id": "1",
        "homework_date": "2023-08-12",
        "submit_date": "2023-08-12",
        "marks": "80.00",
        "description": "<p>Hello, Thanks...</p>",
        "create_date": "2023-08-12",
        "evaluation_date": null,
        "document": null,
        "created_by": "131",
        "evaluated_by": null,
        "created_at": "2023-08-12 14:18:56",
        "subject_name": "English",
        "subject_groups_id": "4",
        "name": "Class 1st Subject Group",
        "assignments": "0"
    }
}
 */
    private int status;
    private String message;
    private TeacherHomeworkById_ListsPojo lists;

}
