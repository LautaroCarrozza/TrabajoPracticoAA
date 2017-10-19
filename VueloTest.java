//import org.junit.Test;
//import sun.util.calendar.LocalGregorianCalendar;
//
//import java.time.MonthDay;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import static org.junit.Assert.*;
//
//public class VueloTest {
//
//    @Test
//    public void test01(){
//        Aeropuerto EZE = new Aeropuerto("EZE", "Buenos Aires", "Ezeiza");
//        Aeropuerto AEP = new Aeropuerto("AEP", "Buenos Aires", "Aeroparque");
//        Avion avion = new Avion();
//        Vuelo vuelo = new Vuelo(EZE, AEP, 10, 10, 2017, avion, "1234");
//
//        assertEquals( 17 , vuelo.getProximaSalida().getDate());
//    }
//}