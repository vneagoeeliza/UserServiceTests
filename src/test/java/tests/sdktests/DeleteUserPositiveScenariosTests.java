package tests.sdktests;

import com.user_service.model.UserPojo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import utils.PropertiesLoader;

@Epic("OpenAi Tests")
@Feature("Delete user")
class DeleteUserPositiveScenariosTests extends PropertiesLoader {
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
    void deleteUser() {
        int statusCode = 0;
        ResponseEntity<Void> responseDelete = toolkit.delete().user(userId);
        Assertions.assertEquals(204, responseDelete.getStatusCode().value());
        try {
            ResponseEntity<UserPojo> responseGet = toolkit.get().user(userId);
        } catch (HttpClientErrorException httpClientErrorException) {
            statusCode = httpClientErrorException.getRawStatusCode();
        }
        Assertions.assertEquals(404, statusCode);
    }
}
