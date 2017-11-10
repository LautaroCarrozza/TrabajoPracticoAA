import java.time.LocalDate;
import java.util.ArrayList;
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
    private List<AreaAdministrativa> areasAdministrativas = new ArrayList<>();

    Saver<Cliente> clientesSaver ;
    Saver<Empleado> empleadosSaver ;
    Saver<Vuelo> vuelosSaver ;
    Saver<Pasaje> pasajesSaver ;
    Saver<TipoDeAvion> tiposDeAvionSaver;
    Saver<Avion> avionesSaver ;
    Saver<Aeropuerto> aeropuertosSaver ;
    Saver<PersonalAbordo> personalDeAbordoSaver;
    Saver<AreaAdministrativa> areasAdministrativasSaver;

    public ServerMock() {
        aeropuertosSaver = new Saver<Aeropuerto>("Aeropuertos");
        clientesSaver = new Saver<>("Clientes");
        vuelosSaver = new Saver<Vuelo>("Vuelos");
        tiposDeAvionSaver = new Saver<>("TiposDeAvion");
        avionesSaver = new Saver<Avion>("Aviones");
        pasajesSaver = new Saver<Pasaje>("Pasajes");
        empleadosSaver = new Saver<Empleado>("Empleados");
        personalDeAbordoSaver = new Saver<PersonalAbordo>("PersonalDeAbordo");
        areasAdministrativasSaver = new Saver<>("AreasAdministrativas");

    }

    public void setUp(){
        addCliente(1, "a", 1);
        addPersonalAbordo(1, "a", "piloto", 1);
        addAeropuerto("aaa", "aaa", "aaa");
        addAeropuerto("bbb", "bbb", "bbb");
        addTipoDeAvion(2, 2, 2, 2, 2, 2, 1, "a");
        addAvion("1", "a");
        addVuelo("aaa", "bbb", 1, 1, 2018, 22, 30,60, "1", 1, 3);
        addVuelo("bbb","aaa",2,2,2018, 22, 30, 60, "1", 2, 3);
        addVuelo("bbb", "aaa", 2, 2, 2018, 22, 30, 60, "1", 2, 3);
        addAreaAdministrativa("gerencia", true);
        addEmpleado(1, "gerente", 1, "gerencia");
        addEmpleado(2,"b",2,"area1");

        aeropuertos = Aeropuerto.build(aeropuertosSaver.get(), this);
        clientes = Cliente.build(clientesSaver.get(), this);
        empleados = Empleado.build(empleadosSaver.get(), this);
        tiposDeAvion = TipoDeAvion.build(tiposDeAvionSaver.get(), this);
        aviones = Avion.build(avionesSaver.get(), this);
        vuelos = Vuelo.build(vuelosSaver.get(), this);
        pasajes = Pasaje.build(pasajesSaver.get(), this);
        personalAbordoLista = PersonalAbordo.build(personalDeAbordoSaver.get(), this);
        areasAdministrativas = AreaAdministrativa.build(areasAdministrativasSaver.get(), this);

        ocuparAsientos();

        addPasajes();
    }

    private void addPasajes() {
        for (Pasaje pasaje:pasajes) {
            pasaje.getCliente().addPasaje(pasaje);
        }
    }

    private void ocuparAsientos() {
        for (Pasaje pasaje: pasajes) {
            pasaje.getVuelo().ocupar(pasaje.getAsiento());
        }
    }



    public void validarSesionCliente(int numero) {

        for (Cliente c : clientes) {
            if(c.getNumeroDeCliente() == numero){
                return;
            }
        }
        throw new RuntimeException("Numero de cliente invalido");
    }

    public void addEmpleado(int dni, String nombre, int codigoEmpleado, String nombreArea){
        for (AreaAdministrativa area:areasAdministrativas) {
          if(area.getNombre().equals(nombreArea)){
              Empleado empleado = new Empleado(dni, nombre, codigoEmpleado,area);
              empleados.add(empleado);
              empleadosSaver.save(empleado);
              break;
          }
        }
    }

    public void addCliente(int dni, String nombre, int numeroDeCliente) {
        Cliente cliente = new Cliente(dni, nombre, numeroDeCliente);
        clientes.add(cliente);
        clientesSaver.save(cliente);
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

    public void comprarAsiento(int codigoVuelo, int codigoCliente, int fila, String columna, int cantidadDePersonas) {

        Vuelo vuelo = getVuelo(codigoVuelo);

        if (!vuelo.getOcupacion(fila, columna)){
            vuelo.ocupar(fila, columna);
            Pasaje pasaje = new Pasaje(vuelo, fila, columna, getCliente(codigoCliente));
            pasajes.add(pasaje);
            getCliente(codigoCliente).addPasaje(pasaje);
            pasajesSaver.save(pasaje);
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


    public void validarSesionEmpleado(int currentSesion) {

        for (Empleado empleado: empleados ) {
            if (empleado.getCodigoEmpleado() == currentSesion && empleado.getArea().isHabilitacionVenta())return;
        }
        throw new RuntimeException("Codigo de empleado invalido");
    }

    public TipoDeAvion getTipoDeAvion(String tipoDeAvion) {

        for (TipoDeAvion t:tiposDeAvion) {
            if (t.getNombre().equals(tipoDeAvion)){
                return t;
            }
        }
        throw new RuntimeException("No existe el tipo de avion");
    }

    public void addAvion(String codigo, String tipoDeAvionStr) {

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
        LocalDate localDate = LocalDate.of(ano, mes, dia);

        for (int i = 0; i <= repeticiones; i++) {
            Vuelo vuelo = new Vuelo(getAeropuerto(aeropuertoDeSalida), getAeropuerto(aeropuertoDeLlegada), localDate.plusDays(i*7).getDayOfMonth(), localDate.plusDays(i*7).getMonthValue(), localDate.plusDays(i*7).getYear(), hours, minutes,minutesDuration, getAvion(plane), flightCode);
            vuelos.add(vuelo);
            vuelosSaver.save(vuelo);
        }
    }

    @Override
    public Empleado getEmployee(int currentSesion) {

        for (Empleado e : empleados) {
            if(e.getCodigoEmpleado() == currentSesion){return e;}
        }
        throw  new RuntimeException("No existe el empleado");
    }

    public Aeropuerto getAeropuerto(String aeropuerto) {

        for (Aeropuerto a:aeropuertos
             ) {
           if (a.getCodigo().equals(aeropuerto)){return a;}
        }
        throw  new RuntimeException("El aeropuerto no existe");
    }

    public Avion getAvion(String plane) {

        for (Avion a:aviones) {
            if (plane.equals(a.getCodigo())){
                return a;
            }
        }
        throw new RuntimeException("No extiste el avion");
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
            if (a.getUbicacion().equals(lugarDePartida)) {
                return;
            }
        }
        throw new RuntimeException("No existe ese aeropuerto");
    }

    @Override
    public void validarLugarDeLlegada(String lugarDeLlegada) {
        validarLugarDePartida(lugarDeLlegada);
    }

    public void validarSesionEmpleadoAbordo(int currentSesion) {

        for (PersonalAbordo personalAbordo:personalAbordoLista) {
            if(personalAbordo.getNumeroDeEmpleado() == currentSesion){
                return;
            }
        }
        throw new RuntimeException("No existe ese numero para personal de abordo");
    }

    @Override
    public PersonalAbordo getPersonalAbordo(int numeroDeEmpleado) {
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
    public void restart() {

    }

    public List<Reserva> getReservas(int numeroDeCliente) {
       return getCliente(numeroDeCliente).getReservas();
    }

    public List<PersonalAbordo> getPersonalAbordoLista() {
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

    @Override
    public void addTarifa(String first, int codigoDeVuelo, int precioFirst) {

    }

    @Override
    public int getPreciodeTarifa(String codigo, String first) {
        return 0;
    }

    @Override
    public AreaAdministrativa getAreaAdministrativa(String nombreArea) {
        for (AreaAdministrativa area:areasAdministrativas) {
            if(area.getNombre().equals(nombreArea)){
                return area;
            }
        }
        throw new RuntimeException("No existe area con ese nombre");
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

    public void addAreaAdministrativa(String nombre, boolean habilitadoVenta){
        AreaAdministrativa areaAdministrativa = new AreaAdministrativa(nombre,habilitadoVenta);
        areasAdministrativas.add(areaAdministrativa);
        areasAdministrativasSaver.save(areaAdministrativa);
    }
    public List<AreaAdministrativa> getAreasAdministrativas() {
        return areasAdministrativas;
    }

    @Override
    public void addTripulacion(Vuelo vuelo) {
        addPiloto(vuelo);
        for (int i = 0; i < vuelo.getCantidadPersonal() -1 ; i++) {
            addPersonalAbordoenVuelo(vuelo);
        }
    }

    @Override
    public void addPersonalAbordoenVuelo(Vuelo vuelo) {
        for (PersonalAbordo personal:getPersonalAbordoLista()) {
            if (!personal.getCargo().equals("Piloto") && personal.available(vuelo.getFechaSalida())){
                vuelo.addTripulacion(personal);
                personal.addVuelo(vuelo);
            }
        }
        throw new RuntimeException("No existe personal de abordo disponible para el vuelo");
    }

    @Override
    public void addPiloto(Vuelo vuelo) {
        for (PersonalAbordo piloto:getPersonalAbordoLista()) {
            if (piloto.getCargo().equals("Piloto") && piloto.available(vuelo.getFechaSalida())) {
                vuelo.addTripulacion(piloto);
                piloto.addVuelo(vuelo);
            }
        }
        throw new RuntimeException("No existen pilotos disponibles");

    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Avion> getAviones() {
        return aviones;
    }

    public List<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }
}
