package tests.sdktests;

import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import utils.PropertiesLoader;

public class CreateUserInvalidEmailTest extends PropertiesLoader {
    private String userId;
    private String exceptionMessage;

    @Feature("Create user sdk invalid email")
    @Test
    public void should_create_new_user() {
        try {
            userId = toolkit.create().user()
                    .withName("TestUser")
                    .withEmail("test123sm")
                    .withGender("male")
                    .withStatus("active")
                    .execute().getId();
        } catch (HttpClientErrorException exception) {
            exceptionMessage = exception.getResponseBodyAsString();
        }

        Assertions.assertEquals("[{\"field\":\"email\",\"message\":\"is invalid\"}]", exceptionMessage);

    }

}
