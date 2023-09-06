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
public class Books_ExpBodyPOJO {

    /*
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 16,
    "lists": [
        {
            "id": "1",
            "book_title": "Multiplication and Division Grades 3-4 23456",
            "book_no": "788789",
            "isbn_no": "",
            "subject": "",
            "rack_no": "110",
            "publish": "Barbara Bando",
            "author": "Barbara Bando",
            "qty": "100",
            "perunitcost": "12.00",
            "postdate": "2022-05-04",
            "description": " The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.",
            "available": "yes",
            "is_active": "no",
            "created_at": "2023-08-10 06:52:41",
            "updated_at": null
        },
     */

    private int status;
    private String message;
    private List<Books_DataPOJO> lists;
}
