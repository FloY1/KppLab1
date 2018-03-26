package DataElements;

import java.sql.Date;
/** Класс хранящий историю действий пользователя
 * @author  artem.smolonskiy
 * @version 1.0
 */

public class UserHistory {

    /**Имя пользователя*/
    private String userName;

    /**Дата добавления*/
    private Date date;

    /**Дествие*/
    private String action;

    /**Лимит на добавление */
    private int addLimit;

    /**Метод для получения значения {@link UserHistory#addLimit}
     * @return лимит добавления
     */
    public int getAddLimit() {
        return addLimit;
    }

    /** Создаёт объект в соответствии с параметрами
     * @param userName имя пользователя
     * @param action действие
     * @param limit лимит на добавление
     */
    public UserHistory(String userName, String action,int limit) {

        this.date = new Date((new java.util.Date()).getTime()) ;
        this.userName = userName;
        this.addLimit = limit;
        this.action = action;
    }

    /** Создаёт объект в соответствии с параметрами
     * @param date дата
     * @param userName имя пользователя
     * @param action действие
     * @param limit лимит на добавление
     */
    public UserHistory(Date date, String userName, String action,int limit) {
        this.addLimit = limit;
        this.userName = userName;
        this.date = date;
        this.action = action;
    }

    /** Метод для получение значения {@link UserHistory#userName}
     * @return имя пользователя
     */
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "UserHistory{\n" +
                "userName='" + userName + '\'' +
                ", \ndate=" + date +
                ", \naction='" + action + '\'' +
                ", \naddLimit=" + addLimit +
                '}';
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** Метод для получение значения {@link UserHistory#date}
     * @return дата
     */
    public Date getDate() {
        return date;
    }

    /** Метод для получение значения {@link UserHistory#action}
     * @return действие пользователя
     */
    public String getAction() {
        return action;
    }
}
