package dbLogic;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            logger.debug(" set DB_DRIVER");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            logger.info("Connection successful");
        } catch (SQLException | ClassNotFoundException e) {
            logger.warn("trow "+e);
        }
        return  connection;
    }

}
