package DataElements.extendet;

import DataElements.DataFile;

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
