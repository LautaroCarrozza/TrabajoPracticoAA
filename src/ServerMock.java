import com.sun.org.apache.regexp.internal.RE;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ServerMock implements ServerInterface{

    private List<Cliente> clientes = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();
    private List<Vuelo> vuelos = new ArrayList<>();
    private List<Pasaje> pasajes = new ArrayList<>();
    private List<TipoDeAvion> tiposDeAvion = new ArrayList<>();
    private List<Avion> aviones = new ArrayList<>();
    private List<Aeropuerto> aeropuertos= new ArrayList<>();
    private List<PersonalAbordo> personalAbordoLista = new ArrayList<>();
    private List<Saver> savers = new ArrayList<>();

    Saver clientesSaver ;
    Saver empleadosSaver ;
    Saver vuelosSaver ;
    Saver pasajesSaver ;
    Saver tiposDeAvionSaver;
    Saver avionesSaver ;
    Saver aeropuertosSaver ;
    Saver personalDeAbordoSaver;

    public ServerMock() {
        aeropuertosSaver = new Saver("Aeropuertos");
        clientesSaver = new Saver("Clientes");
        vuelosSaver = new Saver("Vuelos");
        tiposDeAvionSaver = new Saver("TiposDeAvion");
        avionesSaver = new Saver("Aviones");
        pasajesSaver = new Saver("Pasajes");
        empleadosSaver = new Saver("Empleados");
        personalDeAbordoSaver = new Saver("PersonalDeAbordo");
    }

    public void setUp(){
        addCliente(1, "a", 1);
        addPersonalAbordo(1, "a", "piloto", 1);
        addAeropuerto("aaa", "aaa", "aaa");
        addAeropuerto("bbb", "bbb", "bbb");
        addTipoDeAvion(2, 2, 2, 2, 2, 2, 1, "a");
        addAvion("1", "a");
        addVuelo("aaa", "bbb", 1, 1, 2018, 22, 30,60, "1", 1, 3);
        addEmpleado(1, "a", 1, true);

        aeropuertos = Aeropuerto.build(aeropuertosSaver.get());
        savers.add(aeropuertosSaver);

        clientes = Cliente.build(clientesSaver.get());
        savers.add(clientesSaver);

        vuelos = Vuelo.build(vuelosSaver.get());
        savers.add(vuelosSaver);

        tiposDeAvion = TipoDeAvion.build(tiposDeAvionSaver.get());
        savers.add(vuelosSaver);

        aviones = Avion.build(avionesSaver.get());
        savers.add(avionesSaver);

        pasajes = Pasaje.build(pasajesSaver.get());
        savers.add(pasajesSaver);
        asignarReseras();

        empleados = Empleado.build(empleadosSaver.get());
        savers.add(empleadosSaver);


    }

    private void asignarReseras() {
        for (Pasaje pasaje:pasajes) {
            List<Pasaje> pasajes = new ArrayList<>();
            pasajes.add(pasaje);
            for (Pasaje pasaje2:pasajes                 ) {
                if (pasaje2.getCliente().equals(pasaje.getCliente()) && pasaje2.getVuelo().equals(pasaje.getVuelo())){
                    pasajes.add(pasaje2);
                }
            }
            pasaje.getCliente().guardarReserva(pasajes, pasaje.getVuelo());
        }
    }

    public void validarSesionCliente(int numero) {
        clientes = Cliente.build(clientesSaver.get());

        for (Cliente c : clientes) {
            if(c.getNumeroDeCliente() == numero){
                return;
            }
        }
        throw new RuntimeException("Numero de cliente invalido");
    }

    public void addEmpleado(int dni, String nombre, int codigoEmpleado, boolean habilitadoParaVender){
        Empleado empleado = new Empleado(dni, nombre, codigoEmpleado,habilitadoParaVender);
        empleados.add(empleado);
        empleadosSaver.save(empleado);
    }

    public void addCliente(int dni, String nombre, int numeroDeCliente) {
        Cliente cliente = new Cliente(dni, nombre, numeroDeCliente);
        clientes.add(cliente);
        clientesSaver.save(cliente);
    }

    public List<Vuelo> buscarVuelos(int dia, int mes, int ano, String lugarSalida, String lugarLlegada, int cantidadPersonas){
        vuelos = Vuelo.build(vuelosSaver.get());
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

    public void comprarAsiento(int codigoVuelo, int codigoCliente, Asiento asiento, int cantidadDePersnas) {
        vuelos = Vuelo.build(vuelosSaver.get());
        Vuelo vuelo = getVuelo(codigoVuelo);
            if (!vuelo.getOcupacion(asiento)){
                vuelo.ocupar(asiento);
                Pasaje pasaje = new Pasaje(vuelo, asiento, getCliente(codigoCliente));
                pasajes.add(pasaje);
                pasajesSaver.save(pasaje);
            }
            else{throw new RuntimeException("El asiento esta ocupado");}
    }

    public Vuelo getVuelo(int codigoDeVuelo) {
        vuelos = Vuelo.build(vuelosSaver.get());
        for (Vuelo v:vuelos) {
            if (v.getCodigoDeVuelo()== (codigoDeVuelo)){
                return v;
            }
        }
        throw new RuntimeException("No existen vuelos con ese codigo");
    }

    public void guardarReserva(int codigoCliente, Vuelo vuelo) {
        pasajes = Pasaje.build(pasajesSaver.get());
        List<Pasaje> pasajesReservados = new ArrayList<>();
        for (Pasaje pasaje:pasajes) {
            if (pasaje.getCliente().getNumeroDeCliente() == codigoCliente && pasaje.getVuelo().equals(vuelo)){
                pasajesReservados.add(pasaje);
            }
        }
        getCliente(codigoCliente).guardarReserva(pasajesReservados, vuelo);
        System.out.println("La reserva se a guardado correctamente");
    }

    public void validarSesionEmpleado(int currentSesion) {
        empleados = Empleado.build(empleadosSaver.get());
        for (Empleado empleado: empleados ) {
            if (empleado.getCodigoEmpleado() == currentSesion)return;
        }
        throw new RuntimeException("Codigo de empleado invalido");
    }

    public TipoDeAvion getTipoDeAvion(String tipoDeAvion) {
        tiposDeAvion = TipoDeAvion.build(tiposDeAvionSaver.get());
        for (TipoDeAvion t:tiposDeAvion) {
            if (t.getNombre().equals(tipoDeAvion)){
                return t;
            }
        }
        throw new RuntimeException("No existe el tipo de avion");
    }

    public void addAvion(String codigo, String tipoDeAvionStr) {
        tiposDeAvion = TipoDeAvion.build(tiposDeAvionSaver.get());
        TipoDeAvion tipoDeAvion = getTipoDeAvion(tipoDeAvionStr);
        Avion avion = new Avion(codigo, tipoDeAvion);
        aviones.add(avion);
        avionesSaver.save(avion);
    }

    public void addTipoDeAvion(int cantidadFilasEconomy, int cantidadAsientosPorFilaDeEconomy, int cantidadFilasBussiness, int cantidadAsientosPorFilaDeBussiness, int cantidadFilasFirst, int cantidadAsientosPorFilaDeFirst, int cantidadPersonalAbordo, String nombre) {
        TipoDeAvion tipoDeAvion = new TipoDeAvion(cantidadFilasEconomy, cantidadAsientosPorFilaDeEconomy, cantidadFilasBussiness, cantidadAsientosPorFilaDeBussiness, cantidadFilasFirst, cantidadAsientosPorFilaDeFirst, cantidadPersonalAbordo,nombre);
        tiposDeAvion.add(tipoDeAvion);
        tiposDeAvionSaver.save(tipoDeAvion);
    }

    public void addAeropuerto(String codigoDeAeropuerto, String ubicacion, String nombre) {
        Aeropuerto aeropuerto = new Aeropuerto(codigoDeAeropuerto, ubicacion, nombre);
        aeropuertosSaver.save(aeropuerto);
        aeropuertos.add(aeropuerto);
    }

    public void addVuelo(String aeropuertoDeSalida, String aeropuertoDeLlegada, int dia, int mes, int ano, int hours, int minutes,int minutesDuration, String plane, int flightCode, int repeticiones) {
        aeropuertos = Aeropuerto.build(aeropuertosSaver.get());
        aviones = Avion.build(avionesSaver.get());
        LocalDate localDate = LocalDate.of(ano, mes, dia);

        for (int i = 0; i <= repeticiones; i++) {
           localDate = localDate.plusDays(i * 7);///multiplico por i para no sumar en el primero
            int newDia = localDate.getDayOfMonth();
            int newMonth = localDate.getMonthValue();
            int newYear = localDate.getYear();
            Vuelo vuelo = new Vuelo(getAeropuerto(aeropuertoDeSalida), getAeropuerto(aeropuertoDeLlegada), newDia, newMonth, newYear, hours, minutes,minutesDuration, getAvion(plane), flightCode);
            vuelos.add(vuelo);
            vuelosSaver.save(vuelo);
        }
    }

    @Override
    public Empleado getEmployee(int currentSesion) {
        empleados = Empleado.build(empleadosSaver.get());
        for (Empleado e :
                empleados) {
            if(e.getCodigoEmpleado() == currentSesion){return e;}
        }
        throw  new RuntimeException("No existe el empleado");
    }

    public Aeropuerto getAeropuerto(String aeropuerto) {
        aeropuertos = Aeropuerto.build(aeropuertosSaver.get());
        for (Aeropuerto a:aeropuertos
             ) {
           if (a.getCodigo().equals(aeropuerto)){return a;}
        }
        throw  new RuntimeException("El aeropuerto no existe");
    }

    public Avion getAvion(String plane) {
        aviones = Avion.build(avionesSaver.get());
        for (Avion a:aviones) {
            if (plane.equals(a.getCodigo())){
                return a;
            }
        }
        throw new RuntimeException("No extiste el avion");
    }

    public  Cliente getCliente(int numeroDeCliente){
        clientes = Cliente.build(clientesSaver.get());
        for (Cliente c :
                clientes) {
            if(c.getNumeroDeCliente() == numeroDeCliente){return c;}
        }
        throw new RuntimeException("No existe el cliente");
    }

    public void validarCliente(int numeroCliente){
        clientes = Cliente.build(clientesSaver.get());

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
        aeropuertos = Aeropuerto.build(aeropuertosSaver.get());
        for (Aeropuerto a: aeropuertos
             ) {
            if (a.getUbicacion().equals(lugarDePartida)) {
                return;
            }
        }
        throw new RuntimeException("No existe ese aeropuerto");
    }

    @Override
    public void validarLugarDeLlegada(String lugarDeLlegada) {
        aeropuertos = Aeropuerto.build(aeropuertosSaver.get());
        validarLugarDePartida(lugarDeLlegada);
    }

    public void validarSesionEmpleadoAbordo(int currentSesion) {
        personalAbordoLista = PersonalAbordo.build(personalDeAbordoSaver.get());
        for (PersonalAbordo personalAbordo:personalAbordoLista) {
            if(personalAbordo.getNumeroDeEmpleado() == currentSesion){
                return;
            }
        }
        throw new RuntimeException("No existe ese numero para personal de abordo");
    }

    @Override
    public PersonalAbordo getPersonalAbordo(int numeroDeEmpleado) {
        personalAbordoLista = PersonalAbordo.build(personalDeAbordoSaver.get());
        for (PersonalAbordo personalDeAbordo:personalAbordoLista) {
            if (personalDeAbordo.getNumeroDeEmpleado() == numeroDeEmpleado)return personalDeAbordo;
        }
        throw new RuntimeException("No existe el personal de abordo");
    }

    public void addPersonalAbordo(int dni, String nombre, String cargo, int numeroDeEmpleado) {
        PersonalAbordo personalAbordo = new PersonalAbordo(dni,nombre,cargo,numeroDeEmpleado);
        personalAbordoLista.add(personalAbordo);
        personalDeAbordoSaver.save(personalAbordo);
    }

    @Override
    public void restar() {

    }

    public List<Reserva> getReservas(int numeroDeCliente) {
        clientes = Cliente.build(clientesSaver.get());
        return getCliente(numeroDeCliente).getReservas();
    }

    public List<PersonalAbordo> getPersonalAbordoLista() {
        personalAbordoLista = PersonalAbordo.build(personalDeAbordoSaver.get());
        return personalAbordoLista;
    }

    public void validarVueloPorCantidadDePersonal(Vuelo vuelo){
        if(vuelo.getAvion().getTipoDeAvion().getCantidadDePersonalAbordo() <= vuelo.getListaPersonalAbordo().size()){
            return;
        }
        throw new RuntimeException("Cantidad de personal abordo es mayor a la capacidad del tipo de avion");
    }

    public void validarDisponibilidadTripulacion(LocalDate fechaDeSalida, int cantidadDePersonal){
      validarDisponibilidadPiloto(fechaDeSalida);
        for (int i = 0; i < cantidadDePersonal -1; i++) {
            validadDisponibilidadPersonalDeAbordo(fechaDeSalida);
        }
    }

    private void validadDisponibilidadPersonalDeAbordo(LocalDate fechaDeSalida) {
        for (PersonalAbordo personal:getPersonalAbordoLista()) {
            if (!personal.getCargo().equals("Piloto") && personal.available(fechaDeSalida)){return;}
        }
        throw new RuntimeException("No existe personal de abordo disponible para el vuelo");
    }

    private void validarDisponibilidadPiloto(LocalDate fechaDeSalida) {
        for (PersonalAbordo piloto:getPersonalAbordoLista()) {
            if (piloto.getCargo().equals("Piloto") && piloto.available(fechaDeSalida)) {
                return;
            }
        }
        throw new RuntimeException("No hay suficientes tripulantes disponibles");
    }


}
