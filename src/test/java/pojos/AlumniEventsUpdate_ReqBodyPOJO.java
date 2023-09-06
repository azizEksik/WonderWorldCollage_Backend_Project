package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlumniEventsUpdate_ReqBodyPOJO {
    /*
    {
        "id": 5,
        "title": "Sports Activite 2",
        "event_for": "all",
        "session_id": null,
        "section": "null",
        "from_date": "2023-02-14 00:00:00",
        "to_date": "2023-02-15 23:59:00",
        "note": "Sports",
        "event_notification_message": "Sports",
        "show_onwebsite": "0"
}
     */
    private int id;
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
