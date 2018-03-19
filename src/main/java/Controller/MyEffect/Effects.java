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


    private static  Effects effects;
    private  DropShadow dropShadow;


    private Effects() {
        dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.color(0.1, 0, 0.1));
    }
    public static Effects getEffectsForMainWindow(){
        if(effects==null)
            effects = new Effects();
        return effects;
    }

    private void  maikFade(Node node, int fromV, int toV){
        FadeTransition fadeTransition  = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(fromV);
        fadeTransition.setToValue(toV);
        fadeTransition.play();
    }
    public void twoElemeintFade(Node disableNode,Pane notDisableNode){
        maikFade(notDisableNode,0,1);
        notDisableNode.setDisable(false);

        maikFade(disableNode,1,0);
        disableNode.setDisable(true);
    }
    public void userLogIn(String name, Users user,Button add,Button delete,Text massge) {
        delete.setDisable(!user.iCanDelete());
        add.setDisable(!user.iCanAdd(new File("")));
        massge.setText(name);

    }
    public  void enteredButton(Event actionEvent){

        Object source = actionEvent.getSource();
        if(!(source instanceof Node))
            return;
        ((Node)source).setEffect(dropShadow);
    }


    public void exitedButton(Event actionEvent){
        Object sourse = actionEvent.getSource();
        if(!(sourse instanceof Node))
            return;
        ((Node)sourse).setEffect(null);


    }
    public void enty(String name,Text textName,ImageView img,Pane disablePane,Pane notDisablePane )  {
        if(name.equals("isAdmin")) {
            textName.setText("Admin");
            img.setImage(new Image("img/admin.png"));
        }
        if(name.equals("isUser")){
            textName.setText("User");
            img.setImage(new Image("img/user.png"));
        }
        if(name.equals("isGuest")){
            textName.setText("Guest");
            img.setImage(new Image("img/guest.png"));
        }
        if(name.equals("isAdmin")|| name.equals("isGuest")|| name.equals("isUser")) {
            twoElemeintFade(disablePane,notDisablePane);
        }

    }



    public void printErrorSignInMassage(String massage,Text textEror){
        textEror.setText(massage);
    }
}
