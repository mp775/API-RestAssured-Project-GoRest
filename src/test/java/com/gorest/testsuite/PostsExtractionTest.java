package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
    }
    //Extract the title
    @Test
    public void extractTitle() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the title  : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //Extract the total number of record
    @Test
    public void totalNumberOfId(){
        List<Integer> allId = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the total number of record : " + allId.size());
        System.out.println("------------------End of Test---------------------------");

    }
 //Extract the body of 15th record
    @Test
    public void recordOf15(){
        List<?> record = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the total number of record : " + record);
        System.out.println("------------------End of Test---------------------------");
    }
    //Extract the user_id of all the records
    @Test
    public void UserId(){
        List<Integer> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the user_id of all the records : " + userId);
        System.out.println("------------------End of Test---------------------------");

    }
    // Extract the title of all the records
    @Test
    public void allTitle() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the all title  : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
        //Extract the title of all records whose user_id = 56979
        @Test
                public void userIdTitle() {
            List<String> idTitle = response.extract().path("findAll{it.id == '56979'}.title");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println(" the title of all records whose user_id = 56979 : " + idTitle);
            System.out.println("------------------End of Test---------------------------");
        }
//Extract the body of all records whose id = 2671
    @Test
    public void bodyRecord(){
            List<String> idRecord = response.extract().path("findAll{it.id == '56973'}.body");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println(" the body of all records whose id = 56973 : " + idRecord);
            System.out.println("------------------End of Test---------------------------");
    }

}
