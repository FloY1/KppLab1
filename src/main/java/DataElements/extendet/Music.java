package DataElements.extendet;

import DataElements.DataFile;

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
