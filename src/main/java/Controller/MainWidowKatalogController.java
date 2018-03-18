package Controller;



import Controller.MyEffect.Effects;

import DataElements.Data;
import Factory.UserFactory;
import dbLogic.dao.service.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import users.Users;
import users.extendet.NormalUser;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainWidowKatalogController {

    @FXML
    private ImageView userImg;

    @FXML
    private Text userName;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    private StackPane sigStackPane;

    @FXML
    private StackPane userStack;

    @FXML
    private Pane menuPane;

    @FXML
    private Pane listPane;

    @FXML
    private TableView tableView;

    @FXML
    private  TableColumn<Data,String> tableColum;

    @FXML
    private  Text katalogName;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;

    @FXML
    private Text tetxErrorSignIn;

    private ObservableList<Data> list = FXCollections.observableArrayList();

    private Users user;

    private DataService dataService;

    private Effects effect;

    private static final Logger logger = Logger.getLogger(MainWidowKatalogController.class);

    private Stage massageStage = null;

    private MasgeController masgeController = null;

    public void initialize() {
        tableColum.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        tableView.setItems(list);
        effect = new Effects(
                sigStackPane,
                userStack,
                menuPane,
                listPane,
                userName,
                userImg,
                katalogName,
                addButton,
                deleteButton,
                tetxErrorSignIn);


    }

    public void homeClick() {
        effect.homeClick();
        list.clear();
        dataService.closeConnectin();
        logger.info("Successful");
    }

    public void catalogButtonClick(Event event) {
        if(user != null) {
            Node currentNode = (Node) event.getSource();
            dataService = new DataService(currentNode.getId());
            List<Data> dataList = dataService.getAll();
            list.addAll(dataList);
            effect.catalogButtonClick(currentNode.getId(), user);
        }
        else
        {
            effect.printErrorSignInMassage("log in");
        }

    }

    public void enteredButton(Event actionEvent) {

        effect.enteredButton(actionEvent);

    }

    public void exitedButton(Event actionEvent) {
        effect.exitedButton(actionEvent);

    }


    public void sigClick() {

        user = UserFactory.getUser(loginField.getText(),passwordField.getText());

        if(user!=null) {
            effect.sigClick(user.toString());
            logger.debug("User sign in");
        }
         else {
            effect.sigClick("q");
            logger.warn("NullPont user");
        }
    }


    public void exitClic() {

        effect.exitClic();
        user = null;
        logger.info("User came out");
    }



    public void addByttonClick(Event event) throws IOException {
        File file = new FileChooser().showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file == null)
            logger.info("no choose File");
        else {
            if (user.iCanAdd(file)) {
                Data data = new Data(file.getName(), file.getPath());
                list.add(data);
                dataService.add(data);
                logger.info("Successful");
            } else {
                if (user instanceof NormalUser) {

                    if (massageStage == null) {
                        massageStage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(MasgeController.class.getResource("../fxml/Masage.fxml"));
                        Parent root = fxmlLoader.load();
                        masgeController = fxmlLoader.getController();
                        massageStage.setScene(new Scene(root));
                        massageStage.setTitle("");
                        massageStage.initModality(Modality.WINDOW_MODAL);
                        massageStage.initOwner(
                                ((Node) event.getSource()).getScene().getWindow());
                    }
                    massageStage.show();
                    masgeController.setMasageText("Первышен лимит добавления, остаток: "+((NormalUser) user).getLimit()/1000+" KB");
                }
            }
        }
    }


    public void deleteButtonClick() {
        int currentRowIndex =tableView.getSelectionModel().getFocusedIndex();
        dataService.remove(list.get(currentRowIndex));
        list.remove(list.get(currentRowIndex));
        logger.info("Successful");
    }

}





