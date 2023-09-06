package pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class TeacherHomeworkById_ListsPojo {
/*
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
        private String subject_name;
        private String subject_groups_id;
        private String name;
        private String assignments;


}
