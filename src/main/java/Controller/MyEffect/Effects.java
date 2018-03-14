package Controller.MyEffect;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Effects {

   private StackPane sigStackPane;

    private StackPane userStack;

    private Pane menuPane;

    private Pane listPane;

    private DropShadow dropShadow;

    public Effects(StackPane sigStackPane, StackPane userStack, Pane menuPane, Pane listPane) {
        this.sigStackPane = sigStackPane;
        this.userStack = userStack;
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
    public void catalogButtonClick() {
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

        maikFade(sigStackPane,1,0);
        sigStackPane.setDisable(true);
        maikFade(userStack,0,1);
        userStack.setDisable(false);

    }

    public void exitClic ()  {


        maikFade(sigStackPane,0,1);
        sigStackPane.setDisable(false);
        maikFade(userStack,1,0);
        userStack.setDisable(true);


    }
}
