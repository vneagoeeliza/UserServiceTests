package tests.restassuredtests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import utils.RestAssuredBase;

import static io.restassured.RestAssured.given;
@Epic("RestAssured Tests")
@Feature("Delete user")
public class DeleteUserNegativeScenarioTests extends RestAssuredBase {
    @Test
    public void deleteNonExistingUser() {
        String userId = String.valueOf((int) (Math.random() * 1000));
        String message = given()
                .spec(specifications)
                .pathParams("userId", userId)
                .when()
                .delete("/{userId}")
                .then()
                .statusCode(404)
                .extract().response().jsonPath().getString("message");
        Assertions.assertEquals("Resource not found", message);
    }
}
