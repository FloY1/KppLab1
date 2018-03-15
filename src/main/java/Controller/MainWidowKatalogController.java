package Controller;



import Controller.MyEffect.Effects;

import DataElements.Data;
import Factory.UserFactory;
import dbLogic.dao.service.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.log4j.Logger;
import users.Users;

import java.io.File;
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

    @FXML Text katalogName;

    private ObservableList<Data> list = FXCollections.observableArrayList();

    private Users user;

    private DataService dataService;

    private Effects effect;

    private static final Logger logger = Logger.getLogger(MainWidowKatalogController.class);


    public void initialize() {
        tableColum.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        tableView.setItems(list);
        effect = new Effects(sigStackPane, userStack, menuPane, listPane, userName,userImg,katalogName);


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
            effect.catalogButtonClick(currentNode.getId(), user.iCanAdd(new File("")));
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
            logger.warn("NullPont user");
        }
    }


    public void exitClic() {

        effect.exitClic();
        user = null;
        logger.info("User came out");
    }



    public void addByttonClick(Event event) {
        File file = new FileChooser().showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file == null)
            logger.info("no choose File");
        else {

            Data data = new Data(file.getName(), file.getPath());
            list.add(data);
            dataService.add(data);
            logger.info("Successful");
        }
    }


    public void deleteButtonClick() {
        int currentRowIndex =tableView.getSelectionModel().getFocusedIndex();
        dataService.remove(list.get(currentRowIndex));
        list.remove(list.get(currentRowIndex));
        logger.info("Successful");
    }

}





