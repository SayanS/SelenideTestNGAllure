package com.test.util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class HttpMethods {

    public static Response get(String endpoint, int expectedStatus){
        Response response = RestAssured.given()
                . header("Content-Type", ContentType.JSON).when()
                .get(endpoint);
        Assert.assertEquals(response.getStatusCode(),expectedStatus);
        response.then().log().all();
        return response;
    }
}
