package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
        response.log().all();
    }

    //    1. Extract the All Ids
    @Test
    public void setAllId() {
        List<Integer> allId = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the All Ids : " + allId);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void setAllName() {
        List<String> allName = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the all Names : " + allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void nameOfObject() {
        String object5 = response.extract().path("[4].name");
       // String object5 = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the name of 5th object : " + object5);
        System.out.println("------------------End of Test---------------------------");
    }
    //Extract the names of all object whose status = inactive
    @Test
    public void statusInactive(){
        List<String> status = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the names of all object whose status = inactive : " + status);
        System.out.println("------------------End of Test---------------------------");

    }
    //Extract the gender of all the object whose status = active
    @Test
    public void statusActive(){
        List<String> statusActive = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the gender of all object whose status = inactive : " + statusActive);
        System.out.println("------------------End of Test---------------------------");
    }
    //Print the names of the object whose gender = female
    @Test
    public void nameOfGenderF(){
        List<String> genderF = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the names of all object whose gender = female : " + genderF);
        System.out.println("------------------End of Test---------------------------");

    }
    //Get all the emails of the object where status = inactive
    @Test
    public void emailsOfObject(){
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the email of all object whose status = inactive : " + email);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get the ids of the object where gender = male
    @Test
            public void genderMOfIds() {
        List<Integer> idOfMale = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the id of all object whose gender = male : " + idOfMale);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get all the status
    @Test
    public void allStatus(){
        List<String> statusAll = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Get all the status : " + statusAll);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get email of the object where name = Jahnu Abbott
    @Test
    public void emailOfObjectJahnu() {
        List<String> jahnu = response.extract().path("findAll{it.name == 'Jahnu Abbott'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Get email of the object where name = Jahnu Abbott : " + jahnu);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get gender of id = 5471
    @Test
    public void genderOfId(){
        List<String> gId = response.extract().path("findAll{it.id == '5471'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Get gender of id = 5471: " + gId);
        System.out.println("------------------End of Test---------------------------");

    }

}
