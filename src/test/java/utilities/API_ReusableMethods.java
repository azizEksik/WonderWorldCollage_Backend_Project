package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import stepDefinitions.api.API_StepDefinitions;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;

public class API_ReusableMethods {

    public static Response getResponse;

    public static Response postResponse;
    public static Response patchResponse;
    public static Response deleteResponse;
    public static Response updateResponse;
    public static int getHomeworkDeleteId;

    public static void getRequest() {

        getResponse = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + Authentication.token)
                .when()
                .get(API_StepDefinitions.fullPath);

        getResponse.prettyPrint();
    }

    public static void postRequest(Object pojo) {

        postResponse = given()
                .spec(spec)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + Authentication.token)
                .when()
                .body(pojo)
                .post(API_StepDefinitions.fullPath);

        postResponse.prettyPrint();
    }

    public static void patchRequest(Object pojo) {

        patchResponse = given()
                .spec(spec)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + Authentication.token)
                .when()
                .body(pojo)
                .patch(API_StepDefinitions.fullPath);

        patchResponse.prettyPrint();
    }

    public static void deleteRequest(Object pojo) {

        deleteResponse = given()
                .spec(spec)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + Authentication.token)
                .when()
                .body(pojo)
                .delete(API_StepDefinitions.fullPath);

        deleteResponse.prettyPrint();
    }




    public static void updateResponse(Object pojo){


        updateResponse = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + Authentication.token)
                .when()
                .body(pojo)
                .post(API_StepDefinitions.fullPath);

        updateResponse.prettyPrint();
    }

}






