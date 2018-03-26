package DataElements;


import java.awt.*;
import java.io.File;

/** Класс описывающий общее поведение файлов
 * @author  artem.smolonskiy
 * @version 1.0
 */
public class DataFile {
    /**Свойство - имя файла */
    protected String fileName;

    /**Свойство - путь*/
    protected String url;

    /**Свойство - id */
    protected long id;


    /**Вызывает приложение по умолчянию OS для файла*/
    public boolean show() {
        File file = new File(url);
        new Thread(()->{
            try {
                Desktop.getDesktop().open(file);
                Desktop.getDesktop().open(file);
            }catch (Exception e1){

            }
        }).start();


        return true;
    }

    /** Возврвщает {@link DataFile#id}
     * @return {@link DataFile#id}
     * */
    public long getId() {
        return id;
    }

    /** Устанавливает {@link DataFile#id}
     * @param id новое id
     */
    public void setId(int id) {
        this.id = id;
    }
    /** Возврвщает {@link DataFile#fileName}
     * @return {@link DataFile#id}
     * */
    public String getFileName() {
        return fileName;
    }


    /** Устанавливает {@link DataFile#fileName}
     * @param fileName новое имя файла
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /** Возврвщает {@link DataFile#url}
     * @return {@link DataFile#url}
     * */
    public String getUrl() {
        return url;
    }


    /** Устанавливает {@link DataFile#url}
     * @param url новое имя {@link DataFile#url}
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /** Создаёт объект с заданными щначениями
     * @param fileName имя файла
     * @param url путь к файлу
     */
    public DataFile(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }

    @Override
    public String toString() {
        return "DataFile{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }

    /**Создаёт пустой объект*/
    public DataFile() {
       }
}
