package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumniEvent_DataPojo {
           /*
    "lists": {
        "id": "2",
        "title": "Reunion For 2020-21 Batch",
        "event_for": "class",
        "session_id": "15",
        "class_id": "1",
        "section": "[\"1\",\"2\",\"3\"]",
        "from_date": "2021-03-07 00:00:00",
        "to_date": "2021-03-10 00:00:00",
        "note": "",
        "photo": "",
        "is_active": "0",
        "event_notification_message": "",
        "show_onwebsite": "0",
        "created_at": "2021-03-23 02:53:47"
    }
        */



    private int id;
    private String title;
    private String event_for;
    private String session_id;
    private String class_id;
    private String section;
    private String from_date;
    private String to_date;
    private String note;
    private String photo;
    private String is_active;
    private String event_notification_message;
    private String show_onwebsite;
    private String created_at;
}
