package users;

import java.io.File;
/** Абстрактный класс описывающий поведение пользователя
 * @author  artem.smolonskiy
 * @version 1.0
 */
public abstract class Users  {


    /**
     * Способен ли добавить
     * @param file добавляемый файл
     * @return false
     */
    public  boolean canBeAdded(File file) {
        return false;
    }

    /**
     * Способен ли удалить
     * @return false
     */
    public boolean canBeDeleted(){
        return false;

    }

    /**
     * Метод возвращаюший щначение поля
     * @return 0
     */
    public int  getLimit(){
        return 0;
    }


    @Override
    public String toString() {
        return "null";

    }
}
