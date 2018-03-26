package DataElements;

import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class UserHistoryTest {
    UserHistory userHistory = new UserHistory(new Date(0),"user","add",0);

    @Test
    public void getAddLimit() {
        assertEquals(userHistory.getAddLimit(),0);
    }

    @Test
    public void getUserName() {
        assertEquals(userHistory.getUserName(),"user");
    }

    @Test
    public void getDate() {
        assertEquals(userHistory.getDate(),new Date(0));
    }

    @Test
    public void getAction() {
        assertEquals(userHistory.getAction(),"add");
    }
}