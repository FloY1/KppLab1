package DataElements.extendet;

import DataElements.DataFile;
/** Класс описывающий общее поведение Музыки
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class Music extends DataFile {
    @Override
    public String toString() {
        return "Music{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
