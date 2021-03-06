package dbLogic;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/** Класс для соеденения с Бд MySQL
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class Util {
    /**Драйвер базы*/
    private  static final String DB_DRIVER = "com.mysql.jdbc.Driver";

    /**URL базы*/
    private  static final String DB_URL = "jdbc:mysql://localhost:3306/MyBd";

    /**Имя пользователя базы*/
    private  static final String DB_USERNAME = "root";

    /**Пароль бд*/
    private  static final String DB_PASSWORD = "root";

    /**Логер*/
    private  static final Logger logger = Logger.getLogger(Util.class);

    /**
     * Соеденяется с базой
     * @return конекшен к базе
     */
    protected static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            logger.debug("set DB_DRIVER");
            Properties properties = new Properties();
            properties.setProperty("user", DB_USERNAME);
            properties.setProperty("password",DB_PASSWORD);
            
            connection = DriverManager.getConnection(DB_URL,properties);
            logger.debug("Connection successful");
        } catch (SQLException | ClassNotFoundException e) {
            logger.warn(e);
        }
        return  connection;
    }

}
