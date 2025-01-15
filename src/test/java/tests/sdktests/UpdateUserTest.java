package tests.sdktests;

import io.qameta.allure.Feature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import utils.PropertiesLoader;

public class UpdateUserTest extends PropertiesLoader {
    private String userId;

    @Before
    public void createUser(){
        userId = toolkit.create().user()
                .withName("TestUser")
                .withEmail("testufserdsdsds@test.com")
                .withGender("male")
                .withStatus("active")
                .execute().getId();

        Assertions.assertTrue(toolkit.get().allUsers().toString().contains(userId));
    }

    @Feature("Update user sdk")
    @Test
    public void updateUser() {
        String newName = toolkit.update().user()
                .withName("TestUserModified")
                .withId(userId)
                .withGender("female")
                .withEmail("testufserdsdsds@test.com")
                .withStatus("inactive")
                        .execute().getName();

        Assertions.assertTrue(toolkit.get().allUsers().toString().contains(newName));

    }

    @After
    public void deleteUser(){
        toolkit.delete().user(userId);
        Assertions.assertFalse(toolkit.get().allUsers().toString().contains(userId));
    }
}
