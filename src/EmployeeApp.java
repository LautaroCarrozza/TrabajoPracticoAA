import java.time.LocalDate;
import java.util.List;

public class EmployeeApp {


    private ServerInterface server;
    private int currentSesion, currentClient, diaSalida, mesSalida, anoSalida, cantidadDePasajeros, diaSalidaIda, diaSalidaVuelta, mesSalidaIda, mesSalidaVuelta, anoSalidaIda, anoSalidaVuelta;
    public  String currentDesde, currentHasta;
    public  Vuelo vueloDeseado, vueloDeseadoIda, vueloDeseadoVuelta;

    public EmployeeApp(ServerInterface server) {
        this.server = server;
    }

    public  void iniciarSesion(){
      try {
      currentSesion = Scanner.getInt("Ingrese su numero de empleado: ");
      server.validarSesionEmpleado(currentSesion);
      mostrarMenuAcciones();
      }
      catch (RuntimeException e){
          System.out.println(e.getMessage());
          mostrarMenu();
      }
    }

    private  void cerrarSesion() {
        borrarPantalla();
        System.out.println("Sesion cerrada");
        iniciarSesion();
    }

    public void mostrarMenu(){
        System.out.println("    1- Iniciar Sesion");
        System.out.println("    2- Exit");

        int option = Scanner.getInt("Ingrese opcion deseada");
        switch (option){
            case 1:iniciarSesion();break;
            case 2:MainApp.mostrarMenu();break;
        }


    }

    private  void mostrarMenuAcciones() {
        System.out.println("1- Ingresar tipo de avion");
        System.out.println("2- Ingresar avion");
        System.out.println("3- Ingresar Aeropuerto");
        System.out.println("4- Ingresar vuelo");
        System.out.println("- - - - - - - - - - - - - - - - - -");
        System.out.println("5- Ingresar area administrativa");
        System.out.println("6- Ingresar empleado");
        System.out.println("- - - - - - - - - - - - - - - - - -");
        System.out.println("7- VenderPasaje");
        System.out.println("- - - - - - - - - - - - - - - - - -");
        System.out.println("8- Cerrar Sesion");
        System.out.println("9- Exit");
        System.out.println();

        try {
            int opcion = Scanner.getInt("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    ingresarTipoDeAvion();
                    break;
                case 2:
                    ingresarAvion();
                    break;
                case 3:
                    ingresarAeropuerto();
                    break;
                case 4:
                    ingresarVuelo();
                    break;
                case 5:
                    ingresarAreaAdministrativa();
                    break;
                case 6:
                    ingresarEmpleado();
                case 7:
                    menuDeVenta();
                    break;
                case 8:
                    cerrarSesion();
                    break;
                case 9:
                    mostrarMenu();
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        } catch (RuntimeException e) {
            borrarPantalla();
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }

    private  void ingresarEmpleado() {
        try{
            if(server.getEmployee(currentSesion).getArea().getNombre().equals("gerencia")){
                int dniEmpleado = Scanner.getInt("Ingrese el dni del empleado: ");
                String nombreEmpleado = Scanner.getString("Ingrese el nombre del empleado: ");
                int codigoEmpleado = Scanner.getInt("Ingrese el codigo del empleado: ");
                for (Empleado empleado:server.getEmpleados()) {
                    if(empleado.getCodigoEmpleado() == codigoEmpleado){
                        throw new RuntimeException("Ya existe un empleado con ese codigo");
                    }
                }
                String nombreArea = Scanner.getString("Ingrese el nombre del area del empleado: ");
                for (AreaAdministrativa area:server.getAreasAdministrativas()) {
                    if(server.getAreaAdministrativa(nombreArea).getNombre().equals(area.getNombre())){
                        server.addEmpleado(dniEmpleado,nombreEmpleado,codigoEmpleado,nombreArea);
                        System.out.println("Empleado cargado");
                        mostrarMenuAcciones();
                    }
                }
            }

            throw new RuntimeException("Area de empleado invalida");
        }

        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAcciones();
        }
        mostrarMenuAcciones();
    }

    private  void ingresarAreaAdministrativa() {
        try{
            if(server.getEmployee(currentSesion).getArea().getNombre().equals("gerencia")) {
                String nombre = Scanner.getString("Ingrese un nombre para el area: ");
                for (AreaAdministrativa area:server.getAreasAdministrativas()) {
                    if(area.getNombre().equals(nombre)){
                        throw new RuntimeException("Ya existe un area con ese nombre");
                    }
                }
                System.out.println("¿Esta habilitado para vender?");
                System.out.println();
                System.out.println("Si");
                System.out.println("No");
                String opcion = Scanner.getString("Elija una opcion: ");
                System.out.println();
                if (opcion.equals("Si")) {
                    server.addAreaAdministrativa(nombre, true);
                    System.out.println("Area Administrativa cargada");
                    mostrarMenuAcciones();
                }
                if (opcion.equals("No")) {
                    server.addAreaAdministrativa(nombre, false);
                    System.out.println("Area Administrativa cargada");
                    mostrarMenuAcciones();
                }
            }
            throw new RuntimeException("Area de empleado invalida");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAcciones();
        }
        mostrarMenuAcciones();
    }

    private  void venderPasajeIda() {

        ingresoRegistroDelCliente();
        venderACuantosPasajeros();
        venderDesde();
        venderHasta();
        venderCuando();
        validarVuelo();
        seleccionarVuelo();
        for (int i = 0; i < cantidadDePasajeros; i++) {
            venderAsiento();
        }
        System.out.println("La compra se realizo correctamente");
        System.out.println("");
        mostrarMenuAcciones();
    }

    private  void menuDeVenta() {
        try {
            System.out.println("1- Vender pasaje solo ida");
            System.out.println("2- Vender pasaje ida y vuela");
            int opcion = Scanner.getInt("Seleccione la opcion deseada: ");
            switch (opcion) {
                case 1:
                    venderPasajeIda();
                    break;
                case 2:
                    venderPasajeIdaYVuelta();
                    break;
                default:
                    throw new RuntimeException("Opcion no disponible");
            }
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            menuDeVenta();
        }
    }

    private void ingresoRegistroDelCliente(){
        System.out.println("1- Si");
        System.out.println("2- No");
        int opcion = Scanner.getInt("El cliente ya esta registrado?");
        switch(opcion){
            case 1: venderACliente();break;
            case 2: registrarCliente();break;
            default:
                System.out.println("Opcion invalida");
                ingresoRegistroDelCliente();
        }
    }

    private  void venderPasajeIdaYVuelta() {

        ingresoRegistroDelCliente();
        venderACuantosPasajeros();
        venderDesde();
        venderHasta();
        venderCuandoIda();
        validarVueloIda();
        seleccionarVueloIda();
        venderCuandoVuelta();
        validarVueloVuelta();

        for (int i = 0; i < cantidadDePasajeros; i++) {
            venderAsientoIda();
        }

        seleccionarVueloVuelta();
        for (int i = 0; i < cantidadDePasajeros; i++) {
            venderAsientoVuelta();
        }

        System.out.println("Las reservas se a guardadon correctamente");

        mostrarMenuAcciones();

    }

    private  void seleccionarVuelo() {
        List<Vuelo> vuelosDisponibles = server.buscarVuelos(diaSalida, mesSalida, anoSalida,currentDesde,currentHasta, cantidadDePasajeros);
        int opcion = 1;
        for (Vuelo v: vuelosDisponibles
             ) {
            System.out.println(opcion +" "+ v);
            opcion++;
        }
        int intVueloDeseado = Scanner.getInt("Ingrese vuelo deseado: ") -1 ;/// -1 por que los vuelos empiezan con 0 y se los imprime con un +1
        vueloDeseado = vuelosDisponibles.get(intVueloDeseado);
    }

    private  void seleccionarVueloIda() {
        List<Vuelo> vuelosDisponibles = server.buscarVuelos(diaSalidaIda, mesSalidaIda, anoSalidaIda,currentDesde,currentHasta, cantidadDePasajeros);
        int opcion = 1;
        for (Vuelo v: vuelosDisponibles
                ) {
            System.out.println(opcion +" "+ v);
            opcion++;
        }
        int intVueloDeseado = Scanner.getInt("Ingrese vuelo deseado para la ida: ") -1 ;/// -1 por que los vuelos empiezan con 0 y se los imprime con un +1
        vueloDeseadoIda = vuelosDisponibles.get(intVueloDeseado);
    }

    private  void seleccionarVueloVuelta() {
        List<Vuelo> vuelosDisponibles = server.buscarVuelos(diaSalidaVuelta, mesSalidaVuelta, anoSalidaVuelta,currentHasta,currentDesde, cantidadDePasajeros);
        int opcion = 1;
        for (Vuelo v: vuelosDisponibles
                ) {
            System.out.println(opcion +" "+ v);
            opcion++;
        }
        int intVueloDeseado = Scanner.getInt("Ingrese vuelo deseado para la vuelta: ") -1 ;/// -1 por que los vuelos empiezan con 0 y se los imprime con un +1
        vueloDeseadoVuelta = vuelosDisponibles.get(intVueloDeseado);
    }

    private  void venderAsiento(){
        List<Asiento> asientosDisponibles = vueloDeseado.asientosDisponibles();

        System.out.println("Asientos disponibles: ");
        System.out.println();
        for (Asiento asiento : asientosDisponibles) {
            System.out.println(asiento);
        }
        try {
            int fila = Scanner.getInt("Ingresar flia deseada: ");
            String columna = Scanner.getString("Ingresar columna deseada: ");
            if( !vueloDeseado.getOcupacion(fila, columna)){
                String nombre = Scanner.getString("Ingrese el nombre del pasajero: ");
                int dni = Scanner.getInt("Ingrese el DNI del pasajero: ");
                server.comprarAsiento(vueloDeseado.getCodigoDeVuelo(), currentClient, fila, columna, cantidadDePasajeros, nombre, dni);
            }
            else {
                throw new RuntimeException("Seleccion de asiento no disponible");
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            venderAsiento();
        }

    }

    private  void venderAsientoIda(){
        List<Asiento> asientosDisponibles = vueloDeseadoIda.asientosDisponibles();

        System.out.println("Asientos disponibles para la ida: ");
        System.out.println();
        for (Asiento asiento : asientosDisponibles) {
            System.out.println(asiento);
        }
        try {
            int fila = Scanner.getInt("Ingresar flia deseada: ");
            String columna = Scanner.getString("Ingresar columna deseada: ");
            if( !vueloDeseadoIda.getOcupacion(fila, columna)){
                String nombre = Scanner.getString("Ingrese el nombre del pasajero: ");
                int dni = Scanner.getInt("Ingrese el DNI del pasajero: ");
                server.comprarAsiento(vueloDeseadoIda.getCodigoDeVuelo(), currentClient, fila, columna, cantidadDePasajeros, nombre, dni);
            }
            else {
                throw new RuntimeException("Seleccion de asiento no disponible");
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            venderAsientoIda();
        }

    }

    private  void venderAsientoVuelta(){
        List<Asiento> asientosDisponibles = vueloDeseadoVuelta.asientosDisponibles();

        System.out.println("Asientos disponibles para la vuelta: ");
        System.out.println();
        for (Asiento asiento : asientosDisponibles) {
            System.out.println(asiento);
        }
        try {
            int fila = Scanner.getInt("Ingresar flia deseada: ");
            String columna = Scanner.getString("Ingresar columna deseada: ");
            if( !vueloDeseadoVuelta.getOcupacion(fila, columna)){
                String nombre = Scanner.getString("Ingrese el nombre del pasajero: ");
                int dni = Scanner.getInt("Ingrese el DNI del pasajero: ");
                server.comprarAsiento(vueloDeseadoVuelta.getCodigoDeVuelo(), currentClient, fila, columna, cantidadDePasajeros, nombre, dni);
            }
            else {
                throw new RuntimeException("Seleccion de asiento no disponible");
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            venderAsientoVuelta();
        }

    }

    private  void validarVuelo(){
        try{
            server.buscarVuelos(diaSalida, mesSalida, anoSalida,currentDesde,currentHasta, cantidadDePasajeros);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAcciones();
        }
    }

    private  void validarVueloIda(){
        try{
            server.buscarVuelos(diaSalidaIda, mesSalidaIda, anoSalidaIda,currentDesde,currentHasta, cantidadDePasajeros);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAcciones();
        }
    }

    private  void validarVueloVuelta(){
        try{
            server.buscarVuelos(diaSalidaVuelta, mesSalidaVuelta, anoSalidaVuelta,currentHasta,currentDesde, cantidadDePasajeros);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAcciones();
        }
    }

    private  void venderACuantosPasajeros(){
        cantidadDePasajeros = Scanner.getInt("Ingrese la cantidad de pasajeros: ");
    }

    private  void venderCuando(){
        diaSalida = Scanner.getInt("Ingrese dia de salida: ");
        mesSalida = Scanner.getInt("Ingrese mes de salida: ");
        anoSalida = Scanner.getInt("Ingrese año de salida: ");
    }

    private  void venderCuandoIda(){
        diaSalidaIda = Scanner.getInt("Ingrese dia de salida de la ida: ");
        mesSalidaIda = Scanner.getInt("Ingrese mes de salida de la ida: ");
        anoSalidaIda = Scanner.getInt("Ingrese año de salida de la ida: ");
    }

    private  void venderCuandoVuelta(){
        diaSalidaVuelta = Scanner.getInt("Ingrese dia de salida de la vuelta: ");
        mesSalidaVuelta = Scanner.getInt("Ingrese mes de salida de la vuelta: ");
        anoSalidaVuelta = Scanner.getInt("Ingrese año de salida de la vuelta: ");
    }

    private  void venderHasta() {
        try {
            currentHasta = Scanner.getString("Ingrese lugar de llegada: ");
            server.validarLugarDeLlegada(currentHasta);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            venderHasta();
        }
    }

    private  void venderDesde() {
        try {
            currentDesde = Scanner.getString("Ingrese lugar de partida: ");
            server.validarLugarDePartida(currentDesde);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            venderDesde();
        }
    }

    private  void venderACliente() {
        try{
            currentClient = Scanner.getInt("Ingresar el numero del cliente: ");
            server.validarCliente(currentClient);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            registrarCliente();
        }
    }

    private void registrarCliente(){
        try{
            int dni = Scanner.getInt("Ingresar DNI del cliente: ");
            String nombre = Scanner.getString("Ingrese el nombre del cliente: ");
            int codigo = Scanner.getInt("Ingrese el numero del cliente: ");
            for (Cliente cliente:server.getClientes()) {
                if(cliente.getNumeroDeCliente() == codigo){
                    throw new RuntimeException("Ya existe un cliente con ese codigo");
                }
            }
            server.addCliente(dni, nombre, codigo);
            venderACliente();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            registrarCliente();
        }
    }

    private  void ingresarVuelo() {

        try {
            String aeropuertoDeSalida = Scanner.getString("Ingrese el codigo del aeropuerto de salida: ");
            String aeropuertoDeLlegada = Scanner.getString("Ingrese el codigo del aeropuerto de llegada: ");
            int dia = Scanner.getInt("Ingrese el dia de salida del vuelo: ");
            int mes = Scanner.getInt("Ingese el mes de salida del vuelo: ");
            int ano = Scanner.getInt("Ingrese el año de salida del vuelo: ");
            int hours = Scanner.getInt("Ingrese la hora de salida del vuelo: ");
            int minutes = Scanner.getInt("ingrese los minutos de la hora de salida del vuelo: ");
            int minutesDuration = Scanner.getInt("Ingrese los minutos de la duracion del viaje: ");
            String plane = Scanner.getString("Ingrese el codigo del avion a utilizar: ");
            int flightCode = Scanner.getInt("Ingrese el codigo del vuelo: ");

            for (Vuelo vuelo:server.getVuelos()) {
                if(vuelo.getCodigoDeVuelo() == flightCode){
                    throw new RuntimeException("Ya existe un vuelo con ese codigo");
                }
            }

            int precioEconomy = 0;
            int precioBussiness = 0;
            int precioFirst = 0;

            if (server.getAvion(plane).tieneEconomy()) {
                 precioEconomy = Scanner.getInt("Ingrese el precio para Economy: ");
            }
            if (server.getAvion(plane).tieneBussiness()) {
                 precioBussiness = Scanner.getInt("Ingrese el precio para Bussiness: ");
            }
            if (server.getAvion(plane).tieneFirst()) {
                 precioFirst = Scanner.getInt("Ingrese el precio para First: ");
            }

            int cantidadDeSemanas = Scanner.getInt("¿Durante cuantas semanas va a repetirse el vuelo?");
            LocalDate localDate = LocalDate.of(ano, mes, dia);
            server.addVuelo(aeropuertoDeSalida, aeropuertoDeLlegada, dia, mes, ano, hours, minutes,minutesDuration, plane, flightCode, cantidadDeSemanas, precioEconomy, precioBussiness, precioFirst);
            server.getAvion(plane).agregarVuelo(server.getVuelo(flightCode));
            server.getAvion(plane).confirmarDisponibilidad(localDate);
            server.validarDisponibilidadTripulacion(localDate, server.getAvion(plane).getCantidadDePersonal());
            server.addTripulacion(server.getVuelo(flightCode));
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAcciones();
        }
        System.out.println("Vuelos cargados");
        mostrarMenuAcciones();
    }

    private  void ingresarAeropuerto(){

        try {

            String codigoDeAeropuerto = Scanner.getString("Ingrese el codigo del aeropuerto: ");
            for (Aeropuerto aeropuerto:server.getAeropuertos()) {
                if(aeropuerto.getCodigo().equals(codigoDeAeropuerto)){
                    throw new RuntimeException("Ya existe un aeropuerto con ese codigo");
                }
            }
            String ubicacion = Scanner.getString("Ingrese la ubicacion del aeropuerto: ");
            String nombre = Scanner.getString("Ingrese el nombre del aeropuerto:");
            server.addAeropuerto(codigoDeAeropuerto, ubicacion, nombre);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAcciones();
        }
            System.out.println("Aeropuerto cargado");
            mostrarMenuAcciones();

    }

    private  void ingresarAvion() {
        try {
            String codigoAvion = Scanner.getString("Ingrese el codigo del avion: ");
            for (Avion avion:server.getAviones()) {
                if(avion.getCodigo().equals(codigoAvion)){
                    throw new RuntimeException("Ya existe un avion con ese codigo");
                }
            }
            String tipoDeAvion = Scanner.getString("Ingrese el tipo de avion para este avion: ");
            server.addAvion(codigoAvion, tipoDeAvion);
            System.out.println("Avion cargado");
            mostrarMenuAcciones();
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAcciones();
        }


    }

    private  void ingresarTipoDeAvion() {

        try {

            int cantidadFilasEconomy = Scanner.getInt("Ingrese la cantidad de filas para la categoria Economy: ");
            int cantidadAsientosPorFilaDeEconomy = Scanner.getInt("Ingrese la cantidad de asientos por fila para la categoria Economy: ");
            int cantidadFilasBussiness = Scanner.getInt("Ingrese la cantidad de filas para la categoria Bussiness: ");
            int cantidadAsientosPorFilaDeBussiness = Scanner.getInt("Ingrese la cantidad de asientos por fila para la categoria Bussiness: ");
            int cantidadFilasFirst = Scanner.getInt("Ingrese la cantidad de filas para la categoria First: ");
            int cantidadAsientosPorFilaDeFirst = Scanner.getInt("Ingrese la cantidad de asientos por fila para la categoria First: ");
            int cantidadDePersonalAbordo = Scanner.getInt("Ingrese la cantidad de personal de abordo para el tipo de avion: ");
            String nombre = Scanner.getString("Ingrese el nombre para el tipo de avion ingresado: ");

            server.addTipoDeAvion(cantidadFilasEconomy, cantidadAsientosPorFilaDeEconomy, cantidadFilasBussiness, cantidadAsientosPorFilaDeBussiness, cantidadFilasFirst, cantidadAsientosPorFilaDeFirst,cantidadDePersonalAbordo, nombre);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAcciones();
        }
        System.out.println("Tipo de avion cargado");
        mostrarMenuAcciones();
    }

    private  void borrarPantalla() {
        // TODO: 9/10/17

        for (int i = 0; i < 100; i++) {
            System.out.println("*");
        }
    }
}