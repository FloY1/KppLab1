package Factory;

import users.Users;
import org.apache.log4j.Logger;
import users.extendet.AdminUser;
import users.extendet.GuestUser;
import users.extendet.NormalUser;
/** Класс фабрика для класса Users
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class UserFactory {
    /**
     * Логер
     */
    private  static Logger logger = Logger.getLogger(UserFactory.class);

    /**
     * Создаёт объект в соответствии с введёнными параметрами
     * @param userName имя пользователя
     * @param password пароль
     * @return объект класса
     */
    public static Users getUser(String userName,String password) {
        Users user = null;

        if (userName.equals("Admin"))
            if (AdminUser.isAdminPassword(password)) {
                user = new AdminUser();
            } else {
                logger.warn("incorrect Admin password");
                return user;
            }
        if (userName.equals("User"))
            if (NormalUser.isNormalUserPassword(password)) {
                user = new NormalUser();
            } else {
                logger.warn("incorrect User password");
                return user;
            }
        if(userName.equals("Guest"))
        {
            user = new GuestUser();
        }else {
            if(user==null)
                logger.warn("incorrect login to the system");

        }
        logger.debug(user);
        return user;
    }
}
