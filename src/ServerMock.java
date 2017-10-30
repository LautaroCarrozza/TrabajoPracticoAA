import java.util.ArrayList;
import java.util.List;

public class ServerMock implements ServerInterface{

    private List<Cliente> clientes = new ArrayList<>();
    private List<Vuelo> vuelos = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    public void setUpTest(){

        Cliente clienteA = new Cliente(1, "a", 1);
        Cliente clienteB = new Cliente(2, "b", 2);

        addCliente(clienteA);
        addCliente(clienteB);

        Aeropuerto aeropuertoA = new Aeropuerto("aaa", "aaa", "aaa");
        Aeropuerto aeropuertoB = new Aeropuerto("bbb", "bbb", "bbb");

        TipoDeAvion tipoDeAvionA = new TipoDeAvion(10, 2, 5, 2, 2, 2, "Australis-Airlines 01");

        Avion avionA = new Avion("1", tipoDeAvionA);

        Vuelo vueloA = new Vuelo(aeropuertoA, aeropuertoB, 1, 1, 2017, avionA, 1);
        addVuelo(vueloA);

    }

    public void addVuelo(Vuelo vuelo){vuelos.add(vuelo);}

    public void validarSesion(int numero) {
        for (Cliente c : clientes) {
            if(c.getNumeroDeCliente() == numero){
                return;
            }
        }
        throw new RuntimeException("Numero de cliente invalido");
    }

    public String printReservas(int numeroDeCliente){

        String result =  "";

        for (Cliente c: clientes ) {
            if(c.getNumeroDeCliente() == numeroDeCliente ){
                Cliente cliente = c;

                if(cliente.getReservas().size() == 0){
                    result =  "El cliente no cuenta con reservas";
                    break;
                }

                System.out.println(c.getNombre());
                for (Reserva r :cliente.getReservas()) {
                    result += r;
                }
            }
        }
        return result;
    }

    public  void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Vuelo> buscarVuelos(int dia, int mes, int ano, String lugarSalida, String lugarLlegada, int cantidadPersonas, String categoria){

        List<Vuelo> posiblesVuelos = new ArrayList<>();

        for (Vuelo vuelo: vuelos) {
            if (vuelo.getFechaSalida().getDayOfMonth() == dia && (vuelo.getFechaSalida().getMonthValue() == mes && vuelo.getFechaSalida().getYear() == ano && vuelo.getUbicacionSalida().equals(lugarSalida) && vuelo.getUbicacionLlegada().equals(lugarLlegada)
                    && vuelo.cantidadAsientosDisponibles(categoria) >= cantidadPersonas)){
                posiblesVuelos.add(vuelo);
            }
        }
        if (posiblesVuelos.size() == 0){throw new RuntimeException("No existen vuelos");}
        return posiblesVuelos;
    }

    public void comprarAsiento(int codigoVuelo, int codigoCliente, List<Asiento> asientos, int cantidadDePersnas, String categoria) {
        Vuelo vuelo = getVuelo(codigoVuelo);
        for (Asiento asiento:asientos) {
            if (!vuelo.getOcupacion(asiento)){
                vuelo.ocupar(asiento);
            }
            else{throw new RuntimeException("El asiento esta ocupado");}
        }

        Reserva nuevaReserva = new Reserva(getCliente(codigoCliente), vuelo, cantidadDePersnas, categoria, asientos );
        reservas.add(nuevaReserva);
        getCliente(codigoCliente).addReserva(nuevaReserva);
    }

    public Vuelo getVuelo(int codigoDeVuelo) {
        for (Vuelo v:vuelos) {
            if (v.getCodigoDeVuelo()== (codigoDeVuelo)){
                return v;
            }
        }
        throw new RuntimeException("No existen vuelos con ese codigo");
    }

    public  Cliente getCliente(int numeroDeCliente){
        for (Cliente c :
                clientes) {
            if(c.getNumeroDeCliente() == numeroDeCliente){return c;}
        }
        throw new RuntimeException("No existe el cliente");
    }
}
