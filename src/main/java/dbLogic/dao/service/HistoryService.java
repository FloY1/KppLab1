package dbLogic.dao.service;

import DataElements.DataFile;
import DataElements.UserHistory;
import Factory.DataFactory;
import dbLogic.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryService extends Util {

    private final static Logger logger = Logger.getLogger(DataService.class);


    private Connection connection = getConnection();

    void add(UserHistory userHistory) {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO HISTORY (DATETIME, USER_NAME,Action) VALUES(?, ?, ?) ";
        logger.debug("create sql :" + sql);

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, userHistory.getDate());
            preparedStatement.setString(2, userHistory.getUserName());
            preparedStatement.setString(3, userHistory.getAction());

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

    //read
    public List<UserHistory> getAll() {
        List<UserHistory> userHistoryList = new ArrayList<>();

        String sql = "SELECT DATE, USER_NAME, ACTION FROM HISTORY";
        logger.debug(" create sql :" + sql);
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userHistoryList.add((new UserHistory(resultSet.getDate("DATE"),
                        resultSet.getString("USER_NAME"),
                        resultSet.getString("ACTION"))));

                logger.debug("get dataFile in DB");


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
        return userHistoryList;
    }

    public Date getLastUserDate() {
        Date date = null;
        String sql = "SELECT max(date) FROM Book WHERE name = 'user' ";

        logger.debug(" create sql :" + sql);
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            date = resultSet.getDate("max(date)");


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
            return date;
        }


    }
}
