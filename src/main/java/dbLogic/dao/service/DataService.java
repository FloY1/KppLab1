package dbLogic.dao.service;

import DataElements.DataFile;
import Factory.DataFactory;
import dbLogic.dao.DataDAO;
import dbLogic.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class  DataService extends Util implements DataDAO {

    private  final static Logger logger = Logger.getLogger(DataService.class);


    private  Connection connection = getConnection();

    private  String tableName;

    public DataService(String tableName) {
        this.tableName = tableName;
        logger.debug("set tableName "+tableName);
    }

    @Override
    public void add(DataFile dataFile)  {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO "+tableName+" (NAME, URL) VALUES(?, ?) ";
        logger.debug("create sql :"+sql);

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, dataFile.getFileName());
            preparedStatement.setString(2, dataFile.getUrl());
            logger.info("add Date {"+ dataFile.getFileName()+"; "+ dataFile.getUrl()+"} successful");

            preparedStatement.executeLargeUpdate();

        } catch (SQLException e) {
            logger.warn(e);
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.warn(e);
                }
            } else {
                logger.warn("Statement = " + preparedStatement);
            }
        }


    }

    @Override
    public List<DataFile> getAll()  {
        List<DataFile> dataFileList = new ArrayList<>();



        String sql = "SELECT ID, NAME, URL FROM "+tableName;
        logger.debug(" create sql :"+sql);
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while( resultSet.next()){
                DataFile dataFile = DataFactory.geData(tableName);
                dataFile.setId(resultSet.getInt("ID"));
                dataFile.setFileName(resultSet.getString("NAME"));
                dataFile.setUrl(resultSet.getString("URL"));
                logger.debug("get dataFile in DB :" + dataFile);
                dataFileList.add(dataFile);


            }

        } catch (SQLException e) {
            logger.warn(e);
        } finally {

            if (statement != null) {
                try {
                    statement.close();

                } catch (SQLException e) {
                    logger.warn(e);
                }
            } else {
                logger.warn("Statement = " + statement);
            }


        }
        logger.info("Get successful");
        return dataFileList;
    }




    @Override
    public void update(DataFile dataFile) {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE "+tableName+" SET NAME=?, URL=? WHERE ID=?";
        logger.debug(" create sql :"+sql);
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, dataFile.getFileName());
            preparedStatement.setString(2, dataFile.getUrl());
            preparedStatement.setLong(3, dataFile.getId());

            preparedStatement.executeLargeUpdate();
            logger.info("Update successful");

        } catch (SQLException e) {
            logger.warn(e);
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.warn(e);
                }
            } else {
                logger.warn("Statement = " + preparedStatement);
            }
        }

    }

    @Override
    public void remove(DataFile dataFile) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM " + tableName + " WHERE ID=?";
        logger.debug(" create sql :" + sql);
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, dataFile.getId());


            preparedStatement.executeLargeUpdate();
            logger.info("Delete successful");
        } catch (SQLException e) {
            logger.warn(e);
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.warn(e);
                }
            } else {
                logger.warn("Statement = " + preparedStatement);
            }
        }
    }

    public void closeConnectin(){
        if(connection!=null)
        try {
            connection.close();
            logger.info("close connection");
        } catch (SQLException e) {
            logger.warn(e);
        }
        else
            logger.error("Null connection");
    }
}
