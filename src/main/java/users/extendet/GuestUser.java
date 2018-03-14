package users.extendet;

import users.Users;

public class GuestUser extends Users {
    @Override
    public String toString() {
        return "isGuest";
    }
}
