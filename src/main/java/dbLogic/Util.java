package dbLogic;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private  static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private  static final String DB_URL = "jdbc:mysql://localhost:3306/Test";
    private  static final String DB_USERNAME = "root";
    private  static final String DB_PASSWORD = "root";
    private  static final Logger logger = Logger.getLogger(Util.class);

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            logger.debug("set DB_DRIVER");
            Properties properties = new Properties();
            properties.setProperty("user", DB_USERNAME);
            properties.setProperty("password",DB_PASSWORD);
            
            connection = DriverManager.getConnection(DB_URL,properties);
            logger.info("Connection successful");
        } catch (SQLException | ClassNotFoundException e) {
            logger.warn(e);
        }
        return  connection;
    }

}
