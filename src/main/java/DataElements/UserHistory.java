package DataElements;

import java.sql.Date;

public class UserHistory {
    private String userName;
    private Date date;
    private String action;

    public UserHistory(String userName, String action) {
        this.date = new Date((new java.util.Date()).getTime()) ;
        this.userName = userName;
        this.action = action;
    }

    @Override
    public String toString() {
        return "UserHistory{" +
                "userName='" + userName + '\'' +
                ", date=" + date +
                ", action='" + action + '\'' +
                '}';
    }

    public UserHistory(Date date, String userName, String action) {
        this.userName = userName;
        this.date = date;
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
