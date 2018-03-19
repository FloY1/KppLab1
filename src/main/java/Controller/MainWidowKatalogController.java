package Controller;



import Controller.MyEffect.Effects;
import DataElements.DataFile;
import DataElements.UserHistory;
import Factory.UserFactory;
import dbLogic.dao.service.DataService;
import dbLogic.dao.service.HistoryService;
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
    private TableView<DataFile> tableView;

    @FXML
    private  TableColumn<DataFile,String> tableColum;

    @FXML
    private  Text katalogName;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;

    @FXML
    private Text tetxErrorSignIn;

    private ObservableList<DataFile> list = FXCollections.observableArrayList();

    private Users user;

    private DataService dataService;

    private HistoryService historyService;

    private Effects effect;

    private static final Logger logger = Logger.getLogger(MainWidowKatalogController.class);

    private Stage massageStage = null;

    private MessageController messageController = null;

    public void initialize() {
        tableColum.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        tableView.setItems(list);
        effect = Effects.getEffectsForMainWindow();
        historyService = new HistoryService();

        tableView.setOnMouseClicked(event -> {
            if(event.getClickCount() >1)
            {
                DataFile curentDate = tableView.getSelectionModel().getSelectedItem();
                if(curentDate!=null)
                    curentDate.show();
            }
        });



    }

    public void homeClick() {
        effect.twoElemeintFade(listPane,menuPane);
        list.clear();
        dataService.closeConnectin();
        logger.info("Successful");
    }

    public void catalogButtonClick(Event event) {
        if(user != null) {
            Node currentNode = (Node) event.getSource();
            dataService = new DataService(currentNode.getId());
            List<DataFile> dataFileList = dataService.getAll();
            list.addAll(dataFileList);
            effect.userLogIn(currentNode.getId(), user,addButton,deleteButton,katalogName);
            effect.twoElemeintFade(menuPane,listPane);
        }
        else
        {
            effect.printErrorSignInMassage("log in",tetxErrorSignIn);
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
            effect.enty(user.toString(),userName,userImg,sigStackPane,userStack);
            logger.debug("User sign in");
        }
         else {
            effect.printErrorSignInMassage("incorrect",tetxErrorSignIn);

            logger.warn("NullPont user");
        }
    }


    public void exitClic() {

        effect.twoElemeintFade(userStack,sigStackPane);
        effect.printErrorSignInMassage("",tetxErrorSignIn);
        user = null;
        logger.info("User came out");
    }



    public void addByttonClick(Event event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(katalogName.getText(),getFilter())
        );
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file == null)
            logger.info("no choose File");
        else {
            if (user.canBeAdded(file)) {
                DataFile dataFile = new DataFile(file.getName(), file.getPath());
                list.add(dataFile);
                dataService.add(dataFile);
                historyService.add(new UserHistory(user.toString(),"add",user.getLimit()));
                logger.info("Successful");
            } else {
                if (user instanceof NormalUser) {

                    if (massageStage == null) {
                        massageStage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(MessageController.class.getResource("../fxml/Masage.fxml"));
                        Parent root = fxmlLoader.load();
                        messageController = fxmlLoader.getController();
                        massageStage.setScene(new Scene(root));
                        massageStage.setTitle("");
                        massageStage.initModality(Modality.WINDOW_MODAL);
                        massageStage.initOwner(
                                ((Node) event.getSource()).getScene().getWindow());
                    }
                    massageStage.show();
                    messageController.setMasageText("Первышен лимит добавления, остаток: "+((NormalUser) user).getLimit()/1000+" KB");
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


    public void tableClicked(Event event){

    }

    public String getFilter() {
        if(katalogName.getText().equals("Music"))
            return "*.mp3";
        if(katalogName.getText().equals("Film"))
            return "*.mp4";
        if(katalogName.getText().equals("Book"))
            return "*.pdf";
        if(katalogName.getText().equals("Doc"))
            return "*.docx";
        return "*.*";

    }
}





