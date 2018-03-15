package users.extendet;

import org.apache.commons.codec.digest.DigestUtils;
import users.Users;

import java.io.File;

public class AdminUser extends Users {

    private static final String password = "21232f297a57a5a743894a0e4a801fc3";

    public static boolean isAdminPassword(String password){
        if(DigestUtils.md5Hex(password).equals(AdminUser.password))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "isAdmin";
    }

    @Override
    public boolean iCanAdd(File file) {
        return true;
    }

    @Override
    public boolean iCanDelete() {
        return true;
    }
}
