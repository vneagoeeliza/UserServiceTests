package tests.restassuredtests;

import org.junit.jupiter.api.Test;
import utils.RestAssuredBase;

import static io.restassured.RestAssured.given;

public class GetUserNegativeScenariosTests extends RestAssuredBase {
    @Test
    public void getSingleUser() {
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
