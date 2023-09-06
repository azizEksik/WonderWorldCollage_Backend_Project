package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlumniEventId_ExpectedPojo {

    /*
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 1523,
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

    private int status;
    private String message;
    private AlumniEventId_ListsPojo lists;

}
