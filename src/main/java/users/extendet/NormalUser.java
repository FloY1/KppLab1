package users.extendet;

import org.apache.commons.codec.digest.DigestUtils;
import users.Users;

import java.util.Date;

public class NormalUser extends Users {

    private static final String password = "ee11cbb19052e40b07aac0ca060c23ee";

    @Override
    public String toString() {
        return "isUser";
    }

    public static boolean isNormalUserPassword(String password){
        if(DigestUtils.md5Hex(password).equals(NormalUser.password))
            return true;
        else
            return false;
    }


    private  long byteAddLimit;
    
    private final Date date ;

    public NormalUser() {
        byteAddLimit = 10485760;
        date = new Date();

    }
}
