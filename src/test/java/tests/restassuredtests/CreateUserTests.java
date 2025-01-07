package tests.restassuredtests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import utils.RestAssuredBase;
import utils.User;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends RestAssuredBase {

    @Feature("Creates user")
    @Test
    public void createUser() {
        User user = new User("User Test", "testEmail@test.com", "male", "active");
        given()
                .spec(specifications)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(201)
                .log().all();

    }

}
