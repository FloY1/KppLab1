package DataElements.extendet;

import DataElements.Data;

public class Book extends Data {
    @Override
    public String toString() {
        return "Book{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';

    }
}
