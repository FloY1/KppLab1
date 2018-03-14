package Factory;

import DataElements.*;
import DataElements.extendet.Book;
import DataElements.extendet.Doc;
import DataElements.extendet.Film;
import DataElements.extendet.Music;
import org.apache.log4j.Logger;

public class DataFactory {
    private static final Logger loger = Logger.getLogger(DataFactory.class);

    public static Data geData(String s) {
        Data data = null;
        try {
            switch (s) {
                case "Book":
                    data = new Book();
                    break;
                case "Doc":
                    data = new Doc();
                    break;
                case "Film":
                    data = new Film();
                    break;
                case "Music":
                    data = new Music();
                    break;
                default:
                    if (!s.equals("Date"))
                        loger.warn("No found class " + s);
                    data = new Data();
            }
        } catch (Exception e) {
            loger.warn(e);
        } finally {
            loger.info("Create new " + data.getClass());
        }
        return data;


    }
}

