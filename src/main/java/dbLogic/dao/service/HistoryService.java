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

    public void add(UserHistory userHistory) {
        PreparedStatement preparedStatement = null;

            String sql = "INSERT INTO History (date_action , user_name, actions) VALUES(?, ?, ?) ";
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

        String sql = "SELECT date_action , user_name, actions, add_limit FROM History";
        logger.debug(" create sql :" + sql);
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userHistoryList.add((new UserHistory(resultSet.getDate("date_action"),
                        resultSet.getString("user_name"),
                        resultSet.getString("actions"),
                        resultSet.getInt("add_limit"))));

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

    public UserHistory getLastUserDate() {
        UserHistory userHistory = null;
        String sql = "SELECT max(date_action), add_limit FROM History WHERE user_name = 'isUser' and actions='add' ";

        logger.debug(" create sql :" + sql);
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            userHistory = new UserHistory(resultSet.getDate(
                    "max(date_action)"),
                    "isUser",
                    "add",
                    resultSet.getInt("add_limit"));


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
            return userHistory;
        }


    }
}
