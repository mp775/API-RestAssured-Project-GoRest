package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
        //response.log().all();
    }

    //Verify the if the total record is 25
    @Test
    public void VerifyTheIfTheTotalRecordIs() {
        response.body("id.size", equalTo(25));
    }

    @Test
    public void verifyTitleOfId() {
        response.body("[8].title", hasItems(" Surgo patruus nam tendo ascit."));
    }


    //3. Check the single user_id in the Array list (4104728)
    @Test
    public void singleUserId() {
        response.body("[20].user_id", equalTo(4104728));
    }

    // 4. Check the multiple ids in the ArrayList (57196,7168,57177)
    @Test
    public void multipleId() {
        response.body("id", hasItems(57196, 57168, 57177));
    }
    @Test
    public void getBodyOfUserId(){
        response.body("[6].body",equalTo("\"Canto utique vox. Vorax certus comparo. Avaritia summopere antea. Et autem sto. Carus bestia bene. Nihil debilito certus. Tergiversatio reprehenderit combibo. Acceptus depraedor umbra. Admoneo tyrannus aggredior. Speculum infit vigor. Maxime ambitus desolo. Benigne accipio sumo. Auctus esse supellex. Veritatis canis thesaurus. Vere conicio optio. Alii acidus hic. Textus conicio adimpleo. Vitiosus uxor molestiae. Denuncio aut conventus.\"\n" +
                "]"));
    }
}

