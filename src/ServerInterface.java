import java.util.List;

public interface ServerInterface {

    void setUpTest();
    void validarSesionCliente(int numero);
    List<Reserva> getReservas(int numeroDeCliente);
    void addCliente(Cliente cliente);
    List<Vuelo> buscarVuelos(int dia, int mes, int ano, String lugarSalida, String lugarLlegada, int cantidadPersonas);
    void comprarAsiento(int codigoVuelo, int codigoCliente, Asiento asiento, int cantidadDePersonas);
    Vuelo getVuelo(int codigoDeVuelo);
    void addEmpleado(Empleado empleado);
    void guardarReserva(int codigoCliente, Vuelo vuelo);
    void validarSesionEmpleado(int currentSesion);
    TipoDeAvion getTipoDeAvion(String tipoDeAvion);
    void addAvion(String codigo, String tipoDeAvionStr);
    void addTipoDeAvion(int cantidadFilasEconomy, int cantidadAsientosPorFilaDeEconomy, int cantidadFilasBussiness, int cantidadAsientosPorFilaDeBussiness, int cantidadFilasFirst, int cantidadAsientosPorFilaDeFirst, String nombre);
    void addAeropuerto(String codigoDeAeropuerto, String ubicacion, String nombre);
    void addVuelo(String aeropuertoDeSalida, String aeropuertoDeLlegada, int dia, int mes, int ano, int hours, int minutes, String plane, int flightCode);
    Empleado getEmployee(int currentSesion);
    Cliente getCliente(int numeroCliente);
    void validarCliente(int numeroCliente);
    void validarLugarDePartida(String lugarDePartida);
    void validarLugarDeLlegada(String lugarDeLlegada);
}



