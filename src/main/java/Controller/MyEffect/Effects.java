package Controller.MyEffect;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import users.Users;

import java.io.File;

/** Класс реализует singleton, служит для наложения эфектов на элеметны интерфейса
 * @author  artem.smolonskiy
 * @version 1.0
 */

public class Effects {

    private static  Effects effects;


    /**Свойство - тень*/
    private  DropShadow dropShadow;


    /**Создаёт новый объект устанавливая стандартное значение для тени*/
    private Effects() {
        dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.color(0.1, 0, 0.1));
    }

    /**Метод возвращает значения поля {@link Effects#effects}
     * @return возвращает обьек для работы с эектами
     */
    public static Effects getEffectsForMainWindow(){
        if(effects==null)
            effects = new Effects();
        return effects;
    }

    /** Метод измененяет прозрачность элемента
     * @param node  элемент
     * @param fromV  начальное значенин
     * @param toV  конечное значение
     */
    private void  maikFade(Node node, int fromV, int toV){
        FadeTransition fadeTransition  = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(fromV);
        fadeTransition.setToValue(toV);
        fadeTransition.play();
    }


    /** Метод изменяет значение прозрачности и Disable для 2 элементов , 1 - отображает , 2 - скрывает
     * @param disableNode  отображаемый
     * @param notDisableNode  скрываемый
     */
    public void twoElemeintFade(Node disableNode,Pane notDisableNode){
        maikFade(notDisableNode,0,1);
        notDisableNode.setDisable(false);

        maikFade(disableNode,1,0);
        disableNode.setDisable(true);
    }

    /** Метод накладывает эффекты на кнопки в зависимости от прав пользователя
     * @param info  текст выводимый в massage
     * @param user  пользователь
     * @param add  кнопка добавить
     * @param delete  кнопка удалить
     * @param massge  поле куда выводиться текст
     */
    public void userLogIn(String info, Users user,Button add,Button delete,Text massge) {
        delete.setDisable(!user.canBeDeleted());
        add.setDisable(!user.canBeAdded(new File("")));
        massge.setText(info);

    }


    /**Метод накладывает эффект на элемент при курсора наведении на элемент
     * @param actionEvent  произошедшее событие
     */
    public  void enteredButton(Event actionEvent){

        Object source = actionEvent.getSource();
        if(!(source instanceof Node))
            return;
        ((Node)source).setEffect(dropShadow);
    }


    /** Метод накладывает эффект на элемент при отведении курсора с элемента
     * @param actionEvent  произошедшее событие
     */
    public void exitedButton(Event actionEvent){
        Object sourse = actionEvent.getSource();
        if(!(sourse instanceof Node))
            return;
        ((Node)sourse).setEffect(null);


    }


    /** Эфект входа пользователя , скрывает панель входа , отображает панель пользователя заполняя её информацией
     * @param name  имя пользователя
     * @param textName  поле  Имени
     * @param img  поле Картинки
     * @param disablePane  панель входа
     * @param notDisablePane  панель пользователя
     */
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


    /** Выводит сообщение в текстовое поле
     * @param massage сообщение
     * @param textEror текстовое поле
     */
    public void printErrorSignInMassage(String massage,Text textEror){
        textEror.setText(massage);
    }
}
