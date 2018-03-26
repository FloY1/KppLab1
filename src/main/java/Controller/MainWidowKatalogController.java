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

/** Класс управляет поведением окна MainWindowKatalog
 * @author  artem.smolonskiy
 * @version 1.0
 */

public class MainWidowKatalogController {

    /**Элемент интерфейса - Изображение пользователя*/
    @FXML
    private ImageView userImg;

    /**Элемент интерфейса - Имя пользователя*/
    @FXML
    private Text userName;

    /**Элемент интерфейса - Поле ввода пароля*/
    @FXML
    private PasswordField passwordField;

    /**Элемент интерфейса -Поле ввода логина*/
    @FXML
    private TextField loginField;

    /**Элемент интерфейса - Панель входа */
    @FXML
    private StackPane sigStackPane;

    /**Элемент интерфейса - Панель пользователя */
    @FXML
    private StackPane userStack;

    /**Элемент интерфейса - Панель меню */
    @FXML
    private Pane menuPane;

    /**Элемент интерфейса - Панель со списком файлов*/
    @FXML
    private Pane listPane;

    /**Элемент интерфейса - Таблица данных */
    @FXML
    private TableView<DataFile> tableView;

    /**Элемент интерфейса - Колонка таблицы */
    @FXML
    private  TableColumn<DataFile,String> tableColum;

    /**Элемент интерфейса - Поле с именем каталога */
    @FXML
    private  Text katalogName;

    /**Элемент интерфейса - Кнопка удалить */
    @FXML
    private Button deleteButton;

    /**Элемент интерфейса - Кнопка добавить*/
    @FXML
    private Button addButton;

    /**Элемент интерфейса - Полле с ошибкой входа*/
    @FXML
    private Text tetxErrorSignIn;

    /**Список данных */
    private ObservableList<DataFile> list = FXCollections.observableArrayList();

    /**Пользователь */
    private Users user;

    /**DAO - даныe о файле*/
    private DataService dataService;

    /**DAO - история каталога */
    private HistoryService historyService;

    /**Эфект */
    private Effects effect;

    /**Логер */
    private static final Logger logger = Logger.getLogger(MainWidowKatalogController.class);

    /**Окно для вывода сообщений*/
    private Stage massageStage = null;

    /** Крнтроллер для {@link MainWidowKatalogController#massageStage} */
    private MessageController messageController = null;

    /**Инициализирует {@link MainWidowKatalogController#effect} , задаёт обработчик событий при нажатиин строку таблицы  */
    public void initialize() {
        tableColum.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        tableView.setItems(list);
        effect = Effects.getEffectsForMainWindow();
        historyService = new HistoryService() ;

        tableView.setOnMouseClicked(event -> {
            if(event.getClickCount() >1)
            {
                DataFile curentDate = tableView.getSelectionModel().getSelectedItem();
                if(curentDate!=null)
                    curentDate.show();
            }
        });



    }

    /**Обработчик нажатия на клавишу домой , отображает панель меню и скрываает панель тблицы с данными */
    public void homeClick() {
        effect.twoElemeintFade(listPane,menuPane);
        list.clear();
        dataService.closeConnectin();
        logger.info("Successful");
    }

    /**Обработчик нажатия на кнопку каталога
     * @param event произошедшее событие
     * */
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

    /** Обработчик наведения курсора мыши на объект
     * @param actionEvent произошедшее событие
     */
    public void enteredButton(Event actionEvent) {

        effect.enteredButton(actionEvent);

    }

    /** Обработчик отведения курсора мыши от обхекта
     * @param actionEvent
     */
    public void exitedButton(Event actionEvent) {
        effect.exitedButton(actionEvent);

    }

    /**Обработчик нажатия на кнопку войти,
     * проверяет правильность введённых данных,
     * создаёт обект пользователя,
     * скрывает панель входа,
     * отображает панель пользователя.
     */
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

    /**Обработчик выхода пользователя,
     * скрывает панель пользователя,
     * отображает панель входа.
     */
    public void exitClic() {

        effect.twoElemeintFade(userStack,sigStackPane);
        effect.printErrorSignInMassage("",tetxErrorSignIn);
        user = null;
        logger.info("User came out");
    }


    /**Обработчик нажатия на кноку добавить,
     * открывает окно выбора файла,
     * вызывает окно с сообщение о ошибке если она произошла
     * @param event произошедшее событие
     * @throws IOException
     */
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


    /**Обработчик нажатия на кнопку удалить*/
    public void deleteButtonClick() {
        int currentRowIndex =tableView.getSelectionModel().getFocusedIndex();
        if (currentRowIndex==0) {
            logger.debug("null");
        }else {
            dataService.remove(list.get(currentRowIndex));
            historyService.add(new UserHistory(user.toString(), "delete", user.getLimit()));
            list.remove(list.get(currentRowIndex));
            logger.info("Successful");
        }
    }


    /** Возвращает расширение файла для текущего каталога
     * @return расширение файла
     */
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





