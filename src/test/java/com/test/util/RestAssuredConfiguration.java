package com.test.util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import ru.yandex.qatools.allure.annotations.Step;

public class RestAssuredConfiguration {

    @BeforeTest
    public void configure() {
        RestAssured.baseURI = "https://api.eldorado.ua";
        //RestAssured.port = 8080;
        RestAssured.basePath = "";
    }

    @Step
    public RequestSpecification getRequestSpecification() {
        return RestAssured.given().contentType(ContentType.JSON);
    }

    @Step
    public Response getResponse(RequestSpecification requestSpecification, String endpoint, int status){
        Response response = requestSpecification.get(endpoint);
        Assert.assertEquals(response.getStatusCode(),status);
        response.then().log().all();
        return response;
    }
}
