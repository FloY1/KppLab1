package DataElements;

public class Data {
    protected String fileName;
    protected String url;
    protected long id;

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

    public Data(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Data{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }

    public Data() {
       }
}
