package DataElements.extendet;

import DataElements.DataFile;

public class Film extends DataFile {

    @Override
    public String toString() {
        return "Film{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
