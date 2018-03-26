package users.extendet;

import org.junit.Test;

import static org.junit.Assert.*;

public class NormalUserTest {

    @Test
    public void isNormalUserPassword() {
        assertTrue(NormalUser.isNormalUserPassword("user"));
    }
}