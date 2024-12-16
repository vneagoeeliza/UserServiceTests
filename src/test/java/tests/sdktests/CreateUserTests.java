package tests.sdktests;

import io.qameta.allure.Feature;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import utils.PropertiesLoader;

public class CreateUserTests extends PropertiesLoader {

    private String userId;

@Feature("Create user sdk")
    @Test
    public void should_create_new_user() {
         userId = toolkit.create().user()
                .withName("TestUser")
                .withEmail("testuser@test.com")
                .withGender("male")
                .withStatus("active")
                .execute().getId();

        Assertions.assertTrue(toolkit.get().allUsers().toString().contains(userId));

    }

    @After

    public void should_delete_user(){
    toolkit.delete().user(userId);
      Assertions.assertFalse(toolkit.get().allUsers().toString().contains(userId));

    }
}
