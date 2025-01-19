package tests.restassuredtests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.RestAssuredBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@Epic("RestAssured Tests")
@Feature("Get users")

public class GetUsersPositiveScenariosTests extends RestAssuredBase {
    @Test
    public void getAllUsers() {
        given()
                .spec(specifications)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)));
    }

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
