package users.extendet;

import dbLogic.dao.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import users.Users;

import java.io.File;
/** Класс пользователя с правами admin
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class AdminUser extends Users {
    /**
     * Пароль
     */
    private static final String password = UserService.getUserPassword("admin");

    /**
     * Сравнивает пароль с входными данными
     * @param password вводимы пароль
     * @return совпадает или нет
     */
    public static boolean isAdminPassword(String password){
        if(DigestUtils.md5Hex(password+"!789~234Q").equals(AdminUser.password))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "isAdmin";
    }

    /**
     * Провеяет спосоность добавит файл
     * @param file добавляемый файл
     * @return true
     */
    @Override
    public boolean canBeAdded(File file) {
        return true;
    }

    /**
     * Проверяет способность удалить
     * @return true
     */
    @Override
    public boolean canBeDeleted() {
        return true;
    }
}
