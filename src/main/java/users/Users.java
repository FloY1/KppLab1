package users;

import java.io.File;

public abstract class Users  {



    public  boolean canBeAdded(File file) {
        return false;
    }

    public boolean canBeDeleted(){
        return false;

    }
    public int  getLimit(){
        return 0;
    }


    @Override
    public String toString() {
        return "null";

    }
}
