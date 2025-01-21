package tests.openapitest;

import com.user_service.model.UserPojo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import utils.PropertiesLoader;

@Epic("OpenApi Tests")
@Feature("Get users")
public class GetUserNegativeScenariosTests extends PropertiesLoader {
    @Test
    public void getNonexistentUser() {
        int statusCode = 0;
        String userId = String.valueOf((int) (Math.random() * 1000));
        try {
            ResponseEntity<UserPojo> userPojo = toolkit.get().user(userId);
        } catch (HttpClientErrorException httpClientErrorException) {
            statusCode = httpClientErrorException.getRawStatusCode();
        }
        Assertions.assertEquals(404, statusCode);
    }
}
