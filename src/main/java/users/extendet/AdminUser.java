package users.extendet;

import dbLogic.dao.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import users.Users;

import java.io.File;

public class AdminUser extends Users {

    private static final String password = UserService.getUserPassword("admin");
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

    @Override
    public boolean canBeAdded(File file) {
        return true;
    }

    @Override
    public boolean canBeDeleted() {
        return true;
    }
}
