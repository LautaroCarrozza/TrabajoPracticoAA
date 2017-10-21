import org.junit.Test;
import sun.util.calendar.LocalGregorianCalendar;

import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class VueloTest {

    @Test
    public void test01(){
        ServerInterface server = new ServerMock();
        server.setUpTest();
        Calendar testDay = Calendar.getInstance();
        testDay.set(2017, 11, 25);
        Vuelo vuelo = server.getVuelo(101);
        assertEquals(testDay.get(Calendar.DAY_OF_MONTH), vuelo.getFechaSalida().get(Calendar.DAY_OF_MONTH));
    }
}