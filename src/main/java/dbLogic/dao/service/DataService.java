package dbLogic.dao.service;

import DataElements.DataFile;
import Factory.DataFactory;
import dbLogic.dao.DataDAO;
import dbLogic.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/** Класс для считывания и вывода данных DataFile в MySql
 * @author  artem.smolonskiy
 * @version 1.0
 */
public  class  DataService extends Util implements DataDAO {

    /**
     * Логер
     */
    private  final static Logger logger = Logger.getLogger(DataService.class);

    /**
     * Конекшен к базе
     */
    private  Connection connection = getConnection();

    /**
     * Имя таблицы базы
     */
    private  String tableName;

    /**
     * Создание объекта в соответствии с данными
     * @param tableName имя таблицы
     */
    public DataService(String tableName) {
        this.tableName = tableName;
        logger.debug("set tableName "+tableName);
    }

    /**
     * Вывод в бд
     * @param dataFile что выводим
     */
    @Override
    public void add(DataFile dataFile)  {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO "+tableName+" (NAME, URL) VALUES(?, ?) ";
        logger.debug("create sql :"+sql);

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, dataFile.getFileName());
            preparedStatement.setString(2, dataFile.getUrl());
            logger.debug("add Date {"+ dataFile.getFileName()+"; "+ dataFile.getUrl()+"} successful");

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

    /**
     * Считывание из бд
     * @return Список даных
     */
    @Override
    public List<DataFile> getAll()  {
        List<DataFile> dataFileList =  new ArrayList<>();



        String sql = "SELECT ID, NAME, URL FROM "+tableName;
        logger.debug(" create sql :"+sql);
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while( resultSet.next()){

                DataFile dataFile = DataFactory.getData(tableName);
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
        logger.debug("Get successful");
        return dataFileList;
    }


    /**
     * Обновление в бд
     * @param dataFile новый файл
     */
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
            logger.debug("Update successful");

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

    /**
     * Удаление из бд
     * @param dataFile удаляймый файл
     */
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

    /**
     * Закрытие конекшена
     */
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
