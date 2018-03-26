package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** Класс управляет поведением окна Massage
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class MessageController {

    /**Элемент интерфейса*/
    @FXML
    private Text masageText;

    /**Ничего не делает*/
    public  void initialize(){

    }

    /**Обработчик нажатия на кнопку выхода*/
    public void exitButton(ActionEvent actionEvent){
        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.hide();

    }

    /**Метод выводит сообщение на {@link MessageController#masageText}
     * @param masageText текст сообщения
     */
    public void setMasageText(String masageText){
        this.masageText.setText(masageText);

    }



}
