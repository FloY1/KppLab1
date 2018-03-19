package users;

import java.io.File;

public abstract class Users  {



    public  boolean iCanAdd(File file) {
        return false;
    }

    public boolean  iCanDelete(){
        return false;

    }

    @Override
    public String toString() {
        return "null";
    }
}
