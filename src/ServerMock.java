import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServerMock implements ServerInterface{

    private List<Cliente> clientes = new ArrayList<>();
    private List<Vuelo> vuelos = new ArrayList<>();

    public void setUpTest(){
        Aeropuerto AEP = new Aeropuerto("AEP", "Buenos Aires", "Aeroparque");
        Aeropuerto MTV = new Aeropuerto("MTV", "Montevideo", "A.I de Montevideo");
        TipoDeAvion tipoDeAvion = new TipoDeAvion(5,2 , "737");
        Avion tango01 = new Avion("001", tipoDeAvion);
        Vuelo vuelo = new Vuelo(AEP, MTV, 25, 10, 2017, tango01, 101);
        Cliente cliente = new Cliente(123, "Lauti", 123);
        Cliente cliente2 = new Cliente(124, "Lauti", 124);
        Reserva reserva = new Reserva(cliente, vuelo);
        cliente.addReserva(reserva);
        Aeropuerto AAA = new Aeropuerto("MTV", "Montevideo", "A.I. Montevideo");
        Aeropuerto BBB = new Aeropuerto("JFK", "Nueva York", "J.F Kennedy");
        Vuelo vuelo2 = new Vuelo(AAA, BBB, 11, 10, 2017, tango01, 102);
        Reserva reserva2 = new Reserva(cliente, vuelo2);
        cliente.addReserva(reserva2);
        vuelos.add(vuelo);
        vuelos.add(vuelo2);
        addCliente(cliente);
        addCliente(cliente2);
    }

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
        Calendar fechaSalida = Calendar.getInstance();
        fechaSalida.set(ano, mes -1, dia);
        List<Vuelo> posiblesVuelos = new ArrayList<>();

        for (Vuelo vuelo: vuelos) {
            if (vuelo.getFechaSalida().equals(fechaSalida) && vuelo.getUbicacionSalida().equals(lugarSalida) && vuelo.getUbicacionLlegada().equals(lugarLlegada)
                    && vuelo.cantidadAsientosDisponibles(categoria) >= cantidadPersonas){
                posiblesVuelos.add(vuelo);
            }
        }
        if (posiblesVuelos.size() == 0){throw new RuntimeException("No existen vuelos");}
        return posiblesVuelos;
    }

    public void comprarPasaje(int codigoVuelo, int codigoCliente, int fila, char columna) {
        Vuelo vuelo = getVuelo(codigoVuelo);
        Asiento asiento = vuelo.getAsiento(fila, columna);
        if (!vuelo.getOcupacion(asiento)){vuelo.ocupar(asiento);}
        else{throw new RuntimeException("El asiento esta ocupado");}
    }

    public Vuelo getVuelo(int codigoDeVuelo) {
        for (Vuelo v:vuelos) {
            if (v.getCodigoDeVuelo()== (codigoDeVuelo)){
                return v;
            }
        }
        throw new RuntimeException("No existen vuelos con ese codigo");
    }
}
