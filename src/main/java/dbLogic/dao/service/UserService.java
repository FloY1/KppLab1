package dbLogic.dao.service;

import dbLogic.Util;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService extends Util {



    private static Map<String,String> userInfoMap = new HashMap<>();


    private  final static Logger logger = Logger.getLogger(DataService.class);


    static {
        String sql = "SELECT ID, USER_NAME, USER_PASSWORD FROM UserInfo";
        logger.debug(sql);
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userInfoMap.put(resultSet.getString("USER_NAME"),
                        resultSet.getString("USER_PASSWORD"));
            }
        logger.debug("cread userList");
        }catch (SQLException e) {
            logger.warn(e);
        }finally {

            try {
                statement.cancel();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   public static  String getUserPassword(String userName){

        return userInfoMap.get(userName);
   }

}
