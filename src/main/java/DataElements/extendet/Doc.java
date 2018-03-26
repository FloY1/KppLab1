package DataElements.extendet;

import DataElements.DataFile;
/** Класс описывающий общее поведение Документов
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class Doc extends DataFile {
    @Override
    public String toString() {
        return "Doc{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
