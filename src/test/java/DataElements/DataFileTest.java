package DataElements;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class DataFileTest {
    private DataFile dataFile = new DataFile("fil","/");
    @Before
    public void init(){
        dataFile.setId(12);
    }

    @Test
    public void getId() {
        long i = 12;
        assertEquals(dataFile.getId(),i);
    }

    @Test
    public void getFileName() {
        assertEquals(dataFile.getFileName(),"fil");
    }

    @Test
    public void getUrl() {
        assertEquals(dataFile.getUrl(),"/");
    }

}