package com.test.util;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RestAssuredConfiguration {

    @BeforeTest
    public void configure() throws IOException {
        Properties configProp= new Properties();
        configProp.load(new FileInputStream("./src/test/resources/selenideConfig.properties"));

        RestAssured.baseURI = configProp.getProperty("baseUri");
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
