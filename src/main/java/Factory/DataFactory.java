package Factory;

import DataElements.*;
import DataElements.extendet.Book;
import DataElements.extendet.Doc;
import DataElements.extendet.Film;
import DataElements.extendet.Music;
import org.apache.log4j.Logger;
/** Класс фабрика для класса DataFactory
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class DataFactory {
    /**
     * Логер
     */
    private static final Logger loger = Logger.getLogger(DataFactory.class);

    /**
     * Создаёт объект в соответствии с введённым параметром+
     * @param s имя класса
     * @return объект класса
     */
    public static DataFile getData(String s) {
        DataFile dataFile = null;
        try {
            switch (s) {
                case "Book":
                    dataFile = new Book();
                    break;
                case "Doc":
                    dataFile = new Doc();
                    break;
                case "Film":
                    dataFile = new Film();
                    break;
                case "Music":
                    dataFile = new Music();
                    break;
                default:
                    if (!s.equals("Date"))
                        loger.warn("No found class " + s);
                    dataFile = new DataFile();
            }
        } catch (Exception e) {
            loger.warn(e);
        } finally {
            loger.debug("Create new " + dataFile.getClass());
        }
        return dataFile;


    }
}

