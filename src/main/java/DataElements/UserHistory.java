package DataElements;

import java.sql.Date;

public class UserHistory {
    private String userName;
    private Date date;
    private String action;

    public int getAddLimit() {
        return addLimit;
    }

    public void setAddLimit(int addLimit) {
        this.addLimit = addLimit;
    }

    private int addLimit;

    public UserHistory(String userName, String action,int limit) {

        this.date = new Date((new java.util.Date()).getTime()) ;
        this.userName = userName;
        this.addLimit = limit;
        this.action = action;
    }

    public UserHistory(Date date, String userName, String action,int limit) {
        this.addLimit = limit;
        this.userName = userName;
        this.date = date;
        this.action = action;
    }

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
