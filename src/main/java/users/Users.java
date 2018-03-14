package users;

import DataElements.Data;
import dbLogic.dao.DataDAO;
import dbLogic.dao.service.DataService;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.sql.SQLException;

public abstract class Users  {


    public void show(Data data,Node node){
    }
    public boolean iCanAdd() {
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
