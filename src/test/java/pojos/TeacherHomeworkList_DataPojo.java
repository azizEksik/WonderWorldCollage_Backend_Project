package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class TeacherHomeworkList_DataPojo {

    /*
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

        private String id;
        private String class_id;
        private String section_id;
        private String session_id;
        private String staff_id;
        private String subject_group_subject_id;
        private String subject_id;
        private String homework_date;
        private String submit_date;
        private String marks;
        private String description;
        private String create_date;
        private String evaluation_date;
        private String document;
        private String created_by;
        private String evaluated_by;
        private String created_at;
      //  private String class;
        private String section;
        private String subject_name;
        private String subject_groups_id;
        private String name;
        private String assignments;



}
