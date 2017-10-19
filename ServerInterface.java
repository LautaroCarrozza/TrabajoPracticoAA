import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ServerInterface {

    void setUpTest();
    void validarSesion(int numero);
    String printReservas(int numeroDeCliente);
    void addCliente(Cliente cliente);
    String buscarVuelo(int dia, int mes, int ano, String lugarSalida, String lugarLlegada, int cantidadPersonas, String categoria);
    void comprarPasaje(int codigoVuelo, int codigoCliente, String asiento);

    Vuelo getVuelo(String codigoDeVuelo);
}
