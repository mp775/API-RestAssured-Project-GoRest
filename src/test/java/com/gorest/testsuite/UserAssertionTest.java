package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class UserAssertionTest {
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

    @Test
    public void VerifyTheIfTheTotalRecordIs() {
        response.body("id.size()", equalTo(20));
    }

    //Verify the if the name of id =  is equal to ”Ekaling Marar”
    @Test
    public void verifyNameOfId() {
        response.body("name[2]", equalTo("Tarun Rana"));

    }

    // Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void singleName() {
        response.body("name[4]", equalTo("Manikya Chaturvedi"));
    }

    //Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan
    @Test
    public void verifyMultipleName() {
        response.body("name", hasItems("Bandhul Gill", "Kama Asan", "Ganak Patel"));
    }

    //Verify the emai of userid = 4040688, is equal "shreyashi_kaur@schamberger.test"
    @Test
    public void verifyEmail() {
        response.body("email", hasItems("shreyashi_kaur@schamberger.test"));
    }

    // Verify the status is “Active” of user name is “Chaturbhuj Reddy”
    @Test
    public void verifyStatusIsActive() {
        response.body("[13].status", equalTo("Chaturbhuj Reddy"));
    }


}

