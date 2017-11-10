import java.time.LocalDate;
import java.util.List;

public interface ServerInterface {

    void setUp();
    void validarSesionCliente(int numero);
    List<Reserva> getReservas(int numeroDeCliente);
    void addCliente(int dni, String nombre, int numeroDeCliente);
    List<Vuelo> buscarVuelos(int dia, int mes, int ano, String lugarSalida, String lugarLlegada, int cantidadPersonas);
    void comprarAsiento(int codigoVuelo, int codigoCliente, int fila, String columna, int cantidadDePersonas, String nombre, int dni);
    Vuelo getVuelo(int codigoDeVuelo);
    void addEmpleado(int dni, String nombre, int codigoEmpleado, String nombreArea);
    void validarSesionEmpleado(int currentSesion);
    TipoDeAvion getTipoDeAvion(String tipoDeAvion);
    void addAvion(String codigo, String tipoDeAvionStr);
    void addTipoDeAvion(int cantidadFilasEconomy, int cantidadAsientosPorFilaDeEconomy, int cantidadFilasBussiness, int cantidadAsientosPorFilaDeBussiness, int cantidadFilasFirst, int cantidadAsientosPorFilaDeFirst,int cantidadPersonalAbordo, String nombre);
    void addAeropuerto(String codigoDeAeropuerto, String ubicacion, String nombre);
    void addVuelo(String aeropuertoDeSalida, String aeropuertoDeLlegada, int dia, int mes, int ano, int hours, int minutes,int minutesDuration, String plane, int flightCode, int repeticiones, int precioEconomy, int precioBussiness, int precioFirst);
    Empleado getEmployee(int currentSesion);
    Cliente getCliente(int numeroCliente);
    void validarCliente(int numeroCliente);
    void validarLugarDePartida(String lugarDePartida);
    void validarLugarDeLlegada(String lugarDeLlegada);
    void validarSesionEmpleadoAbordo(int currentSesion);
    PersonalAbordo getPersonalAbordo(int numeroDeEmpleado);
    void addPersonalAbordo(int dni, String nombre, String cargo, int numeroDeEmpleado);
    void restart();
    Aeropuerto getAeropuerto(String aeropuerto);
    Avion getAvion(String avion);
    void validarVueloPorCantidadDePersonal(Vuelo vuelo);
    List<PersonalAbordo> getPersonalAbordoLista();
    void validarDisponibilidadTripulacion(LocalDate localDate, int cantidadDePersonal);
    void addTarifa(String first, int codigoDeVuelo, int precioFirst);
    int getPreciodeTarifa(int codigo, String first);
    AreaAdministrativa getAreaAdministrativa(String nombreArea);
    void addAreaAdministrativa(String nombre, boolean habilitacionVenta);
    List<AreaAdministrativa> getAreasAdministrativas();
    void addTripulacion(Vuelo vuelo);
    void addPersonalAbordoenVuelo(Vuelo vuelo);
    void addPiloto(Vuelo vuelo);
    List<Empleado> getEmpleados();
    List<Cliente> getClientes();
    List<Avion> getAviones();
    List<Aeropuerto> getAeropuertos();
    List<Vuelo> getVuelos();
    void sort(List<Vuelo> asientosDisponibles, String criteri);
}