package users.extendet;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdminUserTest {

    @Test
    public void isAdminPassword() {
        assertTrue(AdminUser.isAdminPassword("admin"));
    }
}