package users.extendet;

import dbLogic.dao.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import users.Users;

import java.io.File;
import java.util.Date;

public class NormalUser extends Users {

    private static final String password = UserService.getUserPassword("user");

    private  Date createDate ;

    private Logger logger = Logger.getLogger(NormalUser.class);

    private  long byteAddLimit;

    @Override
    public String toString() {
        return "isUser";
    }

    public static boolean isNormalUserPassword(String password){
        if(DigestUtils.md5Hex(password+"!789~234Q").equals(NormalUser.password))
            return true;
        else
            return false;
    }


    @Override
    public boolean canBeAdded(File file) {
        System.out.println(file.length());
        long currentHours = (new Date().getTime()- createDate.getTime())/(3600000);
        logger.debug("\nTime : "+currentHours+"\nLinit: "+byteAddLimit+"\nFile size: "+file.length());
        if(currentHours>24)
        {
            byteAddLimit = 10485760;
            Date date = new Date();
            date.setHours(0);
            date.setMinutes(0);
            createDate = date;
        }
        if(byteAddLimit-file.length()>0){
            byteAddLimit-=file.length();
            return true;
        }else
            return false;

    }

    public NormalUser() {
        byteAddLimit = 10485760;
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        createDate = date;
    }
    public int  getLimit(){
        return (int)byteAddLimit;
    }
}
