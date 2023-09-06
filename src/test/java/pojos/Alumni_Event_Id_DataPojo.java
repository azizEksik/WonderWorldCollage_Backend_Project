package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alumni_Event_Id_DataPojo {
    /*
     "status": 200,
    "message": "Success",
    "Token_remaining_time": 226,
    "lists": {
        "id": "1096",
        "title": "Sports Activite",
        "event_for": "all",
        "session_id": "11",
        "class_id": null,
        "section": "null",
        "from_date": "2023-02-14 00:00:00",
        "to_date": "2023-02-15 23:59:00",
        "note": "Sports",
        "photo": null,
        "is_active": "0",
        "event_notification_message": "Sports",
        "show_onwebsite": "0",
        "created_at": "2023-08-14 14:19:58"
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
