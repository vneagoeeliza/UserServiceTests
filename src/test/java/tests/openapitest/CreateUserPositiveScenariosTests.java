package tests.openapitest;

import com.user_service.model.UserPojo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import utils.PropertiesLoader;

@Epic("OpenApi Tests")
@Feature("Create user")
class CreateUserPositiveScenariosTests extends PropertiesLoader {
    String userId;
    String email = "user" + (int) (Math.random() * 1000) + "@testemail.com";
    int exceptionCode;

    @Test
    void createUser() {
        ResponseEntity<UserPojo> response = toolkit.create().user()
                .withName("User Test")
                .withEmail(email)
                .withGender("male")
                .withStatus("active")
                .execute();
        Assertions.assertEquals(201, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody().getId());
        userId = response.getBody().getId();
        Assertions.assertEquals("User Test", response.getBody().getName());
        Assertions.assertEquals(email, response.getBody().getEmail());
        Assertions.assertEquals("male", response.getBody().getGender());
        Assertions.assertEquals("active", response.getBody().getStatus());
    }

    @AfterEach
    public void deleteUser() {
        Assertions.assertEquals(204, toolkit.delete().user(userId).getStatusCode().value());
        try {
            toolkit.get().user(userId);
        } catch (HttpClientErrorException exception) {
            exceptionCode = exception.getRawStatusCode();
        }
        Assertions.assertEquals(404, exceptionCode);
    }
}
