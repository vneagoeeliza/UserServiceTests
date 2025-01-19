package tests.restassuredtests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.RestAssuredBase;
import utils.User;

import static io.restassured.RestAssured.given;

@Epic("RestAssured Tests")
@Feature("Update user")
class UpdateUserNegativeScenariosTests extends RestAssuredBase {
    String userId;

    @BeforeEach
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
    }

    @Test
    void updateUserWithInvalidStatus() {
        User modifiedUser = new User("Modified User", "newEmail@testmail.com", "female", "InvalidStatus");
        given()
                .spec(specifications)
                .pathParams("userId", userId)
                .body(modifiedUser)
                .when()
                .put("/{userId}")
                .then()
                .statusCode(422)
                .extract().response();
        Response getUserResponse = given()
                .spec(specifications)
                .pathParams("userId", userId)
                .when()
                .get("/{userId}")
                .then()
                .statusCode(200)
                .extract().response();
        Assertions.assertEquals("active", getUserResponse.jsonPath().getString("status"));
    }

    @AfterEach
    public void deleteUser() {
        given()
                .spec(specifications)
                .pathParams("userId", userId)
                .when()
                .delete("/{userId}")
                .then()
                .statusCode(204);
    }
}
