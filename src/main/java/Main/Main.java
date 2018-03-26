package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
/** Глайвный класс
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class Main extends Application {
    /**
     * Создаёт главное окно
     * @param primaryStage Stage окна
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainWindowKatalog.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

        primaryStage.show();





    }

    /**
     * Стартовый метод
     * @param args дескрипт консоли
     */
    public static void main(String[] args) {
        launch(args);
    }
}