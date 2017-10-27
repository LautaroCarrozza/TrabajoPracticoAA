import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class VueloTest {

    @Test
    public void test01(){
        ServerInterface server = new ServerMock();
        server.setUpTest();
        LocalDate testDay = LocalDate.of(2017, 1, 1);
        Vuelo vuelo = server.getVuelo(1);
        assertEquals(testDay.getDayOfMonth(), vuelo.getFechaSalida().getDayOfMonth());
    }
}