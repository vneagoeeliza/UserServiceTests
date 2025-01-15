package tests.restassuredtests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.RestAssuredBase;
import utils.User;

import static io.restassured.RestAssured.given;

@Epic("RestAssured Tests")
@Feature("Delete user")
class DeleteUserPositiveScenariosTests extends RestAssuredBase {
    String userId;

    @BeforeEach
    public void createUser() {
        String email = "user" + (int) (Math.random() * 1000) + "@testemail.com";
        User user = new User("User Test", email, "male", "active");
        userId = given()
                .spec(specifications)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract().response().jsonPath().getString("id");
    }

    @Test
    void deleteUser() {
        given()
                .spec(specifications)
                .pathParams("userId", userId)
                .when()
                .delete("/{userId}")
                .then()
                .statusCode(204)
                .log().all();
        String message = given()
                .spec(specifications)
                .pathParams("userId", userId)
                .when()
                .get("/{userId}")
                .then()
                .extract().response().jsonPath().getString("message");
        Assertions.assertEquals("Resource not found", message);
    }
}
