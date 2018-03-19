package DataElements;


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DataFile {
    protected String fileName;
    protected String url;
    protected long id;

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

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public DataFile() {
       }
}
