package Factory;

import DataElements.extendet.Book;
import DataElements.extendet.Doc;
import DataElements.extendet.Film;
import DataElements.extendet.Music;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataFactoryTest {

    @Test
    public void getData() {

        assertEquals(DataFactory.getData("Film").getClass(),new Film().getClass());
        assertEquals(DataFactory.getData("Doc").getClass(),new Doc().getClass());
        assertEquals(DataFactory.getData("Music").getClass(),new Music().getClass());
        assertEquals(DataFactory.getData("Book").getClass(),new Book().getClass());

    }
}