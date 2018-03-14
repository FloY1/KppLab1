package dbLogic.dao;


import DataElements.Data;

import java.sql.SQLException;
import java.util.List;

public interface DataDAO {

     //create
     void add(Data data);

     //read
     List<Data> getAll() ;


     //update
     void update(Data data) ;

     //delete
     void remove(Data data) ;


}

