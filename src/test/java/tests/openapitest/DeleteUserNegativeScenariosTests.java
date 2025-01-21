package tests.openapitest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import utils.PropertiesLoader;

@Epic("OpenApi Tests")
@Feature("Delete user")
public class DeleteUserNegativeScenariosTests extends PropertiesLoader {
    @Test
    public void deleteNonExistingUser() {
        int statusCode = 0;
        String userId = String.valueOf((int) (Math.random() * 1000));
        try {
            toolkit.delete().user(userId);
        } catch (HttpClientErrorException httpClientErrorException) {
            statusCode = httpClientErrorException.getRawStatusCode();
        }
        Assertions.assertEquals(404, statusCode);
    }
}
