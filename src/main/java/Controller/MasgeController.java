package Controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class MasgeController {

    @FXML
    private Text masageText;


    public  void initialize(){

    }

    public void setMasageText(String masageText){
        this.masageText.setText(masageText);
    }

}
