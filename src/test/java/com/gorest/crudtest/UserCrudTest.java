package com.gorest.crudtest;

import com.gorest.model.PostPojo;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCrudTest extends TestBase {

    static String token = "f8f231911770bd29394ba6350b92e3e88b00d7f482a4b763d244d3d42f395d19";


//    @BeforeClass
//    public static void inIt() {
//        RestAssured.baseURI = "https://gorest.co.in";
//        //  RestAssured.port = 3030;
//    }

    @Test()
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName("pooja");
        userPojo.setEmail("manya.patel1@gmail.com");
        userPojo.setGender("female");
        userPojo.setStatus("active");

        // make request to server to create new user
        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .post("/public/v2/users");

        //To fetch response from server
        response.then().log().all().statusCode(201);

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        int userId = jsonPath.getInt("id");
        System.out.println("user id " + userId);
    }
    @Test
    public void verifyUserUpdateSuccessfully() {
        String email = TestUtils.getRandomValue() + "prime@gmail.com";
        PostPojo postsPojo = new PostPojo();
        postsPojo.setName("Prime");
        postsPojo.setEmail(email);
        postsPojo.setStatus("active");
        postsPojo.setGender("female");
        Response response = given()
                .basePath(RestAssured.basePath+"/4131530")
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(postsPojo)
                .put();
        response.prettyPrint();
        response.then().statusCode(200);

        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        int userId = jsonPath.getInt("id");
        System.out.println("user id " + userId);
    }

}