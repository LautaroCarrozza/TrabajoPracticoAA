import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerMock implements ServerInterface{

     List<Cliente> clientes = new ArrayList<>();
     List<Empleado> empleados = new ArrayList<>();
     List<Vuelo> vuelos = new ArrayList<>();
     List<Reserva> reservas = new ArrayList<>();
     List<Pasaje> pasajes = new ArrayList<>();
     List<TipoDeAvion> tiposDeAvion = new ArrayList<>();
     List<Avion> aviones = new ArrayList<>();
     List<Aeropuerto> aeropuertos= new ArrayList<>();
     Object[] lists = {clientes,empleados, vuelos, reservas, pasajes, tiposDeAvion, aviones, aeropuertos};

    Saver clientesSaver ;
    Saver empleadosSaver ;
    Saver vuelosSaver ;
    Saver reservasSaver ;
    Saver pasajesSaver ;
    Saver tiposDeAvionSaver;
    Saver avionesSaver ;
    Saver aeropuertosSaver ;


    public ServerMock() {

        aeropuertosSaver = new Saver("Aeropuertos");
        aeropuertos = Aeropuerto.build(aeropuertosSaver.get());





    }

    public void setUpTest(){

        Cliente clienteA = new Cliente(1, "a", 1);
        Cliente clienteB = new Cliente(2, "b", 2);

        addCliente(clienteA);
        addCliente(clienteB);

        Empleado empleado1 = new Empleado(2020130044, "Fernando Miodosky", 1, true);
        addEmpleado(empleado1);

        Aeropuerto aeropuertoA = new Aeropuerto("aaa", "aaa", "aaa");
        Aeropuerto aeropuertoB = new Aeropuerto("bbb", "bbb", "bbb");
        Aeropuerto aeropuertoC = new Aeropuerto("ccc", "ccc", "ccc");

        TipoDeAvion tipoDeAvionA = new TipoDeAvion(10, 2, 5, 2, 2, 2, "Australis-Airlines 01");
        TipoDeAvion tipoDeAvionB = new TipoDeAvion(10, 2, 5, 2, 2, 2, "Australis-Airlines 02");

        Avion avionA = new Avion("1", tipoDeAvionA);
        Avion avionB = new Avion("2", tipoDeAvionB);

        Vuelo vuelo = new Vuelo(aeropuertoA, aeropuertoB, 1, 1, 2018, 20, 15, avionA, 1);
        vuelos.add(vuelo);

    }

    public void validarSesionCliente(int numero) {
        for (Cliente c : clientes) {
            if(c.getNumeroDeCliente() == numero){
                return;
            }
        }
        throw new RuntimeException("Numero de cliente invalido");
    }

    public void addEmpleado(Empleado empleado){
        empleados.add(empleado);
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Vuelo> buscarVuelos(int dia, int mes, int ano, String lugarSalida, String lugarLlegada, int cantidadPersonas){

        List<Vuelo> posiblesVuelos = new ArrayList<>();

        for (Vuelo vuelo: vuelos) {
            if (vuelo.getFechaSalida().getDayOfMonth() == dia && (vuelo.getFechaSalida().getMonthValue() == mes && vuelo.getFechaSalida().getYear() == ano && vuelo.getUbicacionSalida().equals(lugarSalida) && vuelo.getUbicacionLlegada().equals(lugarLlegada)
                    && vuelo.cantidadAsientosDisponibles() >= cantidadPersonas)){
                posiblesVuelos.add(vuelo);
            }
        }
        if (posiblesVuelos.size() == 0){throw new RuntimeException("No existen vuelos");}
        return posiblesVuelos;
    }

    public void comprarAsiento(int codigoVuelo, int codigoCliente, Asiento asiento, int cantidadDePersnas, String categoria) {
        Vuelo vuelo = getVuelo(codigoVuelo);

            if (!vuelo.getOcupacion(asiento)){
                vuelo.ocupar(asiento);
                Pasaje pasaje = new Pasaje(vuelo, asiento, getCliente(codigoCliente));
                pasajes.add(pasaje);
            }
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

    public void guardarReserva(int codigoCliente, Vuelo vuelo) {
        List<Pasaje> pasajesReservados = new ArrayList<>();
        for (Pasaje pasaje:pasajes) {
            if (pasaje.getCliente().getNumeroDeCliente() == codigoCliente && pasaje.getVuelo().equals(vuelo)){
                pasajesReservados.add(pasaje);
            }
        }
        getCliente(codigoCliente).guardarReserva(pasajesReservados, vuelo);
    }

    public void validarSesionEmpleado(int currentSesion) {
        for (Empleado empleado: empleados ) {
            if (empleado.getCodigoEmpleado() == currentSesion)return;
        }
        throw new RuntimeException("Codigo de empleado invalido");
    }

    public TipoDeAvion getTipoDeAvion(String tipoDeAvion) {
        for (TipoDeAvion t:tiposDeAvion
             ) {
            if (t.getNombre().equals(tipoDeAvion)){return t;}
        }
        throw new RuntimeException("No existe el tipo de avion");
    }

    public void addAvion(String codigo, String tipoDeAvionStr) {
        TipoDeAvion tipoDeAvion = getTipoDeAvion(tipoDeAvionStr);
        Avion avion = new Avion(codigo, tipoDeAvion);
        aviones.add(avion);
    }

    public void addTipoDeAvion(int cantidadFilasEconomy, int cantidadAsientosPorFilaDeEconomy, int cantidadFilasBussiness, int cantidadAsientosPorFilaDeBussiness, int cantidadFilasFirst, int cantidadAsientosPorFilaDeFirst, String nombre) {
        TipoDeAvion tipoDeAvion = new TipoDeAvion(cantidadFilasEconomy, cantidadAsientosPorFilaDeEconomy, cantidadFilasBussiness, cantidadAsientosPorFilaDeBussiness, cantidadFilasFirst, cantidadAsientosPorFilaDeFirst, nombre);
        tiposDeAvion.add(tipoDeAvion);
    }

    public void addAeropuerto(String codigoDeAeropuerto, String ubicacion, String nombre) {
        Aeropuerto aeropuerto = new Aeropuerto(codigoDeAeropuerto, ubicacion, nombre);
        aeropuertosSaver.save(aeropuerto);
        aeropuertos.add(aeropuerto);
    }

    public void addVuelo(String aeropuertoDeSalida, String aeropuertoDeLlegada, int dia, int mes, int ano, int hours, int minutes, String plane, int flightCode) {
        Vuelo vuelo = new Vuelo(getAeropuerto(aeropuertoDeSalida), getAeropuerto(aeropuertoDeLlegada), dia, mes, ano, hours, minutes, getAvion(plane), flightCode);
    }

    @Override
    public Empleado getEmployee(int currentSesion) {
        for (Empleado e :
                empleados) {
            if(e.getCodigoEmpleado() == currentSesion){return e;}
        }
        throw  new RuntimeException("No existe el empleado");
    }

    private Aeropuerto getAeropuerto(String aeropuerto) {
        for (Aeropuerto a:aeropuertos
             ) {
           if (a.getCodigo().equals(aeropuerto)){return a;}
        }
        throw  new RuntimeException("El aeropuerto no existe");
    }

    private Avion getAvion(String plane) {
        for (Avion a:aviones
             ) {
            if (a.getCodigo().equals(plane)){
                return a;
            }
        }
        throw new RuntimeException("No existe el avion");
    }

    public  Cliente getCliente(int numeroDeCliente){
        for (Cliente c :
                clientes) {
            if(c.getNumeroDeCliente() == numeroDeCliente){return c;}
        }
        throw new RuntimeException("No existe el cliente");
    }

    public void validarCliente(int numeroCliente){
        for (Cliente c: clientes
             ) {
            if (c.getNumeroDeCliente() == numeroCliente) {
                return;
            }
            throw new RuntimeException("El cliente no existe");
        }
    }

    @Override
    public void validarLugarDePartida(String lugarDePartida) {
        for (Aeropuerto a: aeropuertos
             ) {
            if (lugarDePartida==a.getCodigo());{return;}
        }
        throw new RuntimeException("No existe ese aeropuerto");
    }

    @Override
    public void validarLugarDeLlegada(String lugarDeLlegada) {
        for (Aeropuerto a: aeropuertos
                ) {
            if (lugarDeLlegada==a.getCodigo());{return;}
        }
        throw new RuntimeException("No existe ese aeropuerto");
    }

    public List<Reserva> getReservas(int numeroDeCliente) {
        return getCliente(numeroDeCliente).getReservas();
    }
}
