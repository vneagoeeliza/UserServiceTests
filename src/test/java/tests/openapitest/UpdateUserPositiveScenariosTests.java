package tests.openapitest;

import com.user_service.model.UserPojo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import utils.PropertiesLoader;

@Epic("OpenApi Tests")
@Feature("Update user")
class UpdateUserPositiveScenariosTests extends PropertiesLoader {
    String userId;

    @BeforeEach
    public void createUser() {
        String email = "user" + (int) (Math.random() * 1000) + "@testemail.com";
        ResponseEntity<UserPojo> response = toolkit.create().user()
                .withName("User Test")
                .withEmail(email)
                .withGender("male")
                .withStatus("active")
                .execute();
        Assertions.assertEquals(201, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody().getId());
        userId = response.getBody().getId();
    }

    @Test
    void updateUser() {
        ResponseEntity<UserPojo> response = toolkit.update().user()
                .withId(userId)
                .withName("Modified User")
                .withGender("female")
                .withEmail("newEmail@testmail.com")
                .withStatus("inactive")
                .execute();
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals("Modified User", response.getBody().getName());
        Assertions.assertEquals("female", response.getBody().getGender());
        Assertions.assertEquals("newEmail@testmail.com", response.getBody().getEmail());
        Assertions.assertEquals("inactive", response.getBody().getStatus());
    }

    @AfterEach
    public void deleteUser() {
        int exceptionCode = 0;
        Assertions.assertEquals(204, toolkit.delete().user(userId).getStatusCode().value());
        try {
            toolkit.get().user(userId);
        } catch (HttpClientErrorException exception) {
            exceptionCode = exception.getRawStatusCode();
        }
        Assertions.assertEquals(404, exceptionCode);
    }
}
