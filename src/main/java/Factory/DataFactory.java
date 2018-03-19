package Factory;

import DataElements.*;
import DataElements.extendet.Book;
import DataElements.extendet.Doc;
import DataElements.extendet.Film;
import DataElements.extendet.Music;
import org.apache.log4j.Logger;

public class DataFactory {
    private static final Logger loger = Logger.getLogger(DataFactory.class);

    public static DataFile geData(String s) {
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
            loger.info("Create new " + dataFile.getClass());
        }
        return dataFile;


    }
}

