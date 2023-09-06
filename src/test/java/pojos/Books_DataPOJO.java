package pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books_DataPOJO {

    /*
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

    private int id;
    private String book_title;
    private String book_no;
    private String isbn_no;
    private String subject;
    private String rack_no;
    private String publish;
    private String author;
    private String qty;
    private String perunitcost;
    private String postdate;
    private String description;
    private String available;
    private String is_active;
    private String created_at;
    private String updated_at;



}
