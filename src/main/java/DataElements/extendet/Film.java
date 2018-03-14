package DataElements.extendet;

import DataElements.Data;

public class Film extends Data {

    @Override
    public String toString() {
        return "Film{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
