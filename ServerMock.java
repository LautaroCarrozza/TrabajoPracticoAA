import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerMock implements ServerInterface{

    List<Cliente> clientes = new ArrayList<>();
    List<Vuelo> vuelos = new ArrayList<>();

     public void setUpTest(){
        Aeropuerto AEP = new Aeropuerto("AEP", "Buenos Aires", "Aeroparque");
        Aeropuerto MTV = new Aeropuerto("MTV", "Montevideo", "A.I de Montevideo");
        TipoDeAvion tipoDeAvion = new TipoDeAvion(5, 5, "737");
        Avion tango01 = new Avion("001", tipoDeAvion);
        Vuelo vuelo = new Vuelo(AEP, MTV, 10, 10, 2017, tango01, "001");
        Cliente cliente = new Cliente(123, "Lauti", 123);
        Cliente cliente2 = new Cliente(124, "Lauti", 124);
        Reserva reserva = new Reserva(cliente, vuelo);
        cliente.addReserva(reserva);
        Aeropuerto AAA = new Aeropuerto("MTV", "Montevideo", "A.I. Montevideo");
        Aeropuerto BBB = new Aeropuerto("JFK", "Nueva York", "J.F Kennedy");
        Vuelo vuelo2 = new Vuelo(AAA, BBB, 11, 10, 2017, tango01, "002");
        Reserva reserva2 = new Reserva(cliente, vuelo2);
        cliente.addReserva(reserva2);
        vuelos.add(vuelo);
        vuelos.add(vuelo2);
    }

     public void validarSesion(int numero) {
        for (Cliente c : clientes) {
            if(c.numeroDeCliente == numero){
                return;
            }
        }
        throw new RuntimeException("Numero de cliente invalido");


        // TODO: 9/10/17
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

    public String buscarVuelo(int dia, int mes, int ano, String lugarSalida, String lugarLlegada, int cantidadPersonas, String categoria) {
        Date fechaSalida = new Date(dia, mes, ano);
        // TODO: 18/10/2017 change to actual implemetantion
        List<Vuelo> posiblesVuelos = new ArrayList<>();
        String result;
        for (Vuelo v:vuelos) {
            if(v.getProximaSalida().equals(fechaSalida) && v.aeropuertoSalida.ubicacion.equals(lugarSalida) && v.aeropuertoLlegada.ubicacion.equals(lugarLlegada) && v.cantidadAsientosDisponibles(categoria) >= cantidadPersonas){
                posiblesVuelos.add(v);
            }
        }
        result = "Posibles vuelos: \n ";
        int count = 1;
        for (Vuelo v :posiblesVuelos) {
            result +=  count + "-  " + v.toString() + "\n";
            count ++;
        }
        return result;
    }

    public void comprarPasaje(int codigoVuelo, int codigoCliente, String asiento) {
        for (Vuelo v: vuelos) {
            if(v.codigoDeVuelo.equals(codigoVuelo)){
                int fila = asiento.charAt(0) * 10 + asiento.charAt(1);
                char columna = asiento.charAt(2);
                v.ocupar(fila, columna);
            }
        }
    }

    @Override
    public Vuelo getVuelo(String codigoDeVuelo) {
        for (Vuelo v:vuelos) {
            if (v.codigoDeVuelo.equals(codigoDeVuelo)){
                return v;
            }
        }
        throw new RuntimeException("No existen vuelos con ese codigo");
    }
}
