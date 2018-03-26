package users.extendet;

import DataElements.UserHistory;
import dbLogic.dao.service.HistoryService;
import dbLogic.dao.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import users.Users;

import java.io.File;
import java.sql.Date;
/** Класс пользователя с правами User
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class NormalUser extends Users {

    /**
     * Пароль
     */
    private static final String password = UserService.getUserPassword("user");

    /**
     * Дата последнего добавления
     */
    private  Date lastAddDate;

    /**
     * Логер
     */
    private Logger logger = Logger.getLogger(NormalUser.class);

    /**
     * Лимит на добавление
     */
    private  long byteAddLimit;

    @Override
    public String toString() {
        return "isUser";
    }

    /**
     * Сравнивает пароль с входными данными
     * @param password вводимый пароль
     * @return совпадает или нет
     */
    public static boolean isNormalUserPassword(String password){
        if(DigestUtils.md5Hex(password+"!789~234Q").equals(NormalUser.password))
            return true;
        else
            return false;
    }


    /**
     * Способен ли добавить файл
     * @param file добавляемый файл
     * @return да или нет
     */
    @Override
    public boolean canBeAdded(File file) {
        System.out.println(file.length());
        long currentHours = (new Date((new java.util.Date()).getTime()).getTime()- lastAddDate.getTime())/(3600000);
        logger.debug("\nTime : "+currentHours+"\nLinit: "+byteAddLimit+"\nFile size: "+file.length());
        if(currentHours>=24)
        {
            byteAddLimit = 10485760;
            Date date = (new Date((new java.util.Date()).getTime()));
            lastAddDate = date;
        }
        if(byteAddLimit-file.length()>0){
            byteAddLimit-=file.length();
            return true;
        }else
            return false;

    }

    /**
     * Создаёт объект
     */
    public NormalUser() {
        HistoryService historyService = new HistoryService();
        logger.debug("create history service");
        UserHistory userHistory = historyService.getLastUserDate();
        logger.debug("get last Date");
        lastAddDate = userHistory.getDate();
        byteAddLimit = userHistory.getAddLimit();
    }

    /**
     * Возвращает поле
     * @return лимит добвления
     */
    public int  getLimit(){
        return (int)byteAddLimit;
    }
}
