package DataElements.extendet;

import DataElements.Data;

public class Doc extends Data {
    @Override
    public String toString() {
        return "Doc{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
