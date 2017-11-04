import java.util.List;

public interface ServerInterface {

    void setUpTest();
    void validarSesionCliente(int numero);
    String printReservas(int numeroDeCliente);
    void addCliente(Cliente cliente);
    List<Vuelo> buscarVuelos(int dia, int mes, int ano, String lugarSalida, String lugarLlegada, int cantidadPersonas, String categoria);
    void comprarAsiento(int codigoVuelo, int codigoCliente, Asiento asiento, int cantidadDePersonas, String categoria);
    Vuelo getVuelo(int codigoDeVuelo);
    void addEmpleado(Empleado empleado);
    void guardarReserva(int codigoCliente, Vuelo vuelo);
    void validarSesionEmpleado(int currentSesion);
    TipoDeAvion getTipoDeAvion(String tipoDeAvion);
    void addAvion(String codigo, String tipoDeAvionStr);
    void addTipoDeAvion(int cantidadFilasEconomy, int cantidadAsientosPorFilaDeEconomy, int cantidadFilasBussiness, int cantidadAsientosPorFilaDeBussiness, int cantidadFilasFirst, int cantidadAsientosPorFilaDeFirst, String nombre);
    void addAeropuerto(String codigoDeAeropuerto, String ubicacion, String nombre);
    void addVuelo(String aeropuertoDeSalida, String aeropuertoDeLlegada, int dia, int mes, int ano, int hours, int minutes, String plane, int flightCode);
}



