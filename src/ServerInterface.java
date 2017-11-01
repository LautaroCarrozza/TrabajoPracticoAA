import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ServerInterface {

    void setUpTest();
    void validarSesion(int numero);
    String printReservas(int numeroDeCliente);
    void addCliente(Cliente cliente);
    List<Vuelo> buscarVuelos(int dia, int mes, int ano, String lugarSalida, String lugarLlegada, int cantidadPersonas, String categoria);
    void comprarAsiento(int codigoVuelo, int codigoCliente, Asiento asiento, int cantidadDePersonas, String categoria);
    Vuelo getVuelo(int codigoDeVuelo);
}
