import org.junit.Test;

import static org.junit.Assert.*;

public class SaverTest {

    @Test
    public void test01(){

        Saver clientSaver = new Saver("saverTest");
        clientSaver.restart();
        Cliente cliente = new Cliente(41710483, "Matias Midoosky", 1);
        Cliente cliente2 = new Cliente(41710483, "Matias Midoosky", 1);
        clientSaver.save(cliente);
        clientSaver.save(cliente2);
        assertEquals(1, clientSaver.get().size());

    }
}