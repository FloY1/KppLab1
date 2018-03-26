package DataElements.extendet;

import DataElements.DataFile;
/** Класс описывающий общее поведение книг
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class Book extends DataFile {
    @Override
    public String toString() {
        return "Book{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';

    }
}
