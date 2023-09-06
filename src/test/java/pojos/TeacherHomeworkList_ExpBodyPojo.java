package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class TeacherHomeworkList_ExpBodyPojo {

/*
{
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 37,
    "lists": [
        {
            "id": "151",
            "class_id": "1",
            "section_id": "1",
            "session_id": "18",
            "staff_id": "5",
            "subject_group_subject_id": "21",
            "subject_id": "1",
            "homework_date": "2022-07-05",
            "submit_date": "2022-07-08",
            "marks": null,
            "description": "<p>\r\n\r\nRead carefully\r\n\r\n<br></p>",
            "create_date": "2022-07-01",
            "evaluation_date": "0000-00-00",
            "document": "",
            "created_by": "5",
            "evaluated_by": null,
            "created_at": "2023-01-18 01:07:13",
            "class": "Class 1",
            "section": "A",
            "subject_name": "English",
            "subject_groups_id": "4",
            "name": "Class 1st Subject Group",
            "assignments": "0"
        },
 */

    private int status;
    private String message;
    private List<TeacherHomeworkList_DataPojo> lists;

}
