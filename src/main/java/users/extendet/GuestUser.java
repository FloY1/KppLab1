package users.extendet;

import users.Users;
/** Класс пользователя с правами Guest
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class GuestUser extends Users {
    @Override
    public String toString() {
        return "isGuest";
    }
}
