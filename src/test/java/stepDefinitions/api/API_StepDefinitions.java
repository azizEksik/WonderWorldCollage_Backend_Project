package stepDefinitions.api;


import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;
import pojos.*;
import utilities.API_ReusableMethods;
import utilities.Authentication;
import utilities.ConfigReader;

import java.util.*;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_StepDefinitions {

    public static String fullPath;

    public static JSONObject reqBody;
    Response response1;
    Response response;

    public static int addIdNoForEventsAdd;

    public static String date;

    public static int booksDeletedId;

    public static int addIdNoForBooksAdd;

    public static int saveId;


    public static int newAddIdForAlumni;

    public static int alumniDeletedId;
    public static int alumniEventsDeletedId;

    public static int deletedHomeworkId;
    public static int newCreatedHomeworkId;

    @Given("Prepare url with path parameters {string}.")
    public void prepare_url_with_path_parameters(String givenPathParams) {

        // https://qa.wonderworldcollege.com/api/studentList

        // spec.pathParams("pp1","api","pp2","studentList");

        String[] paths = givenPathParams.split("/"); // ["api","studentList"]

        System.out.println(Arrays.toString(paths));

        StringBuilder tempPath = new StringBuilder("/{");

        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + i;
            String value = paths[i].trim();

            spec.pathParam(key, value);

            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullPath = tempPath.toString();

        System.out.println("fullpath =" + fullPath);


    }

    @Given("Prepare {string} {string} {string} token")
    public void prepare_token(String givenUser, String user, String password) {
        Authentication.generateToken(givenUser, user, password);

    }


    @Given("Get request.")
    public void get_request() {

        API_ReusableMethods.getRequest();

    }

    @Given("Verify that status code {int} and message is {string}.")
    public void verify_that_status_code_and_message_is(Integer givenCode, String givenMessage) {

        GetResponseExpDataPojo expData = new GetResponseExpDataPojo(givenCode, givenMessage);

        Response response = API_ReusableMethods.getResponse;

        JsonPath resJS = response.jsonPath();

        Assert.assertEquals(expData.getStatus(), response.getStatusCode());
        Assert.assertEquals(expData.getMessage(), resJS.get("message"));
    }


    @Given("A GET request is sent for the book issue list")
    public void a_get_request_is_sent_for_the_book_issue_list() {
        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .headers("Authorization", Authentication.token)
                .get(fullPath);

    }

    @Given("It is verified that the returned response has a status code of {int} and the message information is {string}")
    public void it_is_verified_that_the_returned_response_has_a_status_code_of_and_the_message_information_is(Integer code, String message) {

        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("A GET request with invalid authorization is sent for the book issue list")
    public void a_get_request_with_invalid_authorization_is_sent_for_the_book_issue_list() {

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .header("Authorization", "Failed Token")
                .get(fullPath);

    }

    @Given("It is verified that when invalid authorization credentials are used, the returned response has a status code of {int} and the message information is {string}")
    public void it_is_verified_that_when_invalid_authorization_credentials_are_used_the_returned_response_has_a_status_code_of_and_the_message_information_is
            (Integer code, String message) {

        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("The data with an id of seven in the response's list body should be verified to match the expected data")
    public void the_data_with_an_of_seven_in_the_response_s_list_body_should_be_verified_to_match_the_expected_data() {

        JSONObject secondIndex = new JSONObject();

        String returnDate = null;

        secondIndex.put("id", "7");
        secondIndex.put("book_id", "3");
        secondIndex.put("member_id", "9");
        secondIndex.put("duereturn_date", "2021-05-06");
        secondIndex.put("return_date", JSONObject.NULL);
        secondIndex.put("issue_date", "2021-05-04");
        secondIndex.put("is_returned", "0");
        secondIndex.put("is_active", "no");
        secondIndex.put("created_at", "2021-05-04 02:56:46");

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .header("Authorization", Authentication.token)
                .get(fullPath);

        JsonPath responseJp = response.jsonPath();

        Assert.assertEquals(responseJp.get("lists[1].id"), secondIndex.get("id"));
        Assert.assertEquals(responseJp.get("lists[1].book_id"), secondIndex.get("book_id"));
        Assert.assertEquals(responseJp.get("lists[1].member_id"), secondIndex.get("member_id"));
        Assert.assertEquals(responseJp.get("lists[1].duereturn_date"), secondIndex.get("duereturn_date"));
        Assert.assertEquals(responseJp.get("lists[1].return_date"), null);
        Assert.assertEquals(responseJp.get("lists[1].issue_date"), secondIndex.get("issue_date"));
        Assert.assertEquals(responseJp.get("lists[1].is_returned"), secondIndex.get("is_returned"));
        Assert.assertEquals(responseJp.get("lists[1].is_active"), secondIndex.get("is_active"));
        Assert.assertEquals(responseJp.get("lists[1].created_at"), secondIndex.get("created_at"));


    }

    @Given("A POST request is sent with the correct data and Authorization")
    public void a_post_request_is_sent_with_the_correct_data_and_authorization() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("id", 7);

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(requestBody.toString())
                .header("Authorization", Authentication.token)
                .post(fullPath);
    }

    @Given("The returned response's status code is verified to be {int}, and the message information in the body is confirmed to be {string}")
    public void the_returned_response_s_status_code_is_verified_to_be_and_the_message_information_in_the_body_is_confirmed_to_be
            (Integer code, String message) {

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("An invalid POST request is sent to the api-bookIssueId endpoint with incorrect data for the id")
    public void an_invalid_post_request_is_sent_to_the_api_book_issue_id_endpoint_with_incorrect_data_for_the_id() {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("id", 2500);

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody)
                .header("Authorization", Authentication.token)
                .post(fullPath);

    }

    @Given("It is verified that the response with an invalid id returned from the api-bookIssueId endpoint has a status code of {int}, and the message information in the body is {string}")
    public void it_is_verified_that_the_response_with_an_invalid_id_returned_from_the_api_book_issue_id_endpoint_has_a_status_code_of_and_the_message_information_in_the_body_is
            (Integer code, String message) {


        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("An invalid POST request with incorrect Authorization is sent to the api-bookIssueId endpoint")
    public void an_invalid_post_request_with_incorrect_authorization_is_sent_to_the_api_book_issue_id_endpoint() {
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("id", 7);

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody)
                .header("Authorization", "Bearer Token")
                .post(fullPath);
    }

    @Given("It is verified that the response with invalid Authorization returned from the api-bookIssueId endpoint has a status code of {int}, and the message information in the body is {string}")
    public void it_is_verified_that_the_response_with_invalid_authorization_returned_from_the_api_book_issue_id_endpoint_has_a_status_code_of_and_the_message_information_in_the_body_is
            (Integer code, String message) {


        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("It should be verified that the POST request response returned from the api-bookIssueId address contains the values given in the expected data")
    public void ıt_should_be_verified_that_the_post_request_response_returned_from_the_api_book_ıssue_ıd_address_contains_the_values_given_in_the_expected_data() {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("id", 7);

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody)
                .header("Authorization", Authentication.token)
                .post(fullPath);

        String actualData = response.getBody().asString();


        String[] expectedData = {"id", "book_id", "member_id", "duereturn_date", "return_date"
                , "issue_date", "is_returned", "is_active", "created_at"};


        for (int i = 0; i < expectedData.length; i++) {

            Assert.assertTrue(actualData.contains(expectedData[i]));

        }

    }

    @Given("A POST request with valid authorization credentials and valid data is sent to the api-bookIssueAdd endpoint")
    public void a_post_request_with_valid_authorization_credentials_and_valid_data_is_sent_to_the_api_book_ıssue_add_endpoint() {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("book_id", "300");
        requestBody.put("member_id", "7");
        requestBody.put("duereturn_date", "2021-08-04");
        requestBody.put("return_date", "2021-09-06");
        requestBody.put("issue_date", "2021-08-04");

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(requestBody)
                .header("Authorization", Authentication.token)
                .post(fullPath);
    }

    @Given("The response returned from the api-bookIssueAdd endpoint is verified to have a status code of {int}, and the message information in the response body is {string}")
    public void the_response_returned_from_the_api_book_ıssue_add_endpoint_is_verified_to_have_a_status_code_of_and_the_message_information_in_the_response_body_is
            (Integer code, String message) {

        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("An invalid POST request with incorrect authorization credentials and incomplete data is sent to the api-bookIssueAdd endpoint")
    public void an_invalid_post_request_with_incorrect_authorization_credentials_and_incomplete_data_is_sent_to_the_api_book_ıssue_add_endpoint() {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("member_id", "7");
        requestBody.put("duereturn_date", "2021-08-04");
        requestBody.put("return_date", "2021-09-06");
        requestBody.put("issue_date", "2021-08-04");

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(requestBody)
                .header("Authorization", Authentication.token)
                .post(fullPath);

    }

    @Given("It is verified that the response from the api-bookIssueAdd endpoint, when a request with missing data is sent, has a status code of {int}, and the message information in the response body is {string}")
    public void ıt_is_verified_that_the_response_from_the_api_book_ıssue_add_endpoint_when_a_request_with_missing_data_is_sent_has_a_status_code_of_and_the_message_information_in_the_response_body_is
            (Integer code, String message) {

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }


    @Given("The creation of a new book issue record through the api-bookIssueAdd endpoint on the API should be verified")
    public void the_creation_of_a_new_book_issue_record_through_the_api_book_ıssue_add_endpoint_on_the_apı_should_be_verified() {

        Random rnd = new Random();

        int randomId = rnd.nextInt(300);

        int randomAddId = randomId + 700;

        String bookId = Integer.toString(randomAddId);

        Map<String, Object> requestBodyAdd = new HashMap<>();

        requestBodyAdd.put("book_id", bookId);
        requestBodyAdd.put("member_id", "7");
        requestBodyAdd.put("duereturn_date", "2021-08-04");
        requestBodyAdd.put("return_date", "2021-09-06");
        requestBodyAdd.put("issue_date", "2021-08-04");

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(requestBodyAdd)
                .header("Authorization", Authentication.token)
                .post(fullPath);

        response.prettyPrint();

        response = null;

        Map<String, Object> requestBodyIssueId = new HashMap<>();

        requestBodyIssueId.put("id", bookId);

        response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(requestBodyIssueId)
                .header("Authorization", Authentication.token)
                .post("https://qa.wonderworldcollege.com/api/bookIssueId");

        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(200)
                .body("message", Matchers.equalTo("Success"));
    }


    @Given("Response body content check test for bookList")
    public void response_body_content_check_test_for_book_list() {
        // BERKE


        String book_title = "nnnnnnnnnnnnnnnnn";
        String author = "Barbara Bando";
        String description = " The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.";

        Books_DataPOJO dataPOJO = new Books_DataPOJO(1, book_title, "788789", "", "", "110",
                author, author, "100", "12.00", "2022-05-04", description, "yes", "no", "2023-08-14 13:02:31", null);

        List<Books_DataPOJO> lists = new ArrayList<>();
        lists.add(dataPOJO);

        Books_ExpBodyPOJO expBodyPOJO = new Books_ExpBodyPOJO(200, "Success", lists);

        Response response = API_ReusableMethods.getResponse;

        Books_ExpBodyPOJO respPOJO = response.as(Books_ExpBodyPOJO.class);

        Assert.assertEquals(expBodyPOJO.getStatus(), respPOJO.getStatus());
        Assert.assertEquals(expBodyPOJO.getMessage(), respPOJO.getMessage());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getId(), respPOJO.getLists().get(0).getId());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getBook_title(), respPOJO.getLists().get(0).getBook_title());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getBook_no(), respPOJO.getLists().get(0).getBook_no());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getIsbn_no(), respPOJO.getLists().get(0).getIsbn_no());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getSubject(), respPOJO.getLists().get(0).getSubject());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getRack_no(), respPOJO.getLists().get(0).getRack_no());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getPublish(), respPOJO.getLists().get(0).getPublish());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getAuthor(), respPOJO.getLists().get(0).getAuthor());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getQty(), respPOJO.getLists().get(0).getQty());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getPerunitcost(), respPOJO.getLists().get(0).getPerunitcost());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getPostdate(), respPOJO.getLists().get(0).getPostdate());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getDescription(), respPOJO.getLists().get(0).getDescription());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getAvailable(), respPOJO.getLists().get(0).getAvailable());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getIs_active(), respPOJO.getLists().get(0).getIs_active());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getCreated_at(), respPOJO.getLists().get(0).getCreated_at());
        Assert.assertEquals(expBodyPOJO.getLists().get(0).getUpdated_at(), respPOJO.getLists().get(0).getUpdated_at());

    }

    @Given("Response body content check test for Alumni Events List")
    public void response_body_content_check_test_for_alumni_events_list() {
       /*
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
        },
        */

        AlumniEvent_DataPojo dataPojo = new AlumniEvent_DataPojo(2, "Reunion For 2020-21 Batch", "class",
                "15", "1", "[\"1\",\"2\",\"3\"]", "2021-03-07 00:00:00",
                "2021-03-10 00:00:00", "", "", "0", "", "0",
                "2021-03-23 02:53:47");

        List<AlumniEvent_DataPojo> lists = new ArrayList<>();
        lists.add(dataPojo);

        AlumniEventListExpectedBodyPojo expectedBodyPojo = new AlumniEventListExpectedBodyPojo(200, "Success", lists);
        Response response = API_ReusableMethods.getResponse;


        AlumniEventListExpectedBodyPojo responsPojo = response.as(AlumniEventListExpectedBodyPojo.class);

        Assert.assertEquals(expectedBodyPojo.getStatus(), responsPojo.getStatus());
        Assert.assertEquals(expectedBodyPojo.getMessage(), responsPojo.getMessage());


        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getId(), responsPojo.getLists().get(10).getId());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getTitle(), responsPojo.getLists().get(10).getTitle());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getEvent_for(), responsPojo.getLists().get(10).getEvent_for());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getSession_id(), responsPojo.getLists().get(10).getSession_id());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getClass_id(), responsPojo.getLists().get(10).getClass_id());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getSection(), responsPojo.getLists().get(10).getSection());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getFrom_date(), responsPojo.getLists().get(10).getFrom_date());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getTo_date(), responsPojo.getLists().get(10).getTo_date());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getNote(), responsPojo.getLists().get(10).getNote());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getPhoto(), responsPojo.getLists().get(10).getPhoto());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getIs_active(), responsPojo.getLists().get(10).getIs_active());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getEvent_notification_message(), responsPojo.getLists().get(10).getEvent_notification_message());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getShow_onwebsite(), responsPojo.getLists().get(10).getShow_onwebsite());
        Assert.assertEquals(expectedBodyPojo.getLists().get(0).getCreated_at(), responsPojo.getLists().get(10).getCreated_at());

    }

    @Given("POST Request for booksId")
    public void post_request_for_books_id() {
        // BERKE
        BooksId_PostReqBodyPOJO reqBodyPOJO = new BooksId_PostReqBodyPOJO(2);

        API_ReusableMethods.postRequest(reqBodyPOJO);
    }

    @Given("Verify that status code {int} and message is {string} for POST Request")
    public void verify_that_status_code_and_message_is_for_post_request(Integer givenCode, String givenMessage) {
        // FOR EVERYONE

        GetResponseExpDataPojo expData = new GetResponseExpDataPojo(givenCode, givenMessage);

        Response response = API_ReusableMethods.postResponse;

        JsonPath resJS = response.jsonPath();

        Assert.assertEquals(expData.getStatus(), response.getStatusCode());
        Assert.assertEquals(expData.getMessage(), resJS.get("message"));

    }


    @Given("The POST body with valid authorization information is sent to the api-alumniEventsId endpoint.")
    public void the_post_body_with_valid_authorization_information_is_sent_to_the_api_alumni_events_id_endpoint() {

        BooksId_PostReqBodyPOJO idReqBody = new BooksId_PostReqBodyPOJO(2);
        API_ReusableMethods.postRequest(idReqBody);
    }


    @Given("Response body content check test for bookId")
    public void response_body_content_check_test_for_book_id() {
        // BERKE

        String book_title = "The Science Book: Big Ideas Simply Explained";
        String description = "";

        Books_DataPOJO dataPOJO = new Books_DataPOJO(2, book_title, "1236", "978-3-16-148410-0", "Science ",
                "10", "Tata Macgrow Hills", "DK -999", "50", "1500.00", "2021-05-12", description, "yes", "no", "2022-05-02 08:22:49", null);


        BooksId_ExpBodyPOJO expBodyPOJO = new BooksId_ExpBodyPOJO(200, "Success", dataPOJO);

        Response response = API_ReusableMethods.postResponse;

        BooksId_ExpBodyPOJO respPOJO = response.as(BooksId_ExpBodyPOJO.class);

        Assert.assertEquals(expBodyPOJO.getStatus(), respPOJO.getStatus());
        Assert.assertEquals(expBodyPOJO.getMessage(), respPOJO.getMessage());
        Assert.assertEquals(expBodyPOJO.getLists().getId(), respPOJO.getLists().getId());
        Assert.assertEquals(expBodyPOJO.getLists().getBook_title(), respPOJO.getLists().getBook_title());
        Assert.assertEquals(expBodyPOJO.getLists().getBook_no(), respPOJO.getLists().getBook_no());
        Assert.assertEquals(expBodyPOJO.getLists().getIsbn_no(), respPOJO.getLists().getIsbn_no());
        Assert.assertEquals(expBodyPOJO.getLists().getSubject(), respPOJO.getLists().getSubject());
        Assert.assertEquals(expBodyPOJO.getLists().getRack_no(), respPOJO.getLists().getRack_no());
        Assert.assertEquals(expBodyPOJO.getLists().getPublish(), respPOJO.getLists().getPublish());
        Assert.assertEquals(expBodyPOJO.getLists().getAuthor(), respPOJO.getLists().getAuthor());
        Assert.assertEquals(expBodyPOJO.getLists().getQty(), respPOJO.getLists().getQty());
        Assert.assertEquals(expBodyPOJO.getLists().getPerunitcost(), respPOJO.getLists().getPerunitcost());
        Assert.assertEquals(expBodyPOJO.getLists().getPostdate(), respPOJO.getLists().getPostdate());
        Assert.assertEquals(expBodyPOJO.getLists().getDescription(), respPOJO.getLists().getDescription());
        Assert.assertEquals(expBodyPOJO.getLists().getAvailable(), respPOJO.getLists().getAvailable());
        Assert.assertEquals(expBodyPOJO.getLists().getIs_active(), respPOJO.getLists().getIs_active());
        Assert.assertEquals(expBodyPOJO.getLists().getCreated_at(), respPOJO.getLists().getCreated_at());
        Assert.assertEquals(expBodyPOJO.getLists().getUpdated_at(), respPOJO.getLists().getUpdated_at());

    }

    @Given("The content of the list in the vehicle list response body should be validated")
    public void the_content_of_the_list_in_the_vehicle_list_response_body_should_be_validated() {

        Vehicle_dataPOJO vehicleDataPOJO = new Vehicle_dataPOJO(1, "VH1001", "Volvo Bus",
                "1677502387-149436744063fca7b3a1796!fd.png", "2017", "FVFF-08797865", "45453", "50",
                "Michel", "R534534", "8667777869", "2023-02-27 07:53:07"
        );


        List<Vehicle_dataPOJO> lists = new ArrayList<>();

        lists.add(vehicleDataPOJO);

        GetVehicleListExpBodyPojo expBodyPojo = new GetVehicleListExpBodyPojo(200, "Success", lists);

        Response response = API_ReusableMethods.getResponse;

        GetVehicleListExpBodyPojo responsePojo = response.as(GetVehicleListExpBodyPojo.class);


        int index = 0;
        for (int i = 0; i < responsePojo.getLists().size(); i++) {
            if (responsePojo.getLists().get(i).getId() == expBodyPojo.getLists().get(0).getId())
                index = i;
        }

        Assert.assertEquals(expBodyPojo.getStatus(), responsePojo.getStatus());
        Assert.assertEquals(expBodyPojo.getMessage(), responsePojo.getMessage());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getId(), responsePojo.getLists().get(index).getId());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getVehicle_no(), responsePojo.getLists().get(index).getVehicle_no());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getVehicle_model(), responsePojo.getLists().get(index).getVehicle_model());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getVehicle_photo(), responsePojo.getLists().get(index).getVehicle_photo());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getManufacture_year(), responsePojo.getLists().get(index).getManufacture_year());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getRegistration_number(), responsePojo.getLists().get(index).getRegistration_number());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getChasis_number(), responsePojo.getLists().get(index).getChasis_number());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getMax_seating_capacity(), responsePojo.getLists().get(index).getMax_seating_capacity());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getDriver_name(), responsePojo.getLists().get(index).getDriver_name());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getDriver_licence(), responsePojo.getLists().get(index).getDriver_licence());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getDriver_contact(), responsePojo.getLists().get(index).getDriver_contact());
        Assert.assertEquals(expBodyPojo.getLists().get(0).getCreated_at(), responsePojo.getLists().get(index).getCreated_at());


    }

    @Given("The content of the list data in the response body should be validated for AlumniEventsId.")
    public void the_content_of_the_list_data_in_the_response_body_should_be_validated_for_alumni_events_id() {

        AlumniEventId_ListsPojo listsPojo = new AlumniEventId_ListsPojo(2, "Reunion For 2020-21 Batch", "class",
                "15", "1", "[\"1\",\"2\",\"3\"]", "2021-03-07 00:00:00",
                "2021-03-10 00:00:00", "", "", "0", "",
                "0", "2021-03-23 02:53:47");

        AlumniEventId_ExpectedPojo expectedIdPojo = new AlumniEventId_ExpectedPojo(200, "Success", listsPojo);

        Response response = API_ReusableMethods.postResponse;

        AlumniEventId_ExpectedPojo respIdPojo = response.as(AlumniEventId_ExpectedPojo.class);

        Assert.assertEquals(expectedIdPojo.getStatus(), respIdPojo.getStatus());
        Assert.assertEquals(expectedIdPojo.getMessage(), respIdPojo.getMessage());
        Assert.assertEquals(expectedIdPojo.getLists().getId(), respIdPojo.getLists().getId());
        Assert.assertEquals(expectedIdPojo.getLists().getTitle(), respIdPojo.getLists().getTitle());
        Assert.assertEquals(expectedIdPojo.getLists().getEvent_for(), respIdPojo.getLists().getEvent_for());
        Assert.assertEquals(expectedIdPojo.getLists().getSession_id(), respIdPojo.getLists().getSession_id());
        Assert.assertEquals(expectedIdPojo.getLists().getClass_id(), respIdPojo.getLists().getClass_id());
        Assert.assertEquals(expectedIdPojo.getLists().getSection(), respIdPojo.getLists().getSection());
        Assert.assertEquals(expectedIdPojo.getLists().getFrom_date(), respIdPojo.getLists().getFrom_date());
        Assert.assertEquals(expectedIdPojo.getLists().getTo_date(), respIdPojo.getLists().getTo_date());
        Assert.assertEquals(expectedIdPojo.getLists().getNote(), respIdPojo.getLists().getNote());
        Assert.assertEquals(expectedIdPojo.getLists().getPhoto(), respIdPojo.getLists().getPhoto());
        Assert.assertEquals(expectedIdPojo.getLists().getIs_active(), respIdPojo.getLists().getIs_active());
        Assert.assertEquals(expectedIdPojo.getLists().getEvent_notification_message(), respIdPojo.getLists().getEvent_notification_message());
        Assert.assertEquals(expectedIdPojo.getLists().getShow_onwebsite(), respIdPojo.getLists().getShow_onwebsite());
        Assert.assertEquals(expectedIdPojo.getLists().getCreated_at(), respIdPojo.getLists().getCreated_at());

    }

    @Given("The POST body with valid authorization information is sent to the api-alumniEventsAdd endpoint.")
    public void the_post_body_with_valid_authorization_information_is_sent_to_the_api_alumni_events_add_endpoint() {

        AlumniEventsAdd_ReqBodyPojo addReqBodyPojo = new AlumniEventsAdd_ReqBodyPojo("Sports Activite", "all",
                "11", "null", "2023-02-14 00:00:00",
                "2023-02-15 23:59:00", "Sports", "Sports",
                "0");

        API_ReusableMethods.postRequest(addReqBodyPojo);

    }

    @Given("Verify response body information for Alumni.")
    public void verify_response_body_information_for_alumni() {

        Alumni_DataPOJO dataPojo = new Alumni_DataPOJO("3", "44", 3, "1", "A", "18003", "201", "2021-03-20", "Nicolas", null, "Fleming",
                "uploads/student_images/3.jpg", "54564645564", "nicolas@gmail.com", null, null, null, "", "2015-05-12", "West Brooklyn",
                "", "1", "General", "564564", "4564654", "65456465454", "Capital Bank", "645646", "Dorian Fleming", "Father", "dorian@gmail.com",
                "54646546", "West Brooklyn", "yes", "2021-03-22 08:39:39", null, "Dorian Fleming", "No", "Male", "5", "std3", "password", "yes");

        List<Alumni_DataPOJO> lists = new ArrayList<>();
        lists.add(dataPojo);

        Alumni_ExpBodyPOJO expPojo = new Alumni_ExpBodyPOJO(200, "Success", lists);

        Response response = API_ReusableMethods.getResponse;

        Alumni_ExpBodyPOJO respPOJO = response.as(Alumni_ExpBodyPOJO.class);

        Assert.assertEquals(expPojo.getStatus(), respPOJO.getStatus());
        Assert.assertEquals(expPojo.getMessage(), respPOJO.getMessage());
        Assert.assertEquals(expPojo.getLists().get(0).getClass_id(), respPOJO.getLists().get(2).getClass_id());
        Assert.assertEquals(expPojo.getLists().get(0).getClass(), respPOJO.getLists().get(2).getClass());
        Assert.assertEquals(expPojo.getLists().get(0).getStudent_session_id(), respPOJO.getLists().get(2).getStudent_session_id());
        Assert.assertEquals(expPojo.getLists().get(0).getId(), respPOJO.getLists().get(2).getId());
        Assert.assertEquals(expPojo.getLists().get(0).getSection_id(), respPOJO.getLists().get(2).getSection_id());
        Assert.assertEquals(expPojo.getLists().get(0).getSection(), respPOJO.getLists().get(2).getSection());
        Assert.assertEquals(expPojo.getLists().get(0).getAdmission_no(), respPOJO.getLists().get(2).getAdmission_no());
        Assert.assertEquals(expPojo.getLists().get(0).getRoll_no(), respPOJO.getLists().get(2).getRoll_no());
        Assert.assertEquals(expPojo.getLists().get(0).getAdmission_date(), respPOJO.getLists().get(2).getAdmission_date());
        Assert.assertEquals(expPojo.getLists().get(0).getFirstname(), respPOJO.getLists().get(2).getFirstname());
        Assert.assertEquals(expPojo.getLists().get(0).getMiddlename(), respPOJO.getLists().get(2).getMiddlename());
        Assert.assertEquals(expPojo.getLists().get(0).getLastname(), respPOJO.getLists().get(2).getLastname());
        Assert.assertEquals(expPojo.getLists().get(0).getImage(), respPOJO.getLists().get(2).getImage());
        Assert.assertEquals(expPojo.getLists().get(0).getMobileno(), respPOJO.getLists().get(2).getMobileno());
        Assert.assertEquals(expPojo.getLists().get(0).getEmail(), respPOJO.getLists().get(2).getEmail());
        Assert.assertEquals(expPojo.getLists().get(0).getState(), respPOJO.getLists().get(2).getState());
        Assert.assertEquals(expPojo.getLists().get(0).getCity(), respPOJO.getLists().get(2).getCity());
        Assert.assertEquals(expPojo.getLists().get(0).getPincode(), respPOJO.getLists().get(2).getPincode());
        Assert.assertEquals(expPojo.getLists().get(0).getReligion(), respPOJO.getLists().get(2).getReligion());
        Assert.assertEquals(expPojo.getLists().get(0).getDob(), respPOJO.getLists().get(2).getDob());
        Assert.assertEquals(expPojo.getLists().get(0).getCurrent_address(), respPOJO.getLists().get(2).getCurrent_address());
        Assert.assertEquals(expPojo.getLists().get(0).getPermanent_address(), respPOJO.getLists().get(2).getPermanent_address());
        Assert.assertEquals(expPojo.getLists().get(0).getCategory_id(), respPOJO.getLists().get(2).getCategory_id());
        Assert.assertEquals(expPojo.getLists().get(0).getCategory(), respPOJO.getLists().get(2).getCategory());
        Assert.assertEquals(expPojo.getLists().get(0).getAdhar_no(), respPOJO.getLists().get(2).getAdhar_no());
        Assert.assertEquals(expPojo.getLists().get(0).getSamagra_id(), respPOJO.getLists().get(2).getSamagra_id());
        Assert.assertEquals(expPojo.getLists().get(0).getBank_account_no(), respPOJO.getLists().get(2).getBank_account_no());
        Assert.assertEquals(expPojo.getLists().get(0).getBank_name(), respPOJO.getLists().get(2).getBank_name());
        Assert.assertEquals(expPojo.getLists().get(0).getIfsc_code(), respPOJO.getLists().get(2).getIfsc_code());
        Assert.assertEquals(expPojo.getLists().get(0).getGuardian_name(), respPOJO.getLists().get(2).getGuardian_name());
        Assert.assertEquals(expPojo.getLists().get(0).getGuardian_relation(), respPOJO.getLists().get(2).getGuardian_relation());
        Assert.assertEquals(expPojo.getLists().get(0).getGuardian_email(), respPOJO.getLists().get(2).getGuardian_email());
        Assert.assertEquals(expPojo.getLists().get(0).getGuardian_phone(), respPOJO.getLists().get(2).getGuardian_phone());
        Assert.assertEquals(expPojo.getLists().get(0).getGuardian_address(), respPOJO.getLists().get(2).getGuardian_address());
        Assert.assertEquals(expPojo.getLists().get(0).getIs_active(), respPOJO.getLists().get(2).getIs_active());
        Assert.assertEquals(expPojo.getLists().get(0).getCreated_at(), respPOJO.getLists().get(2).getCreated_at());
        Assert.assertEquals(expPojo.getLists().get(0).getUpdated_at(), respPOJO.getLists().get(2).getUpdated_at());
        Assert.assertEquals(expPojo.getLists().get(0).getFather_name(), respPOJO.getLists().get(2).getFather_name());
        Assert.assertEquals(expPojo.getLists().get(0).getRte(), respPOJO.getLists().get(2).getRte());
        Assert.assertEquals(expPojo.getLists().get(0).getGender(), respPOJO.getLists().get(2).getGender());
        Assert.assertEquals(expPojo.getLists().get(0).getUser_tbl_id(), respPOJO.getLists().get(2).getUser_tbl_id());
        Assert.assertEquals(expPojo.getLists().get(0).getUsername(), respPOJO.getLists().get(2).getUsername());
        Assert.assertEquals(expPojo.getLists().get(0).getUser_tbl_password(), respPOJO.getLists().get(2).getUser_tbl_password());
        Assert.assertEquals(expPojo.getLists().get(0).getUser_tbl_active(), respPOJO.getLists().get(2).getUser_tbl_active());

    }

    @Given("POST Request for alumniid.")
    public void post_request_for_alumni_id() {

        AlumniId_PostReqBodyPOJO reqBodyPOJO = new AlumniId_PostReqBodyPOJO(3);

        API_ReusableMethods.postRequest(reqBodyPOJO);
    }

    @Given("Verify response body information for AlumniId.")
    public void verify_response_body_information_for_alumni_id() {

        AlumniId_DataPOJO dataPojo = new AlumniId_DataPOJO(3, "29", "cuntahsin.com",
                "123456", "police", "", null, "2023-08-13 16:07:28");

        AlumniId_ExpBodyPOJO expPojo = new AlumniId_ExpBodyPOJO(200, "Success", dataPojo);

        Response response = API_ReusableMethods.postResponse;

        AlumniId_ExpBodyPOJO respPOJO = response.as(AlumniId_ExpBodyPOJO.class);

        Assert.assertEquals(expPojo.getStatus(), respPOJO.getStatus());
        Assert.assertEquals(expPojo.getMessage(), respPOJO.getMessage());
        Assert.assertEquals(expPojo.getLists().getId(), respPOJO.getLists().getId());
        Assert.assertEquals(expPojo.getLists().getStudent_id(), respPOJO.getLists().getStudent_id());
        Assert.assertEquals(expPojo.getLists().getCurrent_email(), respPOJO.getLists().getCurrent_email());
        Assert.assertEquals(expPojo.getLists().getCurrent_phone(), respPOJO.getLists().getCurrent_phone());
        Assert.assertEquals(expPojo.getLists().getOccupation(), respPOJO.getLists().getOccupation());
        Assert.assertEquals(expPojo.getLists().getAddress(), respPOJO.getLists().getAddress());
        Assert.assertEquals(expPojo.getLists().getPhoto(), respPOJO.getLists().getPhoto());
        Assert.assertEquals(expPojo.getLists().getCreated_at(), respPOJO.getLists().getCreated_at());

    }

    @Given("Response body content check test for alumniEventId.")
    public void response_body_content_check_test_for_alumni_event_ıd() {

        // for sending add request and take id number

        Response addResponse = API_ReusableMethods.postResponse;
        AlumniEventsAdd_ExpBodyPojo addRespPOJO = addResponse.as(AlumniEventsAdd_ExpBodyPojo.class);

        addIdNoForEventsAdd = addRespPOJO.getAddId();

        // for sending id and control if data is true or not

        prepare_url_with_path_parameters("api/alumniEventsId");
        prepare_token("admin", "hilal", "CorrectPassword");

        AlumniEventsId_PostReqBodyPOJO reqBodyPOJO = new AlumniEventsId_PostReqBodyPOJO(addIdNoForEventsAdd);
        API_ReusableMethods.postRequest(reqBodyPOJO);

        Response idResponse = API_ReusableMethods.postResponse;

        Alumni_Events_Id_ExpectedPojo idRespPOJO = idResponse.as(Alumni_Events_Id_ExpectedPojo.class);

        String date = idRespPOJO.getLists().getCreated_at();

        Alumni_Event_Id_DataPojo dataPOJO = new Alumni_Event_Id_DataPojo(addIdNoForEventsAdd,
                ConfigReader.getProperty("alumniEventsId_title"),
                ConfigReader.getProperty("alumniEventsId_event_for"),
                ConfigReader.getProperty("alumniEventsId_session_id"),
                null,
                ConfigReader.getProperty("alumniEventsId_section"),
                ConfigReader.getProperty("alumniEventsId_from_date"),
                ConfigReader.getProperty("alumniEventsId_to_date"),
                ConfigReader.getProperty("alumniEventsId_note"),
                null,
                ConfigReader.getProperty("alumniEventsId_is_active"),
                ConfigReader.getProperty("alumniEventsId_event_notification_message"),
                ConfigReader.getProperty("alumniEventsId_show_onwebsite"),
                date);

        Alumni_Events_Id_ExpectedPojo expBodyPOJO = new Alumni_Events_Id_ExpectedPojo(200, "Success", dataPOJO);

        Assert.assertEquals(expBodyPOJO.getStatus(), idRespPOJO.getStatus());
        Assert.assertEquals(expBodyPOJO.getMessage(), idRespPOJO.getMessage());
        Assert.assertEquals(expBodyPOJO.getLists().getId(), idRespPOJO.getLists().getId());
        Assert.assertEquals(expBodyPOJO.getLists().getTitle(), idRespPOJO.getLists().getTitle());
        Assert.assertEquals(expBodyPOJO.getLists().getEvent_for(), idRespPOJO.getLists().getEvent_for());
        Assert.assertEquals(expBodyPOJO.getLists().getSession_id(), idRespPOJO.getLists().getSession_id());
        Assert.assertEquals(expBodyPOJO.getLists().getClass_id(), idRespPOJO.getLists().getClass_id());
        Assert.assertEquals(expBodyPOJO.getLists().getSection(), idRespPOJO.getLists().getSection());
        Assert.assertEquals(expBodyPOJO.getLists().getFrom_date(), idRespPOJO.getLists().getFrom_date());
        Assert.assertEquals(expBodyPOJO.getLists().getTo_date(), idRespPOJO.getLists().getTo_date());
        Assert.assertEquals(expBodyPOJO.getLists().getNote(), idRespPOJO.getLists().getNote());
        Assert.assertEquals(expBodyPOJO.getLists().getPhoto(), idRespPOJO.getLists().getPhoto());
        Assert.assertEquals(expBodyPOJO.getLists().getIs_active(), idRespPOJO.getLists().getIs_active());
        Assert.assertEquals(expBodyPOJO.getLists().getEvent_notification_message(), idRespPOJO.getLists().getEvent_notification_message());
        Assert.assertEquals(expBodyPOJO.getLists().getShow_onwebsite(), idRespPOJO.getLists().getShow_onwebsite());

    }


    @Given("POST Request for booksAdd")
    public void post_request_for_books_add() {
        // BERKE

        BooksAdd_PostReqBodyPOJO reqBodyPOJO = new BooksAdd_PostReqBodyPOJO(ConfigReader.getProperty("booksAdd_book_title"),
                ConfigReader.getProperty("booksAdd_book_no"),
                ConfigReader.getProperty("booksAdd_isbn_no"),
                ConfigReader.getProperty("booksAdd_subject"),
                ConfigReader.getProperty("booksAdd_rack_no"),
                ConfigReader.getProperty("booksAdd_publish"),
                ConfigReader.getProperty("booksAdd_author"),
                ConfigReader.getProperty("booksAdd_qty"),
                ConfigReader.getProperty("booksAdd_perunitcost"),
                ConfigReader.getProperty("booksAdd_postdate"),
                ConfigReader.getProperty("booksAdd_description"));

        API_ReusableMethods.postRequest(reqBodyPOJO);
    }

    @Given("Response body content check test for bookAdd")
    public void response_body_content_check_test_for_book_add() {
        //BERKE

        // for sending add request and take addId number
        Response addResponse = API_ReusableMethods.postResponse;
        BooksAdd_ExpBodyPOJO addRespPOJO = addResponse.as(BooksAdd_ExpBodyPOJO.class);

        addIdNoForBooksAdd = addRespPOJO.getAddId();


        // for sending bookId and control if data is true or not
        prepare_url_with_path_parameters("api/booksId");
        prepare_token("admin", "berke", "CorrectPassword");

        BooksId_PostReqBodyPOJO reqBodyPOJO = new BooksId_PostReqBodyPOJO(addIdNoForBooksAdd);
        API_ReusableMethods.postRequest(reqBodyPOJO);

        Response idResponse = API_ReusableMethods.postResponse;
        BooksId_ExpBodyPOJO idRespPOJO = idResponse.as(BooksId_ExpBodyPOJO.class);

        date = idRespPOJO.getLists().getCreated_at();
        String qtyNumber = idRespPOJO.getLists().getQty();

        Books_DataPOJO dataPOJO = new Books_DataPOJO(addIdNoForBooksAdd,
                ConfigReader.getProperty("booksAdd_book_title"),
                ConfigReader.getProperty("booksAdd_book_no"),
                ConfigReader.getProperty("booksAdd_isbn_no"),
                ConfigReader.getProperty("booksAdd_subject"),
                ConfigReader.getProperty("booksAdd_rack_no"),
                ConfigReader.getProperty("booksAdd_publish"),
                ConfigReader.getProperty("booksAdd_author"),
                qtyNumber,
                ConfigReader.getProperty("booksAdd_perunitcost"),
                ConfigReader.getProperty("booksAdd_postdate"),
                ConfigReader.getProperty("booksAdd_description"),
                null,
                null,
                date,
                null);

        BooksId_ExpBodyPOJO expBodyPOJO = new BooksId_ExpBodyPOJO(200, "Success", dataPOJO);

        Assert.assertEquals(expBodyPOJO.getStatus(), idRespPOJO.getStatus());
        Assert.assertEquals(expBodyPOJO.getMessage(), idRespPOJO.getMessage());
        Assert.assertEquals(expBodyPOJO.getLists().getId(), idRespPOJO.getLists().getId());
        Assert.assertEquals(expBodyPOJO.getLists().getBook_title(), idRespPOJO.getLists().getBook_title());
        Assert.assertEquals(expBodyPOJO.getLists().getBook_no(), idRespPOJO.getLists().getBook_no());
        Assert.assertEquals(expBodyPOJO.getLists().getIsbn_no(), idRespPOJO.getLists().getIsbn_no());
        Assert.assertEquals(expBodyPOJO.getLists().getSubject(), idRespPOJO.getLists().getSubject());
        Assert.assertEquals(expBodyPOJO.getLists().getRack_no(), idRespPOJO.getLists().getRack_no());
        Assert.assertEquals(expBodyPOJO.getLists().getPublish(), idRespPOJO.getLists().getPublish());
        Assert.assertEquals(expBodyPOJO.getLists().getAuthor(), idRespPOJO.getLists().getAuthor());
        Assert.assertEquals(expBodyPOJO.getLists().getQty(), idRespPOJO.getLists().getQty());
        Assert.assertEquals(expBodyPOJO.getLists().getPerunitcost(), idRespPOJO.getLists().getPerunitcost());
        Assert.assertEquals(expBodyPOJO.getLists().getPostdate(), idRespPOJO.getLists().getPostdate());
        Assert.assertEquals(expBodyPOJO.getLists().getDescription(), idRespPOJO.getLists().getDescription());
        Assert.assertEquals(expBodyPOJO.getLists().getAvailable(), idRespPOJO.getLists().getAvailable());
        Assert.assertEquals(expBodyPOJO.getLists().getIs_active(), idRespPOJO.getLists().getIs_active());
        Assert.assertEquals(expBodyPOJO.getLists().getCreated_at(), idRespPOJO.getLists().getCreated_at());
        Assert.assertEquals(expBodyPOJO.getLists().getUpdated_at(), idRespPOJO.getLists().getUpdated_at());


    }


    @Given("POST Request for alumniAdd.")
    public void post_request_for_alumni_add() {

        AlumniAdd_PostExpBodyPOJO reqBodyPojo = new AlumniAdd_PostExpBodyPOJO(51, "frk@gmail.com", "5535525956",
                "Software", "Turkey", "no photo");

        API_ReusableMethods.postRequest(reqBodyPojo);

        Response response = API_ReusableMethods.postResponse;

        int status = response.getStatusCode();

        if (status == 200) {
            JsonPath resJS = response.jsonPath();
            newAddIdForAlumni = resJS.getInt("addId");
            System.out.println(newAddIdForAlumni);

        } else {
        }

    }

    @Given("POST Request for alumniAdd in alumniId.")
    public void post_request_for_alumni_add_in_alumni_id() {

        AlumniId_PostReqBodyPOJO reqBodyPOJO = new AlumniId_PostReqBodyPOJO(newAddIdForAlumni);

        API_ReusableMethods.postRequest(reqBodyPOJO);

    }

    @Given("Patch request for Alumni Update")
    public void patch_request_for_alumni_update() {

        System.out.println(newAddIdForAlumni);
        AlumniUpdate_ExpBodyPOJO reqBodyPojo = new AlumniUpdate_ExpBodyPOJO(newAddIdForAlumni, 52, "frkc@gmail.com", "5535525956",
                "Software/", "Turkey/", "no photo");

        API_ReusableMethods.patchRequest(reqBodyPojo);

        Response response = API_ReusableMethods.patchResponse;
    }

    @Given("Verify that status code {int} and message is {string} for PATCH Request")
    public void verify_that_status_code_and_message_is_for_patch_request(Integer givenCode, String givenMessage) {
        GetResponseExpDataPojo expData = new GetResponseExpDataPojo(givenCode, givenMessage);

        Response response = API_ReusableMethods.patchResponse;

        JsonPath resJS = response.jsonPath();

        Assert.assertEquals(expData.getStatus(), response.getStatusCode());
        Assert.assertEquals(expData.getMessage(), resJS.get("message"));

    }

    @Given("Verify that the updated id in the response body should be same updated id in the reqBody.")
    public void verify_that_the_updated_id_in_the_response_body_should_be_same_updated_id_in_the_req_body() {

        AlumniUpdate_ExpBodyPOJO reqBodyPojo = new AlumniUpdate_ExpBodyPOJO(newAddIdForAlumni, 52, "frkc@gmail.com", "5535525956",
                "Software/", "Turkey/", "no photo");

        Response response = API_ReusableMethods.patchResponse;

        AlumniUpdate_RespBodyPOJO respPOJO = response.as(AlumniUpdate_RespBodyPOJO.class);

        System.out.println(respPOJO.getUpdateId());

        Assert.assertEquals(reqBodyPojo.getId(), respPOJO.getUpdateId());

    }

    @Given("Delete request for Alumni delete.")
    public void delete_request_for_alumni_delete() {
        System.out.println(newAddIdForAlumni);
        AlumniDelete_ReqBodyPOJO reqBodyPojo = new AlumniDelete_ReqBodyPOJO(newAddIdForAlumni);

        API_ReusableMethods.deleteRequest(reqBodyPojo);

    }

    @Given("Verify that status code {int} and message is {string} for DELETE Request")
    public void verify_that_status_code_and_message_is_for_delete_request(Integer givenCode, String givenMessage) {
        GetResponseExpDataPojo expData = new GetResponseExpDataPojo(givenCode, givenMessage);

        Response response = API_ReusableMethods.deleteResponse;

        JsonPath resJS = response.jsonPath();

        Assert.assertEquals(expData.getStatus(), response.getStatusCode());
        Assert.assertEquals(expData.getMessage(), resJS.get("message"));

        try {
            booksDeletedId = resJS.get("DeletedId");
        } catch (Exception e) {

        }
        try {
            alumniDeletedId = resJS.get("DeletedId");
        } catch (Exception e) {

        }
        try {
            alumniEventsDeletedId = resJS.get("DeletedId");
        } catch (Exception e) {

        }

    }

    @Given("POST Request for alumniDelete.")
    public void post_request_for_alumni_delete() {
        AlumniId_PostReqBodyPOJO reqBodyPOJO = new AlumniId_PostReqBodyPOJO(newAddIdForAlumni);

        API_ReusableMethods.postRequest(reqBodyPOJO);

    }


    @Given("PATCH Request for alumniEventsUpdate")
    public void patch_request_for_alumni_events_update() {

        AlumniEventsUpdate_ReqBodyPOJO patchReqBody = new AlumniEventsUpdate_ReqBodyPOJO(addIdNoForEventsAdd,
                "Sports Activite 2", "all", null, "null", "2023-02-14 00:00:00",
                "2023-02-15 23:59:00", "Sports", "Sports", "0");

        API_ReusableMethods.patchRequest(patchReqBody);

    }

    @Given("PATCH Request for booksUpdate")
    public void patch_request_for_books_update() {
        // BERKE

        BooksUpdate_ReqBodyPOJO patchReqBody = new BooksUpdate_ReqBodyPOJO(addIdNoForBooksAdd,
                "berke", "788789", "berke", "berke", "110", "berke",
                "berke", "100", "12.00", "2022-05-04",
                "berke", "berke", "berke");

        API_ReusableMethods.patchRequest(patchReqBody);

    }

    @Given("Response body content check test for bookUpdate")
    public void response_body_content_check_test_for_book_update() {
        // BERKE

        // for sending bookId and control if data is true or not
        prepare_url_with_path_parameters("api/booksId");
        prepare_token("admin", "berke", "CorrectPassword");

        BooksId_PostReqBodyPOJO reqBodyPOJO = new BooksId_PostReqBodyPOJO(addIdNoForBooksAdd);
        API_ReusableMethods.postRequest(reqBodyPOJO);

        Response idResponse = API_ReusableMethods.postResponse;
        BooksId_ExpBodyPOJO idRespPOJO = idResponse.as(BooksId_ExpBodyPOJO.class);

        date = idRespPOJO.getLists().getCreated_at();

        Books_DataPOJO dataPOJO = new Books_DataPOJO(addIdNoForBooksAdd,
                "berke",
                "788789",
                "berke",
                "berke",
                "110",
                "berke",
                "berke",
                "100",
                "12.00",
                "2022-05-04",
                "berke",
                "berke",
                "berke",
                date,
                null);

        BooksId_ExpBodyPOJO expBodyPOJO = new BooksId_ExpBodyPOJO(200, "Success", dataPOJO);

        Assert.assertEquals(expBodyPOJO.getStatus(), idRespPOJO.getStatus());
        Assert.assertEquals(expBodyPOJO.getMessage(), idRespPOJO.getMessage());
        Assert.assertEquals(expBodyPOJO.getLists().getId(), idRespPOJO.getLists().getId());
        Assert.assertEquals(expBodyPOJO.getLists().getBook_title(), idRespPOJO.getLists().getBook_title());
        Assert.assertEquals(expBodyPOJO.getLists().getBook_no(), idRespPOJO.getLists().getBook_no());
        Assert.assertEquals(expBodyPOJO.getLists().getIsbn_no(), idRespPOJO.getLists().getIsbn_no());
        Assert.assertEquals(expBodyPOJO.getLists().getSubject(), idRespPOJO.getLists().getSubject());
        Assert.assertEquals(expBodyPOJO.getLists().getRack_no(), idRespPOJO.getLists().getRack_no());
        Assert.assertEquals(expBodyPOJO.getLists().getPublish(), idRespPOJO.getLists().getPublish());
        Assert.assertEquals(expBodyPOJO.getLists().getAuthor(), idRespPOJO.getLists().getAuthor());
        Assert.assertEquals(expBodyPOJO.getLists().getQty(), idRespPOJO.getLists().getQty());
        Assert.assertEquals(expBodyPOJO.getLists().getPerunitcost(), idRespPOJO.getLists().getPerunitcost());
        Assert.assertEquals(expBodyPOJO.getLists().getPostdate(), idRespPOJO.getLists().getPostdate());
        Assert.assertEquals(expBodyPOJO.getLists().getDescription(), idRespPOJO.getLists().getDescription());
        Assert.assertEquals(expBodyPOJO.getLists().getAvailable(), idRespPOJO.getLists().getAvailable());
        Assert.assertEquals(expBodyPOJO.getLists().getIs_active(), idRespPOJO.getLists().getIs_active());
        Assert.assertEquals(expBodyPOJO.getLists().getCreated_at(), idRespPOJO.getLists().getCreated_at());
        Assert.assertEquals(expBodyPOJO.getLists().getUpdated_at(), idRespPOJO.getLists().getUpdated_at());


    }

    @Given("Verify response updateId matches PATCH request id in booksUpdate.")
    public void verify_response_update_id_matches_patch_request_id_in_books_update() {
        // BERKE

        BooksUpdate_ExpBodyPOJO expBodyPOJO = new BooksUpdate_ExpBodyPOJO(200, "Success", addIdNoForBooksAdd);

        Response response = API_ReusableMethods.patchResponse;
        BooksUpdate_ExpBodyPOJO respPOJO = response.as(BooksUpdate_ExpBodyPOJO.class);

        Assert.assertEquals(expBodyPOJO.getUpdateId(), respPOJO.getUpdateId());
    }

    @Given("DELETE Request for booksDelete")
    public void delete_request_for_books_delete() {
        // BERKE
        BooksId_PostReqBodyPOJO deleteReqBodyPOJO = new BooksId_PostReqBodyPOJO(addIdNoForBooksAdd);

        API_ReusableMethods.deleteRequest(deleteReqBodyPOJO);
    }


    @Given("Verify response updateId matches DELETE request id in booksDelete")
    public void verify_response_update_id_matches_delete_request_id_in_books_delete() {
        // BERKE

        Assert.assertEquals(booksDeletedId, addIdNoForBooksAdd);
    }

    @Given("Response body content check test for booksDelete")
    public void response_body_content_check_test_for_books_delete() {
        // BERKE

        prepare_url_with_path_parameters("api/booksId");
        prepare_token("admin", "berke", "CorrectPassword");

        BooksId_PostReqBodyPOJO reqBodyPOJO = new BooksId_PostReqBodyPOJO(addIdNoForBooksAdd);
        API_ReusableMethods.postRequest(reqBodyPOJO);


        verify_that_status_code_and_message_is_for_post_request(403, "failed");


    }


    @Given("Response body content check test for visitorsList")
    public void response_body_content_check_test_for_visitors_list() {

        String purpose = "FREE fast ranks for wonderworldcollege.com";
        String name = "Mike Creighton";
        String email = "altunmetehan01@gmail.com";
        String note = "Hi there Just checked your wonderworldcollege.com baclink profile, I noticed a moderate percentage of toxic links pointing to your website We will investigate each link for its toxicity and perform a professional clean up for you free of charge. Start recovering your ranks today: https://www.hilkom-digital.de/professional-linksprofile-clean-up-service/ Regards Mike CreightonHilkom Digital SEO Experts https://www.hilkom-digital.de/ (Sent from online front site)";

        Visitors_DataPOJO visitorsDataPOJO = new Visitors_DataPOJO("250", null, null,
                "Online", purpose, name, email, "", "", "0", "2023-07-30",
                "", "", note, null, "", "2023-07-23 09:14:13", null,
                null, null, null, null, null, null,
                null, null, null, null, null);

        List<Visitors_DataPOJO> lists = new ArrayList<>();
        lists.add(visitorsDataPOJO);

        Visitors_ExpBodyPOJO visitorsExpBodyPOJO = new Visitors_ExpBodyPOJO(200, "Success", lists);

        Response response = API_ReusableMethods.getResponse;

        Visitors_ExpBodyPOJO responsePOJO = response.as(Visitors_ExpBodyPOJO.class);

        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getId(), responsePOJO.getLists().get(228).getId());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getStaff_id(), responsePOJO.getLists().get(228).getStaff_id());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getStudent_session_id(), responsePOJO.getLists().get(228).getStudent_session_id());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getSource(), responsePOJO.getLists().get(228).getSource());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getPurpose(), responsePOJO.getLists().get(228).getPurpose());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getName(), responsePOJO.getLists().get(228).getName());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getEmail(), responsePOJO.getLists().get(228).getEmail());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getContact(), responsePOJO.getLists().get(228).getContact());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getId_proof(), responsePOJO.getLists().get(228).getId_proof());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getNo_of_people(), responsePOJO.getLists().get(228).getNo_of_people());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getDate(), responsePOJO.getLists().get(228).getDate());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getIn_time(), responsePOJO.getLists().get(228).getIn_time());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getOut_time(), responsePOJO.getLists().get(228).getOut_time());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getNote(), responsePOJO.getLists().get(228).getNote());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getImage(), responsePOJO.getLists().get(228).getImage());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getMeeting_with(), responsePOJO.getLists().get(228).getMeeting_with());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getCreated_at(), responsePOJO.getLists().get(228).getCreated_at());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getSection(), responsePOJO.getLists().get(228).getSection());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getStaff_name(), responsePOJO.getLists().get(228).getStaff_name());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getStaff_surname(), responsePOJO.getLists().get(228).getStaff_surname());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getStaff_employee_id(), responsePOJO.getLists().get(228).getStaff_employee_id());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getClass_id(), responsePOJO.getLists().get(228).getClass_id());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getSection_id(), responsePOJO.getLists().get(228).getSection_id());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getStudents_id(), responsePOJO.getLists().get(228).getStudents_id());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getAdmission_no(), responsePOJO.getLists().get(228).getAdmission_no());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getStudent_firstname(), responsePOJO.getLists().get(228).getStudent_firstname());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getStudent_middlename(), responsePOJO.getLists().get(228).getStudent_middlename());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getStudent_lastname(), responsePOJO.getLists().get(228).getStudent_lastname());
        assertEquals(visitorsExpBodyPOJO.getLists().get(0).getRole_id(), responsePOJO.getLists().get(228).getRole_id());
    }

    @Given("POST Request for visitorsId")
    public void post_request_for_visitors_id() {
        VisitorsId_ReqBodyPOJO reqBodyPOJO = new VisitorsId_ReqBodyPOJO(570);
        API_ReusableMethods.postRequest(reqBodyPOJO);
    }

    @Given("Response body content check test for visitorsId")
    public void response_body_content_check_test_for_visitors_id() {

        Visitors_DataPOJO dataPOJO = new Visitors_DataPOJO("570", null, null, null,
                "Principal Meeting", "Burkay", null, "17682285995", "12345",
                "16", "2023-08-16", "22:00 PM", "06:30 PM", "PTM meeting", null,
                "", "2023-08-14 20:13:06", null, null, null,
                null, null, null, null, null,
                null, null, null,
                null);

        List<Visitors_DataPOJO> lists = new ArrayList<>();
        lists.add(dataPOJO);

        Visitors_ExpBodyPOJO expBodyPOJO = new Visitors_ExpBodyPOJO(200, "Success", lists);

        Response response = API_ReusableMethods.getResponse;

        Visitors_ExpBodyPOJO responsePOJO = response.as(Visitors_ExpBodyPOJO.class);

        assertEquals(expBodyPOJO.getStatus(), responsePOJO.getStatus());
        assertEquals(expBodyPOJO.getMessage(), responsePOJO.getMessage());
        assertEquals(expBodyPOJO.getLists().get(0).getId(), responsePOJO.getLists().get(0).getId());
        assertEquals(expBodyPOJO.getLists().get(0).getStaff_id(), responsePOJO.getLists().get(0).getStaff_id());
        assertEquals(expBodyPOJO.getLists().get(0).getStudent_session_id(), responsePOJO.getLists().get(0).getStudent_session_id());
        assertEquals(expBodyPOJO.getLists().get(0).getSource(), responsePOJO.getLists().get(0).getSource());
        assertEquals(expBodyPOJO.getLists().get(0).getPurpose(), responsePOJO.getLists().get(0).getPurpose());
        assertEquals(expBodyPOJO.getLists().get(0).getName(), responsePOJO.getLists().get(0).getName());
        assertEquals(expBodyPOJO.getLists().get(0).getEmail(), responsePOJO.getLists().get(0).getEmail());
        assertEquals(expBodyPOJO.getLists().get(0).getContact(), responsePOJO.getLists().get(0).getContact());
        assertEquals(expBodyPOJO.getLists().get(0).getId_proof(), responsePOJO.getLists().get(0).getId_proof());
        assertEquals(expBodyPOJO.getLists().get(0).getNo_of_people(), responsePOJO.getLists().get(0).getNo_of_people());
        assertEquals(expBodyPOJO.getLists().get(0).getDate(), responsePOJO.getLists().get(0).getDate());
        assertEquals(expBodyPOJO.getLists().get(0).getIn_time(), responsePOJO.getLists().get(0).getIn_time());
        assertEquals(expBodyPOJO.getLists().get(0).getOut_time(), responsePOJO.getLists().get(0).getOut_time());
        assertEquals(expBodyPOJO.getLists().get(0).getNote(), responsePOJO.getLists().get(0).getNote());
        assertEquals(expBodyPOJO.getLists().get(0).getImage(), responsePOJO.getLists().get(0).getImage());
        assertEquals(expBodyPOJO.getLists().get(0).getMeeting_with(), responsePOJO.getLists().get(0).getMeeting_with());
        assertEquals(expBodyPOJO.getLists().get(0).getCreated_at(), responsePOJO.getLists().get(0).getCreated_at());
        assertEquals(expBodyPOJO.getLists().get(0).getSection(), responsePOJO.getLists().get(0).getSection());
        assertEquals(expBodyPOJO.getLists().get(0).getStaff_name(), responsePOJO.getLists().get(0).getStaff_name());
        assertEquals(expBodyPOJO.getLists().get(0).getStaff_surname(), responsePOJO.getLists().get(0).getStaff_surname());
        assertEquals(expBodyPOJO.getLists().get(0).getStaff_employee_id(), responsePOJO.getLists().get(0).getStaff_employee_id());
        assertEquals(expBodyPOJO.getLists().get(0).getClass_id(), responsePOJO.getLists().get(0).getClass_id());
        assertEquals(expBodyPOJO.getLists().get(0).getSection_id(), responsePOJO.getLists().get(0).getSection_id());
        assertEquals(expBodyPOJO.getLists().get(0).getStudents_id(), responsePOJO.getLists().get(0).getStudents_id());
        assertEquals(expBodyPOJO.getLists().get(0).getAdmission_no(), responsePOJO.getLists().get(0).getAdmission_no());
        assertEquals(expBodyPOJO.getLists().get(0).getStudent_firstname(), responsePOJO.getLists().get(0).getStudent_firstname());
        assertEquals(expBodyPOJO.getLists().get(0).getStudent_middlename(), responsePOJO.getLists().get(0).getStudent_middlename());
        assertEquals(expBodyPOJO.getLists().get(0).getStudent_lastname(), responsePOJO.getLists().get(0).getStudent_lastname());
        assertEquals(expBodyPOJO.getLists().get(0).getRole_id(), responsePOJO.getLists().get(0).getRole_id());
    }

    @Given("Verify response updateId matches PATCH request id in alumniEventsUpdate.")
    public void verify_response_update_id_matches_patch_request_id_in_alumni_events_update() {

        AlumniEventsUpdate_ExpBodyPOJO expBodyPOJO = new AlumniEventsUpdate_ExpBodyPOJO(200, "Success", addIdNoForEventsAdd);

        Response response = API_ReusableMethods.patchResponse;
        AlumniEventsUpdate_ExpBodyPOJO respPOJO = response.as(AlumniEventsUpdate_ExpBodyPOJO.class);

        Assert.assertEquals(expBodyPOJO.getUpdateId(), respPOJO.getUpdateId());
    }

    @Given("Response body content check test for alumniEventsUpdate")
    public void response_body_content_check_test_for_alumni_events_update() {

        // for sending id and control if data is true or not

        prepare_url_with_path_parameters("api/alumniEventsId");
        prepare_token("admin", "hilal", "CorrectPassword");

        AlumniEventsId_PostReqBodyPOJO reqBodyPOJO = new AlumniEventsId_PostReqBodyPOJO(addIdNoForEventsAdd);
        API_ReusableMethods.postRequest(reqBodyPOJO);

        Response idResponse = API_ReusableMethods.postResponse;

        Alumni_Events_Id_ExpectedPojo idRespPOJO = idResponse.as(Alumni_Events_Id_ExpectedPojo.class);

        String date = idRespPOJO.getLists().getCreated_at();

        Alumni_Event_Id_DataPojo dataPOJO = new Alumni_Event_Id_DataPojo(addIdNoForEventsAdd, "Sports Activite 2",
                "all", null, null, "null", "2023-02-14 00:00:00",
                "2023-02-15 23:59:00", "Sports", null, "0", "Sports",
                "0", date);


        Alumni_Events_Id_ExpectedPojo expBodyPOJO = new Alumni_Events_Id_ExpectedPojo(200, "Success", dataPOJO);

        Assert.assertEquals(expBodyPOJO.getStatus(), idRespPOJO.getStatus());
        Assert.assertEquals(expBodyPOJO.getMessage(), idRespPOJO.getMessage());
        Assert.assertEquals(expBodyPOJO.getLists().getId(), idRespPOJO.getLists().getId());
        Assert.assertEquals(expBodyPOJO.getLists().getTitle(), idRespPOJO.getLists().getTitle());
        Assert.assertEquals(expBodyPOJO.getLists().getEvent_for(), idRespPOJO.getLists().getEvent_for());
        Assert.assertEquals(expBodyPOJO.getLists().getSession_id(), idRespPOJO.getLists().getSession_id());
        Assert.assertEquals(expBodyPOJO.getLists().getClass_id(), idRespPOJO.getLists().getClass_id());
        Assert.assertEquals(expBodyPOJO.getLists().getSection(), idRespPOJO.getLists().getSection());
        Assert.assertEquals(expBodyPOJO.getLists().getFrom_date(), idRespPOJO.getLists().getFrom_date());
        Assert.assertEquals(expBodyPOJO.getLists().getTo_date(), idRespPOJO.getLists().getTo_date());
        Assert.assertEquals(expBodyPOJO.getLists().getNote(), idRespPOJO.getLists().getNote());
        Assert.assertEquals(expBodyPOJO.getLists().getPhoto(), idRespPOJO.getLists().getPhoto());
        Assert.assertEquals(expBodyPOJO.getLists().getIs_active(), idRespPOJO.getLists().getIs_active());
        Assert.assertEquals(expBodyPOJO.getLists().getEvent_notification_message(), idRespPOJO.getLists().getEvent_notification_message());
        Assert.assertEquals(expBodyPOJO.getLists().getShow_onwebsite(), idRespPOJO.getLists().getShow_onwebsite());

    }

    @Given("DELETE Request for alumniEventsDelete")
    public void delete_request_for_alumni_events_delete() {

        AlumniEvents_PostReqBodyPOJO deleteReqBodyPOJO = new AlumniEvents_PostReqBodyPOJO(addIdNoForEventsAdd);

        API_ReusableMethods.deleteRequest(deleteReqBodyPOJO);
    }

    @Given("Verify response updateId matches DELETE request id in alumniEventsDelete")
    public void verify_response_update_id_matches_delete_request_id_in_alumni_events_delete() {

        Assert.assertEquals(alumniEventsDeletedId, addIdNoForEventsAdd);
    }

    @Given("Response body content check test for alumniEventsDelete")
    public void response_body_content_check_test_for_alumni_events_delete() {

        prepare_url_with_path_parameters("api/alumniEventsDelete");
        prepare_token("admin", "hilal", "CorrectPassword");

        AlumniEvents_PostReqBodyPOJO reqBodyPOJO = new AlumniEvents_PostReqBodyPOJO(addIdNoForEventsAdd);
        API_ReusableMethods.postRequest(reqBodyPOJO);

        verify_that_status_code_and_message_is_for_post_request(403, "failed");
    }


    @Given("A valid DELETE request is sent to the api-bookIssueDelete endpoint with proper authorization credentials and correct data included")
    public void a_valid_delete_request_is_sent_to_the_api_book_ıssue_delete_endpoint_with_proper_authorization_credentials_and_correct_data_included() {

        // create request bug oldugu icin yeni bir book issue create edip onu silemiyorum
        // mevcutlardan silinicek

        JSONObject requestBody = new JSONObject();

        requestBody.put("id", 536);

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(requestBody.toString())
                .header("Authorization", Authentication.token)
                .delete(fullPath);

        response.prettyPrint();
    }

    @Given("The status code of the response from the api-bookIssueDelete endpoint is verified to be {int}, and the message information in the body is confirmed to be {string}")
    public void the_status_code_of_the_response_from_the_api_book_ıssue_delete_endpoint_is_verified_to_be_and_the_information_in_the_body_is_confirmed_to_be(Integer code, String message) {

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("An invalid DELETE request with incorrect data id is sent to the api-bookIssueDelete endpoint")
    public void an_invalid_delete_request_with_incorrect_data_id_is_sent_to_the_api_book_ıssue_delete_endpoint() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("id", 156);

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(requestBody.toString())
                .header("Authorization", Authentication.token)
                .delete(fullPath);

        response.prettyPrint();
    }

    @Given("It is verified that the status code of the response with an invalid id returned from the api-bookIssueDelete endpoint is {int}, and the message information in the response body is {string}")
    public void ıt_is_verified_that_the_status_code_of_the_response_with_an_invalid_id_returned_from_the_api_book_ıssue_delete_endpoint_is_and_the_message_information_in_the_response_body_is
            (Integer code, String message) {
        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));
    }

    @Given("An invalid DELETE request with incorrect Authorization is sent to the api-bookIssueDelete endpoint")
    public void an_invalid_delete_request_with_incorrect_authorization_is_sent_to_the_api_book_ıssue_delete_endpoint() {

        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        try {
            JSONObject requestBody = new JSONObject();

            requestBody.put("id", 537);

            response = given()
                    .contentType(ContentType.JSON)
                    .spec(spec)
                    .when()
                    .body(requestBody.toString())
                    .header("Authorization", "incorrect Token")
                    .delete(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }
    }

    @Given("It is confirmed that the status code of the response with invalid Authorization returned from the api-bookIssueDelete endpoint is {int}, and the message information in the response body is {string}")
    public void ıt_is_confirmed_that_the_status_code_of_the_response_with_invalid_authorization_returned_from_the_api_book_ıssue_delete_endpoint_is_and_the_message_information_in_the_response_body_is
            (Integer code, String message) {
        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));
    }

    @Given("A DELETE request is sent to the api-bookIssueDelete endpoint")
    public void a_delete_request_is_sent_to_the_api_book_ıssue_delete_endpoint() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("id", 41);

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(requestBody.toString())
                .header("Authorization", Authentication.token)
                .delete(fullPath);

        response.prettyPrint();
    }

    @Given("It is confirmed that the DeletedId in the response from the api-bookIssueDelete endpoint matches the id information in the request body")
    public void ıt_is_confirmed_that_the_deleted_ıd_in_the_response_from_the_api_book_ıssue_delete_endpoint_matches_the_id_information_in_the_request_body() {

        JSONObject requestBody = new JSONObject();

        requestBody.put("id", 155);

        response.then()
                .assertThat()
                .body("DeletedId", Matchers.equalTo(requestBody.get("id")));
    }

    @Given("The DeletedId information from the response body of the api-bookIssueDelete endpoint is used to prepare a request body, which is then sent as a POST request to the api-bookIssueId endpoint")
    public void the_deleted_ıd_information_from_the_response_body_of_the_api_book_ıssue_delete_endpoint_is_used_to_prepare_a_request_body_which_is_then_sent_as_a_post_request_to_the_api_book_ıssue_ıd_endpoint() {

        JSONObject requestBody = new JSONObject();

        requestBody.put("id", 41);

        String url = "https://qa.wonderworldcollege.com/api/bookIssueId";

        response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody.toString())
                .post(url);

        response.prettyPrint();

    }

    @Given("The status code of the response from the api-bookIssueId endpoint is verified to be {int}, and the message information in the body is confirmed to be {string}")
    public void the_status_code_of_the_response_from_the_api_book_ıssue_ıd_endpoint_is_verified_to_be_and_the_message_information_in_the_body_is_confirmed_to_be(Integer code, String message) {

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));
    }

    @Given("A GET request is sent to the api-alumniList endpoint")
    public void a_get_request_is_sent_to_the_api_alumni_list_endpoint() {

        response = given()
                .spec(spec)
                .when()
                .header("Authorization", Authentication.token)
                .get(fullPath);


    }

    @Given("It should be verified that the status code of the response from the api-alumniList endpoint is {int}, and the message information in the response body is {string}")
    public void ıt_should_be_verified_that_the_status_code_of_the_response_from_the_api_alumni_list_endpoint_is_and_the_message_information_in_the_response_body_is(Integer code, String message) {

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));


    }

    @Given("An invalid GET request with incorrect authorization is sent to the api-alumniList endpoint")
    public void an_invalid_get_request_with_incorrect_authorization_is_sent_to_the_api_alumni_list_endpoint() {

        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);


        try {
            response = given()
                    .spec(spec)
                    .when()
                    .header("Authorization", "Bearer incorrectToken")
                    .get(fullPath);

        } catch (Exception innerException) {
            innerException.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }


        response.prettyPrint();
    }

    @Given("Send a GET request to the api-alumniList endpoint, and verify that the data in the returned response matches the expected data")
    public void send_a_get_request_to_the_api_alumni_list_endpoint_and_verify_that_the_data_in_the_returned_response_matches_the_expected_data() {

        JSONObject expectedData = new JSONObject();

        expectedData.put("id", "2");
        expectedData.put("student_id", "41");
        expectedData.put("current_email", "rohan@gmail.com");
        expectedData.put("current_phone", "0808080707");
        expectedData.put("occupation", "");
        expectedData.put("address", "");
        expectedData.put("photo", "");
        expectedData.put("created_at", "2023-03-11 03:04:50");

        response = given()
                .spec(spec)
                .when()
                .header("Authorization", Authentication.token)
                .get(fullPath);


        JsonPath responseJp = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseJp.get("lists[0].id"), expectedData.get("id"));
        softAssert.assertEquals(responseJp.get("lists[0].student_id"), expectedData.get("student_id"));
        softAssert.assertEquals(responseJp.get("lists[0].current_email"), expectedData.get("current_email"));
        softAssert.assertEquals(responseJp.get("lists[0].current_phone"), expectedData.get("current_phone"));
        softAssert.assertEquals(responseJp.get("lists[0].occupation"), expectedData.get("occupation"));
        softAssert.assertEquals(responseJp.get("lists[0].address"), expectedData.get("address"));
        softAssert.assertEquals(responseJp.get("lists[0].photo"), "");
        softAssert.assertEquals(responseJp.get("lists[0].created_at"), expectedData.get("created_at"));

        softAssert.assertAll();

    }

    @Given("When a GET request is sent to the api-sessionList endpoint with valid authorization credentials, it is verified that the returned response has a status code of {int} and the message information in the body is confirmed to be {string}")
    public void when_a_get_request_is_sent_to_the_api_session_list_endpoint_with_valid_authorization_credentials_it_is_verified_that_the_returned_response_has_a_status_code_of_and_the_message_information_in_the_body_is_confirmed_to_be
            (Integer code, String message) {

        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        try {

            response = given()
                    .spec(spec)
                    .when()
                    .header("Authorization", Authentication.token)
                    .get(fullPath);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }

        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("When a GET request with invalid authorization credentials is sent to the api-sessionList endpoint, it is verified that the returned response has a status code of {int} and the message information in the body is confirmed to be {string}")
    public void when_a_get_request_with_invalid_authorization_credentials_is_sent_to_the_api_session_list_endpoint_it_is_verified_that_the_returned_response_has_a_status_code_of_and_the_message_information_in_the_body_is_confirmed_to_be
            (Integer code, String message) {


        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        try {

            response = given()
                    .spec(spec)
                    .when()
                    .header("Authorization", "incorrect Token")
                    .get(fullPath);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("When a GET request is sent to the api-sessionList endpoint with valid authorization credentials, the returned response should be verified to match the expected data")
    public void when_a_get_request_is_sent_to_the_api_session_list_endpoint_with_valid_authorization_credentials_the_returned_response_should_be_verified_to_match_the_expected_data() {


        JSONObject expectedData = new JSONObject();

        expectedData.put("id", "11");
        expectedData.put("session", "2017-18");
        expectedData.put("is_active", "no");
        expectedData.put("created_at", "2017-04-20 02:41:37");
        expectedData.put("updated_at", "0000-00-00");

        response = given()
                .spec(spec)
                .when()
                .header("Authorization", Authentication.token)
                .get(fullPath);

        JsonPath reponseJp = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(reponseJp.get("lists[1].id"), expectedData.get("id"));
        softAssert.assertEquals(reponseJp.get("lists[1].session"), expectedData.get("session"));
        softAssert.assertEquals(reponseJp.get("lists[1].is_active"), expectedData.get("is_active"));
        softAssert.assertEquals(reponseJp.get("lists[1].created_at"), expectedData.get("created_at"));
        softAssert.assertEquals(reponseJp.get("lists[1].updated_at"), expectedData.get("updated_at"));

        softAssert.assertAll();

    }

    @Given("a POST request is sent to the api-alumniEventsByDateRange endpoint with valid authorization credentials, it is verified that the returned response has a status code of {int} and the message information in the body is confirmed to be {string}")
    public void a_post_request_is_sent_to_the_api_alumni_events_by_date_range_endpoint_with_valid_authorization_credentials_it_is_verified_that_the_returned_response_has_a_status_code_of_and_the_message_information_in_the_body_is_confirmed_to_be
            (Integer code, String message) {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("start", "2021-03-03 00:00:00");
        requestBody.put("end", "2023-03-03 00:00:00");

        response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(requestBody)
                .header("Authorization", Authentication.token)
                .post(fullPath);

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

    }

    @Given("when a POST request is sent to the api-alumniEventsByDateRange endpoint with invalid authorization credentials, it is verified that the returned response has a status code of {int} and the message information in the body is confirmed to be {string}")
    public void when_a_post_request_is_sent_to_the_api_alumni_events_by_date_range_endpoint_with_invalid_authorization_credentials_it_is_verified_that_the_returned_response_has_a_status_code_of_and_the_message_information_in_the_body_is_confirmed_to_be
            (Integer code, String message) {

        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("start", "2018-03-03 00:00:00");
        requestBody.put("end", "2019-03-03 00:00:00");

        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .spec(spec)
                    .when()
                    .header("Authorization", "incorrect Token")
                    .body(requestBody)
                    .post(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

        response.prettyPrint();

    }

    @Given("When a POST request with invalid request body information is sent to the api-alumniEventsByDateRange endpoint, it is verified that the returned response has a status code of {int} and the message information in the body is confirmed to be {string}")
    public void when_a_post_request_with_invalid_request_body_information_is_sent_to_the_api_alumni_events_by_date_range_endpoint_it_is_verified_that_the_returned_response_has_a_status_code_of_and_the_message_information_in_the_body_is_confirmed_to_be
            (Integer code, String message) {

        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("start", "2018-03-03 00:00:00");

        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .spec(spec)
                    .when()
                    .header("Authorization", Authentication.token)
                    .body(requestBody)
                    .post(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }

        response.then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));

        response.prettyPrint();

    }

    @Given("when a POST request with valid authorization credentials and correct data included in the POST body is sent to the api-alumniEventsByDateRange endpoint, the data within the list in the returned response body should be verified to match the data in the expected data")
    public void when_a_post_request_with_valid_authorization_credentials_and_correct_data_included_in_the_post_body_is_sent_to_the_api_alumni_events_by_date_range_endpoint_the_data_within_the_list_in_the_returned_response_body_should_be_verified_to_match_the_data_in_the_expected_data() {

        JSONObject requestBody = new JSONObject();

        requestBody.put("start", "2017-03-03 00:00:00");
        requestBody.put("end", "2018-03-03 00:00:00");

        JSONObject expectedDate = new JSONObject();

        expectedDate.put("id", "1");
        expectedDate.put("title", "Covid-19 Seminar");
        expectedDate.put("event_for", "class");
        expectedDate.put("session_id", "16");
        expectedDate.put("class_id", "1");
        expectedDate.put("section", "[\\\"1\\\"]");
        expectedDate.put("from_date", "2021-03-01 00:00:00");
        expectedDate.put("to_date", "2021-03-16 00:00:00");
        expectedDate.put("note", "COVID-19 is the disease caused by a new coronavirus called SARS-CoV-2.  " +
                "WHO first learned of this new virus on 31 December 2019, following a report of a " +
                "cluster of cases of â€˜viral pneumoniaâ€™ in Wuhan, Peopleâ€™s Republic of China.");
        expectedDate.put("photo", "");
        expectedDate.put("is_active", "0");
        expectedDate.put("event_notification_message",
                "GAVI'S RESPONSE\\r\\nRespond and protect: " +
                        "With COVID-19 now reported in almost all Gavi-eligible countries, " +
                        "the Vaccine Alliance is providing immediate funding to health systems, " +
                        "enabling countries to protect health care workers, perform vital surveillance and training, " +
                        "and purchase diagnostic tests.\\r\\n\\r\\nMaintain, restore and strengthen: " +
                        "Gavi will support countries to adapt and restart immunisation services, " +
                        "rebuild community trust and catch up those who have been missed both before a" +
                        "nd during the pandemic, while also investing in strengthening immunisation systems " +
                        "to be more resilient and responsive to the communities they serve.\\r\\n\\r\\nEnsure " +
                        "global equitable access: Gavi is co-leading COVAX, the global effort to securing a global " +
                        "response to COVID-19 that is effective and fair, using its unique expertise to help identify " +
                        "and rapidly accelerate development, production and delivery of COVID-19 vaccines so that anyone " +
                        "that needs them, gets them.");

        expectedDate.put("show_onwebsite", "0");
        expectedDate.put("created_at", "2021-03-23 02:54:29");


        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody.toString())
                .header("Authorization", Authentication.token)
                .post(fullPath);

        JsonPath responseJp = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseJp.get("lists[0].id"), expectedDate.get("id"));
        softAssert.assertEquals(responseJp.get("lists[0].title"), expectedDate.get("title"));
        softAssert.assertEquals(responseJp.get("lists[0].event_for"), expectedDate.get("event_for"));
        softAssert.assertEquals(responseJp.get("lists[0].session_id"), expectedDate.get("session_id"));
        softAssert.assertEquals(responseJp.get("lists[0].class_id"), expectedDate.get("class_id"));
        softAssert.assertEquals(responseJp.get("lists[0].section"), expectedDate.get("section"));
        softAssert.assertEquals(responseJp.get("lists[0].from_date"), expectedDate.get("from_date"));
        softAssert.assertEquals(responseJp.get("lists[0].to_date"), expectedDate.get("to_date"));
        softAssert.assertEquals(responseJp.get("lists[0].note"), expectedDate.get("note"));
        softAssert.assertEquals(responseJp.get("lists[0].photo"), expectedDate.get("photo"));
        softAssert.assertEquals(responseJp.get("lists[0].is_active"), expectedDate.get("is_active"));
        softAssert.assertEquals(responseJp.get("lists[0].event_notification_message"), expectedDate.get("event_notification_message"));
        softAssert.assertEquals(responseJp.get("lists[0].show_onwebsite"), expectedDate.get("show_onwebsite"));
        softAssert.assertEquals(responseJp.get("lists[0].created_at"), expectedDate.get("created_at"));

        softAssert.assertAll();


    }

    //API_43-1
    @Given("Verify the content of the list with specified data.")
    public void verify_the_content_of_the_list_with_specified_data() {

        TeacherHomeworkList_DataPojo dataHomework = new TeacherHomeworkList_DataPojo("151", "1", "1", "1", "18", "5", "21", "1", "2022-07-05", "2022-07-08", null, "<p>\\r\\n\\r\\nRead carefully\\r\\n\\r\\n<br></p>," +
                "2022-07-01", "0000-00-00", "", "5", null, "2023-01-18 01:07:13", "A", "English", "4", "Class 1st Subject Group", "0");

        List<TeacherHomeworkList_DataPojo> lists = new ArrayList<>();
        lists.add(dataHomework);

        TeacherHomeworkList_ExpBodyPojo expPojo = new TeacherHomeworkList_ExpBodyPojo(200, "Success", lists);

        Response response = API_ReusableMethods.getResponse;

        TeacherHomeworkList_ExpBodyPojo respPojo = response.as(TeacherHomeworkList_ExpBodyPojo.class);

        int index = 0;
        for (int i = 0; i < respPojo.getLists().size(); i++) {
            if (respPojo.getLists().get(i).getId().equals(expPojo.getLists().get(0).getId())) {
                index = i;
            }

            Assert.assertEquals(expPojo.getStatus(), respPojo.getStatus());
            Assert.assertEquals(expPojo.getMessage(), respPojo.getMessage());

            Assert.assertEquals(expPojo.getLists().get(index).getId(), respPojo.getLists().get(0).getId());
            Assert.assertEquals(expPojo.getLists().get(index).getClass_id(), respPojo.getLists().get(0).getClass_id());
            Assert.assertEquals(expPojo.getLists().get(index).getSection_id(), respPojo.getLists().get(0).getSection_id());
            Assert.assertEquals(expPojo.getLists().get(index).getStaff_id(), respPojo.getLists().get(0).getStaff_id());
            Assert.assertEquals(expPojo.getLists().get(index).getSubject_group_subject_id(), respPojo.getLists().get(0).getSubject_group_subject_id());
            Assert.assertEquals(expPojo.getLists().get(index).getSubject_id(), respPojo.getLists().get(0).getSubject_id());
            Assert.assertEquals(expPojo.getLists().get(index).getHomework_date(), respPojo.getLists().get(0).getHomework_date());
            Assert.assertEquals(expPojo.getLists().get(index).getSubmit_date(), respPojo.getLists().get(0).getSubmit_date());
            Assert.assertEquals(expPojo.getLists().get(index).getMarks(), respPojo.getLists().get(0).getMarks());
            Assert.assertEquals(expPojo.getLists().get(index).getDescription(), respPojo.getLists().get(0).getDescription());
            Assert.assertEquals(expPojo.getLists().get(index).getCreate_date(), respPojo.getLists().get(0).getCreate_date());
            Assert.assertEquals(expPojo.getLists().get(index).getEvaluation_date(), respPojo.getLists().get(0).getEvaluation_date());
            Assert.assertEquals(expPojo.getLists().get(index).getDocument(), respPojo.getLists().get(0).getDocument());
            Assert.assertEquals(expPojo.getLists().get(index).getCreated_by(), respPojo.getLists().get(0).getCreated_by());
            Assert.assertEquals(expPojo.getLists().get(index).getEvaluated_by(), respPojo.getLists().get(0).getEvaluated_by());
            Assert.assertEquals(expPojo.getLists().get(index).getCreated_at(), respPojo.getLists().get(0).getCreated_at());
            Assert.assertEquals(expPojo.getLists().get(index).getSection(), respPojo.getLists().get(0).getSection());
            Assert.assertEquals(expPojo.getLists().get(index).getSubject_name(), respPojo.getLists().get(0).getSubject_name());
            Assert.assertEquals(expPojo.getLists().get(index).getSubject_groups_id(), respPojo.getLists().get(0).getSubject_groups_id());
            Assert.assertEquals(expPojo.getLists().get(index).getName(), respPojo.getLists().get(0).getName());
            Assert.assertEquals(expPojo.getLists().get(index).getAssignments(), respPojo.getLists().get(0).getAssignments());

        }
    }

    //API_44-1
    @Given("Post request homeworkById")
    public void post_request() {
        TeacherHomeworkById_PostReqBodyPojo idReqBody = new TeacherHomeworkById_PostReqBodyPojo(729);
        API_ReusableMethods.postRequest(idReqBody);

    }

    //44-3
    @Given("Verify the POST response data with expected content of the data")
    public void verify_the_post_response_data_with_expected_content_of_the_data() {

        TeacherHomeworkById_ListsPojo listsPojo = new TeacherHomeworkById_ListsPojo("729", "1", "1", "18", "131", "21", "1", "2023-08-12", "2023-08-12", "80.00",
                "<p>Hello, Thanks...</p>", "2023-08-12", null, null, "131", null,
                "2023-08-12 14:18:56", "English", "4", "Class 1st Subject Group", "0");

        TeacherHomeworkById_ExpBodyPojo expBodyPojo = new TeacherHomeworkById_ExpBodyPojo(200, "Success", listsPojo);
        System.out.println("expectedBody" + expBodyPojo);

        Response response = API_ReusableMethods.postResponse;

        TeacherHomeworkById_ExpBodyPojo respPojo = response.as(TeacherHomeworkById_ExpBodyPojo.class);

        Assert.assertEquals(expBodyPojo.getStatus(), respPojo.getStatus());
        Assert.assertEquals(expBodyPojo.getMessage(), respPojo.getMessage());

        Assert.assertEquals(expBodyPojo.getLists().getId(), respPojo.getLists().getId());
        Assert.assertEquals(expBodyPojo.getLists().getClass_id(), respPojo.getLists().getClass_id());
        Assert.assertEquals(expBodyPojo.getLists().getSection_id(), respPojo.getLists().getSection_id());
        Assert.assertEquals(expBodyPojo.getLists().getSession_id(), respPojo.getLists().getSession_id());
        Assert.assertEquals(expBodyPojo.getLists().getStaff_id(), respPojo.getLists().getStaff_id());
        Assert.assertEquals(expBodyPojo.getLists().getSubject_group_subject_id(), respPojo.getLists().getSubject_group_subject_id());
        Assert.assertEquals(expBodyPojo.getLists().getSubject_id(), respPojo.getLists().getSubject_id());
        Assert.assertEquals(expBodyPojo.getLists().getHomework_date(), respPojo.getLists().getHomework_date());
        Assert.assertEquals(expBodyPojo.getLists().getSubmit_date(), respPojo.getLists().getSubmit_date());
        Assert.assertEquals(expBodyPojo.getLists().getMarks(), respPojo.getLists().getMarks());
        Assert.assertEquals(expBodyPojo.getLists().getDescription(), respPojo.getLists().getDescription());
        Assert.assertEquals(expBodyPojo.getLists().getCreate_date(), respPojo.getLists().getCreate_date());
        Assert.assertEquals(expBodyPojo.getLists().getEvaluation_date(), respPojo.getLists().getEvaluation_date());
        Assert.assertEquals(expBodyPojo.getLists().getDocument(), respPojo.getLists().getDocument());
        Assert.assertEquals(expBodyPojo.getLists().getCreated_by(), respPojo.getLists().getCreated_by());
        Assert.assertEquals(expBodyPojo.getLists().getEvaluated_by(), respPojo.getLists().getEvaluated_by());
        Assert.assertEquals(expBodyPojo.getLists().getCreated_at(), respPojo.getLists().getCreated_at());
        Assert.assertEquals(expBodyPojo.getLists().getSubject_name(), respPojo.getLists().getSubject_name());
        Assert.assertEquals(expBodyPojo.getLists().getSubject_groups_id(), respPojo.getLists().getSubject_groups_id());
        Assert.assertEquals(expBodyPojo.getLists().getName(), respPojo.getLists().getName());
        Assert.assertEquals(expBodyPojo.getLists().getAssignments(), respPojo.getLists().getAssignments());
    }

    // API_45-1
    @Given("Post request homeworkAdd")
    public void post_request_homework_add() {

        TeacherHomeworkAdd_PostReqBodyPojo reqBodyPojo = new TeacherHomeworkAdd_PostReqBodyPojo("1", "1", "18", "21",
                "1", "2022-07-05", "2022-07-08", 1.00, "<p>\\r\\n\\r\\nRead carefully\\r\\n\\r\\n<br></p>",
                "2022-07-01", "0000-00-00", "", "5", "5");

        API_ReusableMethods.postRequest(reqBodyPojo);

        TeacherHomeworkAdd_ExpBodyPojo expBodyPojo = new TeacherHomeworkAdd_ExpBodyPojo(200, "Success", 0);

        Response response = API_ReusableMethods.postResponse;
        TeacherHomeworkAdd_ExpBodyPojo respPojo = response.as(TeacherHomeworkAdd_ExpBodyPojo.class);

        newCreatedHomeworkId = respPojo.getAddId();
    }

    //API_45-3
    @Given("Post request homeworkByNewId")
    public void post_request_homework_by_new_id() {
        TeacherHomeworkById_PostReqBodyPojo idReqBody = new TeacherHomeworkById_PostReqBodyPojo(newCreatedHomeworkId);
        API_ReusableMethods.postRequest(idReqBody);
    }

    //46-1
    @Given("Update request for homeworkUpdate")
    public void update_request_homework_update() {
        TeacherHomework_UpdateReqBodyPojo reqBodyPojo = new TeacherHomework_UpdateReqBodyPojo("729", "2", "2", "20", "101",
                "2", "2023-10-20", "2023-10-30", 5.00, "Great!!!!", "2023-08-14",
                "2023-11-01", "project.doc", "1078", "Nurettin");
        API_ReusableMethods.updateResponse(reqBodyPojo);
    }
    //46-1
    @Given("Verify that status code 200 and message is Success for HomeworkUpdate Request")
    public void verify_that_status_code_and_message_is_success_for_homework_update_request() {

        TeacherHomework_UpdateExpBoydPojo expBodyPojo=new TeacherHomework_UpdateExpBoydPojo(200,"Success","729");

        Response response=API_ReusableMethods.updateResponse;
        TeacherHomework_UpdateExpBoydPojo respPojo=response.as(TeacherHomework_UpdateExpBoydPojo.class);

        Assert.assertEquals(expBodyPojo.getStatus(), respPojo.getStatus());
        Assert.assertEquals(expBodyPojo.getMessage(), respPojo.getMessage());
    }
    //API_46-2
    @Given("Verify that status code 403 and message is failed for HomeworkUpdate Request")
    public void verify_that_status_code_and_message_is_failed_for_homework_update_request() {
        TeacherHomework_UpdateExpBoydPojo expBodyPojo=new TeacherHomework_UpdateExpBoydPojo(403,"failed","729");

        Response response=API_ReusableMethods.updateResponse;
        TeacherHomework_UpdateExpBoydPojo respPojo=response.as(TeacherHomework_UpdateExpBoydPojo.class);

        Assert.assertEquals(expBodyPojo.getStatus(), respPojo.getStatus());
        Assert.assertEquals(expBodyPojo.getMessage(), respPojo.getMessage());
    }
    //API_46-3

    @Given("Verify that responseId match the updated Id.")
    public void verify_that_response_id_match_the_updated_id() {
        TeacherHomework_UpdateExpBoydPojo expBodyPojo=new TeacherHomework_UpdateExpBoydPojo(200,"Success","729");

        TeacherHomework_UpdateReqBodyPojo reqBodyPojo=new TeacherHomework_UpdateReqBodyPojo("729","2","2","20","101",
                "2","2023-10-20","2023-10-30",5.00,"Great!!!!","2023-08-14",
                "2023-11-01","project.doc","1078","Nurettin");

        Response response=API_ReusableMethods.updateResponse;
        TeacherHomework_UpdateExpBoydPojo respPojo=response.as(TeacherHomework_UpdateExpBoydPojo.class);

        //   System.out.println(reqBodyPojo.getId());
        //  System.out.println(respPojo.getUpdatedId());

        Assert.assertEquals(reqBodyPojo.getId(),respPojo.getUpdatedId());
    }
    // API_46-4
    @Given("Post request for updated homeworkById")
    public void post_request_for_updated_homework_by_id() {
        TeacherHomeworkById_PostReqBodyPojo idReqBody = new TeacherHomeworkById_PostReqBodyPojo(729);
        API_ReusableMethods.postRequest(idReqBody);

    }
    //API_46-4
    @Given("Verify that POST response data match the updated data")
    public void verify_that_post_response_id_match_the_updated_id() {

        // Update id 729 body partly:
        TeacherHomework_UpdateReqBodyPojo reqBodyPojo = new TeacherHomework_UpdateReqBodyPojo("729","2","2","20","101",
                "2","2023-10-20","2023-10-30",5.00,"Great!!!!","2023-08-14",
                "2023-11-01","project.doc","1078","Nurettin");
        API_ReusableMethods.updateResponse(reqBodyPojo);

        // post request for updated ID:
        TeacherHomeworkById_PostReqBodyPojo idReqBodyPojo = new TeacherHomeworkById_PostReqBodyPojo(729);
        API_ReusableMethods.postRequest(idReqBodyPojo);

        Response responseCheckUpdateId= API_ReusableMethods.postResponse;
        TeacherHomeworkById_ExpBodyPojo actRespPojo= responseCheckUpdateId.as(TeacherHomeworkById_ExpBodyPojo.class);
        System.out.println("actualResponsePojo" + actRespPojo);

        Assert.assertEquals(reqBodyPojo.getId(), actRespPojo.getLists().getId());
        Assert.assertEquals(reqBodyPojo.getClass_id(), actRespPojo.getLists().getClass_id());
        Assert.assertEquals(reqBodyPojo.getSection_id(), actRespPojo.getLists().getSection_id());
        Assert.assertEquals(reqBodyPojo.getSession_id(), actRespPojo.getLists().getSession_id());
        Assert.assertEquals(reqBodyPojo.getSubject_group_subject_id(), actRespPojo.getLists().getSubject_group_subject_id());
        Assert.assertEquals(reqBodyPojo.getSubject_id(), actRespPojo.getLists().getSubject_id());
        Assert.assertEquals(reqBodyPojo.getHomework_date(), actRespPojo.getLists().getHomework_date());
        Assert.assertEquals(reqBodyPojo.getSubmit_date(), actRespPojo.getLists().getSubmit_date());
        //  Assert.assertEquals(reqBodyPojo.getMarks(), actRespPojo.getLists().getMarks());
        Assert.assertEquals(reqBodyPojo.getDescription(), actRespPojo.getLists().getDescription());
        Assert.assertEquals(reqBodyPojo.getCreate_date(), actRespPojo.getLists().getCreate_date());
        Assert.assertEquals(reqBodyPojo.getEvaluation_date(), actRespPojo.getLists().getEvaluation_date());
        Assert.assertEquals(reqBodyPojo.getDocument(), actRespPojo.getLists().getDocument());
        Assert.assertEquals(reqBodyPojo.getCreated_by(), actRespPojo.getLists().getCreated_by());
        Assert.assertEquals(reqBodyPojo.getEvaluated_by(), actRespPojo.getLists().getEvaluated_by());

    }
//API_47-1
@Given("Delete request for homeworkDelete")
public void delete_request_for_homework_delete() {
    JSONObject reqBody1=new JSONObject();
    reqBody1.put("id",761);
    reqBody=reqBody1;
 //   System.out.println("---------------request body--------------------");
 //   System.out.println(reqBody);

}
//API_47-1
@Given("Verify that status code 200 and message is Success for homeworkDelete.")
public void verify_that_status_code_and_message_is_success_for_homework_delete() {
    response=given()
            .spec(spec)
            .accept(ContentType.JSON)
            .header("Authorization","Bearer "+Authentication.token)
            .when()
            .body(reqBody.toString())
            .delete(API_StepDefinitions.fullPath);
  //  System.out.println("*************************************************************");
    response.prettyPrint();

    response
            .then()
            .assertThat()
            .body("message", Matchers.equalTo("Success"))
            .body("status", Matchers.equalTo(200));
}
    //API_47-2
    @Given("Verify that status code 403 and message is failed for homeworkDelete.")
    public void verify_that_status_code_and_message_is_failed_for_homework_delete() {

        response=given()
                .spec(spec)
                .accept(ContentType.JSON)
                .header("Authorization","Bearer " + Authentication.token)
                .when()
                .body(reqBody.toString())
                .delete(API_StepDefinitions.fullPath);
        response.prettyPrint();

        response
                .then()
                .assertThat()
                .body("message", Matchers.equalTo("failed"))
                .body("status", Matchers.equalTo(403));

    }
    //API_47-3
    @Given("Verify that DeletedId in the respBody match the id sent in the reqBody")
    public void verify_that_deleted_id_in_the_resp_body_match_the_id_sent_in_the_req_body() {

        response=given()
                .spec(spec)
                .accept(ContentType.JSON)
                .header("Authorization","Bearer " + Authentication.token)
                .when()
                .body(reqBody.toString())
                .delete(API_StepDefinitions.fullPath);
        response.prettyPrint();

        System.out.println("Reqest Body for 47-03 :" + reqBody.get("id"));

        response.getBody().prettyPrint();

        response
                .then()
                .assertThat()
                .body("DeletedId", Matchers.equalTo(reqBody.get("id")));
    }
//API_47-4
@Given("Get the deletedId")
public void get_the_deleted_id() {

    response=given()
            .spec(spec)
            .accept(ContentType.JSON)
            .header("Authorization","Bearer " + Authentication.token)
            .when()
            .body(reqBody.toString())
            .delete(API_StepDefinitions.fullPath);
    response.prettyPrint();

    TeacherHomework_DeleteRespBody respBodyPojo=response.as(TeacherHomework_DeleteRespBody.class);

    deletedHomeworkId=respBodyPojo.getDeletedId();

    System.out.println("deletedId: " + respBodyPojo.getDeletedId());
}

    //***

    @Given("{string} request for Vehicle - id {string} -")
    public void batu_step(String requestType,String id) {

        reqBody= new JSONObject();

        if (requestType.equals("POST")) {
            int idNo= Integer.parseInt(id);
            if (idNo==0){
                AlumniDelete_ReqBodyPOJO reqBodyPOJO= new AlumniDelete_ReqBodyPOJO(saveId);
                API_ReusableMethods.postRequest(reqBodyPOJO);

            }
            else{reqBody.put("id",idNo);
                API_ReusableMethods.postRequest(reqBody.toString());
            }


        }
        if (requestType.equals("POSTADD")) {

            VehiclePostAddRequestPOJO vehiclePostAddRequestPOJO= new VehiclePostAddRequestPOJO("VH4584","Ford CAB","1677502339-191558462463fca783b26b0!fd.png",
                    "2015","FFG-76575676787","523422","50","Batuhan","258714545","8521479630","");

            API_ReusableMethods.postRequest(vehiclePostAddRequestPOJO);
        }



        if (requestType.equals("PATCH")) {

            Vehicle_dataPOJO reqBody= new Vehicle_dataPOJO(saveId,"VH4584","Toyota","1677502339-191558462463fca783b26b0!fd.png",
                    "2015","FFG-76575676787","523422","50","Batuhan","258714545","8521479630","");

            System.out.println("Reqbody*******************"+reqBody.toString());
            API_ReusableMethods.patchRequest(reqBody);
        }



        if (requestType.equals("DELETE")) {
            int idNo= Integer.parseInt(id);
            if (idNo==0){
                AlumniDelete_ReqBodyPOJO reqBody= new AlumniDelete_ReqBodyPOJO(saveId);
                System.out.println("Delete id : "+saveId);
                API_ReusableMethods.deleteRequest(reqBody);}
            else {
                AlumniDelete_ReqBodyPOJO reqBody= new AlumniDelete_ReqBodyPOJO(idNo);
                API_ReusableMethods.deleteRequest(reqBody);

            }

        }


        if (requestType.equals("POSTDELETE")){
            AlumniDelete_ReqBodyPOJO reqBody= new AlumniDelete_ReqBodyPOJO(saveId);
            API_ReusableMethods.postRequest(reqBody);

        }

        if (requestType.equals("POST2")){

            //   Vehicle_dataPOJO reqpojo= new Vehicle_dataPOJO(saveId,"","","","","","","","","","","");
            AlumniDelete_ReqBodyPOJO reqpojo= new AlumniDelete_ReqBodyPOJO(saveId);
            System.out.println(fullPath);


            API_ReusableMethods.postRequest(reqpojo);

        }




    }


    @Given("Response body should be validated for {string} request")
    public void the_content_of_the_list_in_the_vehicle_list_response_body_should_be_validated(String requestType) {


        if (requestType.equals("GET")){
            Vehicle_dataPOJO vehicleDataPOJO = new Vehicle_dataPOJO(1, "VH1001", "Volvo Bus",
                    "1677502387-149436744063fca7b3a1796!fd.png", "2017", "FVFF-08797865", "45453", "50",
                    "Michel", "R534534", "8667777869", "2023-02-27 07:53:07"
            );

            List<Vehicle_dataPOJO> lists = new ArrayList<>();

            lists.add(vehicleDataPOJO);

            GetVehicleListExpBodyPojo expBodyPojo = new GetVehicleListExpBodyPojo(200, "Success", lists);

            Response response = API_ReusableMethods.getResponse;

            GetVehicleListExpBodyPojo responsePojo = response.as(GetVehicleListExpBodyPojo.class);


            int index = 0;
            for (int i = 0; i < responsePojo.getLists().size(); i++) {
                if (responsePojo.getLists().get(i).getId() == expBodyPojo.getLists().get(0).getId())
                    index = i;
            }


            Assert.assertEquals(expBodyPojo.getStatus(), responsePojo.getStatus());
            Assert.assertEquals(expBodyPojo.getMessage(), responsePojo.getMessage());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getId(), responsePojo.getLists().get(index).getId());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getVehicle_no(), responsePojo.getLists().get(index).getVehicle_no());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getVehicle_model(), responsePojo.getLists().get(index).getVehicle_model());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getVehicle_photo(), responsePojo.getLists().get(index).getVehicle_photo());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getManufacture_year(), responsePojo.getLists().get(index).getManufacture_year());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getRegistration_number(), responsePojo.getLists().get(index).getRegistration_number());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getChasis_number(), responsePojo.getLists().get(index).getChasis_number());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getMax_seating_capacity(), responsePojo.getLists().get(index).getMax_seating_capacity());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getDriver_name(), responsePojo.getLists().get(index).getDriver_name());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getDriver_licence(), responsePojo.getLists().get(index).getDriver_licence());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getDriver_contact(), responsePojo.getLists().get(index).getDriver_contact());
            Assert.assertEquals(expBodyPojo.getLists().get(0).getCreated_at(), responsePojo.getLists().get(index).getCreated_at());



        }






        if (requestType.equals("POST")){


            Response response = API_ReusableMethods.postResponse;

            System.out.println(response.asString());
            String responseStr= response.asString();



            List<String> expectedKeysList=new ArrayList<>();
            expectedKeysList.add("id");
            expectedKeysList.add("vehicle_no");
            expectedKeysList.add("vehicle_model");
            expectedKeysList.add("vehicle_photo");
            expectedKeysList.add("manufacture_year");
            expectedKeysList.add("registration_number");
            expectedKeysList.add("chasis_number");
            expectedKeysList.add("max_seating_capacity");
            expectedKeysList.add("driver_name");
            expectedKeysList.add("driver_licence");
            expectedKeysList.add("driver_contact");
            expectedKeysList.add("note");
            expectedKeysList.add("created_at");

            int flag=0;

            for (int i = 0; i <expectedKeysList.size() ; i++) {
                if (responseStr.contains(expectedKeysList.get(i))){
                    System.out.println("Contains");
                }
                else {
                    flag++;
                }
            }
            Assert.assertTrue(flag==0);



        }






        if (requestType.equals("POSTADD")){

            Response response= API_ReusableMethods.postResponse;

            VehicleAddExpPOJO responsePojo= response.as(VehicleAddExpPOJO.class);
            saveId= responsePojo.getAddId();
            System.out.println("Eklenen kayıt "+saveId+" olarak kaydedildi.");
            fullPath=null;

        }
        if (requestType.equals("PATCH")){
            Response response= API_ReusableMethods.patchResponse;
            VehicleUpdateExpPojo responsePOJO= response.as(VehicleUpdateExpPojo.class);
            Assert.assertEquals(saveId,responsePOJO.getUpdateId());

        }
        if (requestType.equals("PATCHPOST")){
            //    Vehicle_dataPOJO reqBody= new Vehicle_dataPOJO(saveId,"VH4584","Toyota","1677502339-191558462463fca783b26b0!fd.png",
            //         "2015","FFG-76575676787","523422","50","Batuhan","258714545","8521479630","");

            Vehicle_dataPOJO vehicleDataPOJO = new Vehicle_dataPOJO(1, "VH1001", "Volvo Bus",
                    "1677502387-149436744063fca7b3a1796!fd.png", "2017", "FVFF-08797865", "45453", "50",
                    "Michel", "R534534", "8667777869", "2023-02-27 07:53:07"
            );

            List<Vehicle_dataPOJO> lists = new ArrayList<>();

            lists.add(vehicleDataPOJO);

            GetVehicleListExpBodyPojo expBodyPojo = new GetVehicleListExpBodyPojo(200, "Success", lists);

            Response response = API_ReusableMethods.getResponse;

            GetVehicleListExpBodyPojo responsePojo = response.as(GetVehicleListExpBodyPojo.class);


            int index = 0;
            for (int i = 0; i < responsePojo.getLists().size(); i++) {
                if (responsePojo.getLists().get(i).getId() == saveId)
                    index = i;
            }
            System.out.println("Save id :"+saveId);
            System.out.println(responsePojo.getLists().get(index).getId());
            System.out.println(index);

            Assert.assertEquals("Toyota",responsePojo.getLists().get(index).getVehicle_model());



          /*
            Response response= API_ReusableMethods.postResponse;
         //   Vehicle_dataPOJO responsePOJO= response.as(Vehicle_dataPOJO.class);
            GetVehicleListExpBodyPojo responsePOJO= response.as(GetVehicleListExpBodyPojo.class);
            Assert.assertEquals(reqBody.getVehicle_model(),responsePOJO.getLists().get(0).getVehicle_model());
*/
        }
        if (requestType.equals("DELETE")){

            Response response= API_ReusableMethods.deleteResponse;

            AlumniDelete_RespBodyPOJO responsePojo= response.as(AlumniDelete_RespBodyPOJO.class);


            Assert.assertTrue(saveId==responsePojo.getDeletedId());

        }


    }

    @Given("Find the id of the content created by {string}")
    public void findBatu(String name) {

        Response response = API_ReusableMethods.getResponse;

        GetVehicleListExpBodyPojo responsePojo = response.as(GetVehicleListExpBodyPojo.class);


        for (int i = 0; i < responsePojo.getLists().size(); i++) {

            if (responsePojo.getLists().get(i).getDriver_name().equals(name))
                saveId= responsePojo.getLists().get(i).getId();

        }




    }

    @Given("Verify that status code {int} and message is {string} for {string}")
    public void verify_that_status_code_and_message_is_json(Integer givenCode, String givenMessage, String requestType) {


        if (requestType.equals("POST")){
            API_ReusableMethods.postResponse.then()
                    .assertThat()
                    .statusCode(givenCode)
                    .contentType(ContentType.JSON)
                    .body("message", Matchers.equalTo(givenMessage));
        }

        if (requestType.equals("PATCH")){
            GetResponseExpDataPojo expDataPojo = new GetResponseExpDataPojo(givenCode,givenMessage);

            Response response= API_ReusableMethods.patchResponse;

            GetResponseExpDataPojo respnsePojo= response.as(GetResponseExpDataPojo.class);

            Assert.assertEquals(expDataPojo.getMessage(),respnsePojo.getMessage());
            Assert.assertEquals(expDataPojo.getStatus(),respnsePojo.getStatus());

           /*
            API_ReusableMethods.patchResponse.then()
                    .assertThat()
                    .statusCode(givenCode)
                    .contentType(ContentType.JSON)
                    .body("message", Matchers.equalTo(givenMessage));

            */

        }





    }



}



