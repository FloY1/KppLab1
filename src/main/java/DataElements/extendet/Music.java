package DataElements.extendet;

import DataElements.Data;

public class Music extends Data {
    @Override
    public String toString() {
        return "Music{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
