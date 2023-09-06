package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Authentication {

    private static RequestSpecification spec;
    public static String token;

    public static String generateToken(String givenUser, String user, String Password){

        if (givenUser.contains("admin")){
            spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

            spec.pathParams("pp1","api","pp2","getToken");

            JSONObject reqBody = new JSONObject();

            reqBody.put("email", ConfigReader.getProperty(user+"AdminEmail"));
            reqBody.put("password", ConfigReader.getProperty(Password));

            Response response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept","application/json")
                    .when()
                    .body(reqBody.toString())
                    .post("/{pp1}/{pp2}");

            JsonPath resJP = response.jsonPath();

            token =resJP.getString("token");

        } else if (givenUser.contains("student")) {
            spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

            spec.pathParams("pp1","apistudent","pp2","getToken");

            JSONObject reqBody = new JSONObject();

            reqBody.put("username", ConfigReader.getProperty(user+"StudentName"));
            reqBody.put("password", ConfigReader.getProperty(Password));

            Response response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept","application/json")
                    .when()
                    .body(reqBody.toString())
                    .post("/{pp1}/{pp2}");

            JsonPath resJP = response.jsonPath();

            token =resJP.getString("token");

        } else if (givenUser.contains("teacher")){

            spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

            spec.pathParams("pp1","api","pp2","getToken");

            JSONObject reqBody = new JSONObject();

            reqBody.put("email", ConfigReader.getProperty(user+"TeacherEmail"));
            reqBody.put("password", ConfigReader.getProperty(Password));

            Response response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept","application/json")
                    .when()
                    .body(reqBody.toString())
                    .post("/{pp1}/{pp2}");

            JsonPath resJP = response.jsonPath();

            token =resJP.getString("token");

        }
        System.out.println(token);
        return token;
    }


}