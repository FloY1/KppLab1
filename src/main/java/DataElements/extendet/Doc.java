package DataElements.extendet;

import DataElements.DataFile;

public class Doc extends DataFile {
    @Override
    public String toString() {
        return "Doc{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
