package tests.restassuredtests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import utils.RestAssuredBase;
import utils.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("RestAssured Tests")
@Feature("Create user")

public class CreateUserNegativeScenariosTests extends RestAssuredBase {

    @Test
    public void createUserWithoutName() {
        String email = "user" + (int) (Math.random() * 1000) + "@testemail.com";
        User user = new User(null, email, "male", "active");
        given()
                .spec(specifications)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(422)
                .body("[0].field", equalTo("name"))
                .body("[0].message", equalTo("can't be blank"))
                .log().all();
    }

    @Test
    public void createUserWithInvalidEmail() {
        String email = "user" + (int) (Math.random() * 1000);
        User user = new User("User Test", email, "male", "active");
        given()
                .spec(specifications)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(422)
                .body("[0].field", equalTo("email"))
                .body("[0].message", equalTo("is invalid"))
                .log().all();
    }
}
