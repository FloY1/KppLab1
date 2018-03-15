package Controller.MyEffect;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import users.Users;

import java.io.File;


public class Effects {


    private  Text katalogName;
    private  ImageView userImage;
    private  Text userName;
    private  StackPane sigStackPane;
    private  StackPane userStack;
    private  Pane menuPane;
    private  Pane listPane;
    private  DropShadow dropShadow;
    private  Button addButton;
    private  Button deleteButton;
    private  Text tetxErrorSignIn;

    public Effects(StackPane sigStackPane, StackPane userStack, Pane menuPane, Pane listPane, Text userName, ImageView userImg, Text katalogName, Button addButton, Button deleteButton, Text tetxErrorSignIn) {
        this.tetxErrorSignIn = tetxErrorSignIn;
        this.addButton = addButton;
        this.deleteButton = deleteButton;
        this.katalogName = katalogName;
        this.userName = userName;
        this.userImage = userImg;
        this.sigStackPane = sigStackPane;
        this.userStack = userStack;;
        this.menuPane = menuPane;
        this.listPane = listPane;
        dropShadow = new DropShadow();

        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.color(0.1, 0, 0.1));


    }

    private void  maikFade(Node node, int fromV, int toV){
        FadeTransition fadeTransition  = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(fromV);
        fadeTransition.setToValue(toV);
        fadeTransition.play();
    }
    public void homeClick(){
        maikFade(menuPane,0,1);
        menuPane.setDisable(false);

        maikFade(listPane,1,0);
        listPane.setDisable(true);
    }
    public void catalogButtonClick(String name, Users user) {
        deleteButton.setDisable(!user.iCanDelete());
        addButton.setDisable(!user.iCanAdd(new File("")));
        katalogName.setText(name);
        maikFade(menuPane, 1, 0);
        menuPane.setDisable(true);
        maikFade(listPane, 0, 1);
        listPane.setDisable(false);

    }
    public  void enteredButton(Event actionEvent){

        Object sourse = actionEvent.getSource();
        if(!(sourse instanceof Node))
            return;
        ((Node)sourse).setEffect(dropShadow);
    }


    public void exitedButton(Event actionEvent){
        Object sourse = actionEvent.getSource();
        if(!(sourse instanceof Node))
            return;
        ((Node)sourse).setEffect(null);


    }
    public void sigClick(String userName)  {
        if(userName.equals("isAdmin")) {
            this.userName.setText("Admin");
            this.userImage.setImage(new Image("img/admin.png"));
        }
        if(userName.equals("isUser")){
            this.userName.setText("User");
            this.userImage.setImage(new Image("img/user.png"));
        }
        if(userName.equals("isGuest")){
            this.userName.setText("Guest");
            this.userImage.setImage(new Image("img/guest.png"));
        }
        if(userName.equals("isAdmin")||userName.equals("isGuest")||userName.equals("isUser")) {
            maikFade(sigStackPane, 1, 0);
            sigStackPane.setDisable(true);
            maikFade(userStack, 0, 1);
            userStack.setDisable(false);
        }else
        {
            printErrorSignInMassage("incorrect");
        }

    }

    public void exitClic ()  {


        maikFade(sigStackPane,0,1);
        sigStackPane.setDisable(false);
        maikFade(userStack,1,0);
        userStack.setDisable(true);
        printErrorSignInMassage("");


    }

    public void printErrorSignInMassage(String massage){
        tetxErrorSignIn.setText(massage);
    }
}
