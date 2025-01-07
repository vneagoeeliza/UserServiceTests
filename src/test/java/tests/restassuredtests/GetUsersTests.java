package tests.restassuredtests;

import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.RestAssuredBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class GetUsersTests extends RestAssuredBase {
    @Feature("Creates user")
    @Test
    public void GetBooksDetails() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = given();
        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        Response response = httpRequest.request(Method.GET, "");
        // Print the status and muessage body of the response received from the server
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());

    }

    @Feature("Get all users")
    @Test
    public void getUsersTest() {
        given()
                .spec(specifications)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)));
    }

    @Feature("Get single user")
    @Test
    public void getSingleUser() {
        Response responseAllUsers = given()
                .spec(specifications)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        String userId = responseAllUsers.jsonPath().getMap("[0]").get("id").toString();
        String userName = responseAllUsers.jsonPath().getMap("[0]").get("name").toString();

        Response responseSingleUser = given()
                .spec(specifications)
                .pathParams("userId", userId)
                .when()
                .get("/{userId}")
                .then()
                .statusCode(200)
                .extract()
                .response();
        String userId2 = responseSingleUser.jsonPath().getString("id");
        String userName2 = responseSingleUser.jsonPath().getString("name");
        Assertions.assertEquals(userId, userId2);
        Assertions.assertEquals(userName, userName2);

    }
}
