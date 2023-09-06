package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alumni_DataPOJO {

    /*

         class_id : "3",
         student_session_id "44",
         id: "3",
         class: "Class 3",
         section_id: "1",
         section: "A",
         admission_no:"18003",
         roll_no: "201",
         admission_date: "2021-03-20",
         firstname: "Nicolas",
         middlename: null,
         lastname: "Fleming",
         image: "uploads/student_images/3.jpg",
         mobileno: "54564645564",
         email: "nicolas@gmail.com",
         state: null,
         city: null,
         pincode: null,
         religion: "",
         dob: "2015-05-12",
         current_address: "West Brooklyn",
         permanent_address: "",
         category_id: "1",
         category: "General",
         adhar_no: "564564",
         samagra_id: "4564654",
         bank_account_no:  "65456465454",
         bank_name: "Capital Bank",
         ifsc_code: "645646",
         guardian_name: "Dorian Fleming",
         guardian_relation: "Father",
         guardian_email: "dorian@gmail.com",
         guardian_phone: "54646546",
         guardian_address: "West Brooklyn",
         is_active: "yes",
         created_at: "2021-03-22 08:39:39",
         updated_at: null,
         father_name: "Dorian Fleming",
         rte: "No",
         gender: "Male",
         user_tbl_id: "5",
         username: "std3",
         user_tbl_password: "password",
         user_tbl_active: "yes"

     */

private String class_id;
private String student_session_id;
private int id;
//private String Class;
private String section_id;
private String section;
private String admission_no;
private String roll_no;
private String admission_date;
private String firstname;
private String middlename;
private String lastname;
private String image;
private String mobileno;
private String email;
private String state;
private String city;
private String pincode;
private String religion;
private String dob;
private String current_address;
private String permanent_address;
private String category_id;
private String category;
private String adhar_no;
private String samagra_id;
private String bank_account_no;
private String bank_name;
private String ifsc_code;
private String guardian_name;
private String guardian_relation;
private String guardian_email;
private String guardian_phone;
private String guardian_address;
private String is_active;
private String created_at;
private String updated_at;
private String father_name;
private String rte;
private String gender;
private String user_tbl_id;
private String username;
private String user_tbl_password;
private String user_tbl_active;




}
