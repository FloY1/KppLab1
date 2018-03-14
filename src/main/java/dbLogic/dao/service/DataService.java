package dbLogic.dao.service;

import DataElements.Data;
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
    public void add(Data data)  {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO "+tableName+" (NAME, URL) VALUES(?, ?) ";
        logger.debug("create sql :"+sql);

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, data.getFileName());
            preparedStatement.setString(2,data.getUrl());
            logger.info("add Date {"+data.getFileName()+"; "+data.getUrl()+"} successful");

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
    public List<Data> getAll()  {
        List<Data>  dataList = new ArrayList<>();



        String sql = "SELECT ID, NAME, URL FROM "+tableName;
        logger.debug(" create sql :"+sql);
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while( resultSet.next()){
                Data data = DataFactory.geData(tableName);
                data.setId(resultSet.getInt("ID"));
                data.setFileName(resultSet.getString("NAME"));
                data.setUrl(resultSet.getString("URL"));
                logger.debug("get data in DB :" + data);
                dataList.add(data);


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
        return  dataList;
    }




    @Override
    public void update(Data data) {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE "+tableName+" SET NAME=?, URL=? WHERE ID=?";
        logger.debug(" create sql :"+sql);
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,data.getFileName());
            preparedStatement.setString(2,data.getUrl());
            preparedStatement.setLong(3,data.getId());

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
    public void remove(Data data) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM " + tableName + " WHERE ID=?";
        logger.debug(" create sql :" + sql);
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, data.getId());


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
