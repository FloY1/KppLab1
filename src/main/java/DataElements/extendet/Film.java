package DataElements.extendet;

import DataElements.DataFile;
/** Класс описывающий общее поведение Фильмов
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class Film extends DataFile {

    @Override
    public String toString() {
        return "Film{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
