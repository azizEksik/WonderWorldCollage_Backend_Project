package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumniEventsAdd_ReqBodyPojo {
    /*
    {
        "title": "Sports Activite",
        "event_for": "all",
        "session_id": 11,
        "section": "null",
        "from_date": "2023-02-14 00:00:00",
        "to_date": "2023-02-15 23:59:00",
        "note": "Sports",
        "event_notification_message": "Sports",
        "show_onwebsite": "0"
}
     */
    private String title;
    private String event_for;
    private String session_id;
    private String section;
    private String from_date;
    private String to_date;
    private String note;
    private String event_notification_message;
    private String show_onwebsite;

}
