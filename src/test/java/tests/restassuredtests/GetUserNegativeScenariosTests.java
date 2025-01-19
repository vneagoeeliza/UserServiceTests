package tests.restassuredtests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import utils.RestAssuredBase;

import static io.restassured.RestAssured.given;

@Epic("RestAssured Tests")
@Feature("Get users")
public class GetUserNegativeScenariosTests extends RestAssuredBase {
    @Test
    public void getNonExistingUser() {
        String userId = String.valueOf((int) (Math.random() * 1000));
        given()
                .spec(specifications)
                .pathParams("userId", userId)
                .when()
                .get("/{userId}")
                .then()
                .statusCode(404)
                .extract()
                .response();
    }
}
