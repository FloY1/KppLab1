package Controller;

import DataElements.Data;
import DataElements.extendet.Book;
import DataElements.extendet.Doc;
import DataElements.extendet.Film;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MasgeController {

    @FXML
    private Text masageText;


    public  void initialize(){

    }

    public void exitButton(ActionEvent actionEvent){
        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.hide();

    }

    public void setMasageText(String masageText){
        this.masageText.setText(masageText);

    }



}
