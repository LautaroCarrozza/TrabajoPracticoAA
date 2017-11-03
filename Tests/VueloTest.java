import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class VueloTest {

    @Test
    public void test01(){
        ServerInterface server = new ServerMock();
        server.setUpTest();
        System.out.println(server.getVuelo(1));
    }
}