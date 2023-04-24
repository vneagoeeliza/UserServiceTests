import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import utils.PropertiesLoader;


public class UserServiceTests extends PropertiesLoader {

    @Test
    public void should_get_all_users() {
      Assertions.assertNotNull(toolkit.get().allUsers());
    }
}
