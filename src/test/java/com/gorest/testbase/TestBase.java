package com.gorest.testbase;

import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase extends TestUtils {
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //RestAssured.port = 3030;
        RestAssured.basePath = "";

    }
}