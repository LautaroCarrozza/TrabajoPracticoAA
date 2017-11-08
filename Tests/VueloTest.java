import org.junit.Test;

public class VueloTest {

    @Test
    public void test01(){
        ServerInterface server = new ServerMock();
        server.setUp();
        System.out.println(server.getVuelo(1));
    }
}