package tests.restassuredtests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.RestAssuredBase;
import utils.User;

import static io.restassured.RestAssured.given;

@Epic("RestAssured Tests")
@Feature("Create user")
class CreateUserPositiveScenariosTests extends RestAssuredBase {
    private String userId;

    @Test
    public void createUser() {
        String email = "user" + (int) (Math.random() * 1000) + "@testemail.com";
        User user = new User("User Test", email, "male", "active");
        Response response = given()
                .spec(specifications)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract().response();
        Assertions.assertNotNull(response.jsonPath().getString("id"));
        userId = response.jsonPath().getString("id");
        Assertions.assertEquals("User Test", response.jsonPath().getString("name"));
        Assertions.assertEquals("male", response.jsonPath().getString("gender"));
        Assertions.assertEquals("active", response.jsonPath().getString("status"));
        Assertions.assertEquals(email, response.jsonPath().getString("email"));
    }

    @AfterEach
    public void deleteUser() {
        given()
                .spec(specifications)
                .pathParams("userId", userId)
                .when()
                .delete("/{userId}")
                .then()
                .statusCode(204)
                .log().all();
    }
}
