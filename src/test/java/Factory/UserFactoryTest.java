package Factory;

import org.junit.Test;
import users.extendet.AdminUser;
import users.extendet.GuestUser;
import users.extendet.NormalUser;

import static org.junit.Assert.*;

public class UserFactoryTest {

    @Test
    public void getUser() {
        assertEquals(UserFactory.getUser("User","user").getClass(),new NormalUser().getClass());


        assertEquals(UserFactory.getUser("Admin","admin").getClass(),new AdminUser().getClass());

        assertEquals(UserFactory.getUser("Guest","").getClass(),new GuestUser().getClass());
    }
}