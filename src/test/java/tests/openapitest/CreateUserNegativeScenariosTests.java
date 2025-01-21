package tests.openapitest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import utils.PropertiesLoader;

@Epic("OpenApi Tests")
@Feature("Create user")
public class CreateUserNegativeScenariosTests extends PropertiesLoader {

    @Test
    public void createUserWithoutName() {
        String exceptionMessage = null;

        String email = "user" + (int) (Math.random() * 1000) + "@testemail.com";
        try {
            toolkit.create().user()
                    .withName(null)
                    .withEmail(email)
                    .withGender("male")
                    .withStatus("active")
                    .execute();
        } catch (HttpClientErrorException exception) {
            exceptionMessage = exception.getResponseBodyAsString();
        }

        Assertions.assertEquals("[{\"field\":\"name\",\"message\":\"can't be blank\"}]", exceptionMessage);

    }

    @Test
    public void createUserWithInvalidEmail() {
        String exceptionMessage = null;
        try {
            toolkit.create().user()
                    .withName("TestUser")
                    .withEmail("test123sm")
                    .withGender("male")
                    .withStatus("active")
                    .execute();
        } catch (HttpClientErrorException exception) {
            exceptionMessage = exception.getResponseBodyAsString();
        }

        Assertions.assertEquals("[{\"field\":\"email\",\"message\":\"is invalid\"}]", exceptionMessage);

    }
}
