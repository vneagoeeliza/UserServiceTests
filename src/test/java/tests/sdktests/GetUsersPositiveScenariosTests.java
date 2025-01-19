package tests.sdktests;

import com.user_service.model.UserPojo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;
import utils.PropertiesLoader;

import java.util.List;

@Epic("OpenAi Tests")
@Feature("Get users")
public class GetUsersPositiveScenariosTests extends PropertiesLoader {

    @Test
    public void getAllUsers() {
        ResponseEntity<List<UserPojo>> response = toolkit.get().allUsers();
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotEquals(0, response.getBody().size());

    }

    @Test
    public void getSingleUser() {
        ResponseEntity<List<UserPojo>> responseGetAllUsers = toolkit.get().allUsers();
        UserPojo firstUserInList = responseGetAllUsers.getBody().get(0);
        String userId = firstUserInList.getId();
        String name = firstUserInList.getName();

        ResponseEntity<UserPojo> responseGetSingleUser = toolkit.get().user(userId);
        Assertions.assertEquals(200, responseGetSingleUser.getStatusCode().value());
        String userId2 = responseGetSingleUser.getBody().getId();
        String name2 = responseGetSingleUser.getBody().getName();
        Assertions.assertEquals(userId, userId2);
        Assertions.assertEquals(name, name2);
    }
}
