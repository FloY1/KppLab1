package dbLogic.dao;


import DataElements.DataFile;

import java.util.List;

public interface DataDAO {

     //create
     void add(DataFile dataFile);

     //read
     List<DataFile> getAll() ;


     //update
     void update(DataFile dataFile) ;

     //delete
     void remove(DataFile dataFile) ;


}

