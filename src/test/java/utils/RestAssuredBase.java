package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class RestAssuredBase {
    protected static RequestSpecification specifications;

    static {
        specifications = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2/users")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer 43225c35777b5c2458c5566386c5c876c43db255ef04eb022d76ee284afd113c")
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

    }
}
