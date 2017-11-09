import java.util.List;

public class ClientApp {

    private static int currentCliente, intVueloDeseado, cantidadDePersonas, diaSalida, mesSalida, anoSalida;
    private static ServerInterface server;
    private static List<Vuelo> vuelosDisponibles;
    private static Vuelo vueloDeseado;
    private static String currentDesde, currentHasta;

    public static void main(String[] args) {
        server = new ServerMock();
        server.setUp();
        menuDeInicio();
    }

    private static void menuDeInicio() {
        System.out.println("1- Iniciar sesion: ");
        System.out.println("2- Registrarse");

        int opcion = Scanner.getInt("Seleccione una opcion: ");
        try {
        switch (opcion) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                registroDeCliente();
                break;
            default:
                throw new RuntimeException("Opcion invalida");
        }

        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            menuDeInicio();
        }
    }

    private static void mostrarMenu() {
        System.out.println("1- Ver reservas");
        System.out.println("2- Cerrar sesion");
        System.out.println("3- Buscar Vuelo");
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("9- Exit");
        System.out.println();

        try {
            int opcion = Scanner.getInt("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    verReservas();
                    break;
                case 2:
                    cerrarSesion();
                    break;
                case 3:
                    buscarVuelo();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        } catch (RuntimeException e) {
            borrarPantalla();
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }

    private static void cerrarSesion() {
        borrarPantalla();
        System.out.println("Sesion cerrada");
        iniciarSesion();
    }

    private static void verReservas() {

        List<Reserva> reservas = server.getReservas(currentCliente);

        if (reservas.size()!= 0) {
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        }
        else {
            System.out.println("Aun no tiene ninguna reserva");

        }
        mostrarMenu();
    }

    private static void registroDeCliente(){
        int dni = Scanner.getInt("Ingresar DNI: ");
        String nombre = Scanner.getString("Ingrese su nombre: ");
        int codigo = Scanner.getInt("Ingrese su numero de cliente: ");
        server.addCliente(dni, nombre, codigo);
        borrarPantalla();
        menuDeInicio();
    }

    public static void iniciarSesion() {
        System.out.println("\nBienvenido a AustralisAirlines");
        System.out.println();

        try {
            currentCliente = Scanner.getInt("Ingrese su numero de cliente: ");
            server.validarSesionCliente(currentCliente);

        } catch (RuntimeException e) {
            borrarPantalla();
            System.out.println(e.getMessage());
            menuDeInicio();
        }
        borrarPantalla();
        mostrarMenu();
    }

    public static void borrarPantalla() {
        // TODO: 9/10/17

        for (int i = 0; i < 100; i++) {
            System.out.println("*");
        }
    }

    public static void buscarVuelo() {

        menuDeCompra();
        comprarcuantos();
        comprarDesde();
        comprarHasta();
        comprarCuando();
        validarVuelo();
        menuDeVuelo();

        for (int i = 0; i < cantidadDePersonas; i++) {
            comprarAsiento();
        }
        server.guardarReserva(currentCliente, vueloDeseado);
        mostrarMenu();
    }

    //Opcion de compra de solo ida o ida y vuelta
    private static void menuDeCompra() {
    }

    //Selecciona cantidad de pasajeros
    private static void comprarcuantos() {
        cantidadDePersonas = Scanner.getInt("Ingrese la cantidad de pasajeros: ");
    }

    private static void comprarAsiento() {
        List<Asiento> asientosDisponibles = vueloDeseado.asientosDisponibles();

        System.out.println("Asientos disponibles: ");
        System.out.println();

        for (Asiento asiento : asientosDisponibles) {
            System.out.println(asiento);
        }
        try {
            int fila = Scanner.getInt("Ingresar fila deseada: ");
            char columna = Scanner.getChar("Ingresar columna deseada: ");
            if( !(vueloDeseado.getOcupacion(vueloDeseado.getAsiento(fila, columna)))){
                server.comprarAsiento(vueloDeseado.getCodigoDeVuelo(), currentCliente,vueloDeseado.getAsiento(fila, columna), cantidadDePersonas );
                return;
            }

            else {
                throw new RuntimeException("Seleccion de asiento no disponible");
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            comprarAsiento();
        }
    }

    //Opcion de compra o volver a menu - la opcion de compra va a seleccionar vuelo.
    private static void menuDeVuelo (){
        System.out.println();
        System.out.println("1 - Comprar vuelo");
        System.out.println("2 - Volver al menu");
        int opcion = Scanner.getInt("Seleccione una opcion: ");
        try {
            switch (opcion){
                case 1: seleccionarVuelo();
                break;
                case 2: mostrarMenu();
                break;
                default: throw new RuntimeException("Opcion invalida");
            }
        }
        catch (RuntimeException e){
                System.out.println(e.getMessage());
                menuDeVuelo();
        }

    }

    //CLiente selecciona el vuelo que desee mostrados en la consola
    private static void seleccionarVuelo() {
        try {
            int intVueloDeseado = Scanner.getInt("¿Que vuelo desea comprar? : ") - 1;/// -1 por que los vuelos empiezan con 0 y se los imprime con un +1
            vueloDeseado = vuelosDisponibles.get(intVueloDeseado); // Selecciona el vuelo elegido dentro de la lista de posibles vuelos segun los criterios de busqueda
        } catch (Exception e) {
            System.out.println("Opcion invalida");
            menuDeVuelo();
        }
    }

    //Lista de vuelos que matchean con los criterios de busqueda y los imprime en pantalla
    private static void validarVuelo() {
        try{
            vuelosDisponibles = server.buscarVuelos(diaSalida, mesSalida, anoSalida,currentDesde,currentHasta, cantidadDePersonas);
            int opcion = 1;
            for (Vuelo v: vuelosDisponibles) {
                System.out.println(opcion +" "+ v);
                opcion++;
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }

    //Seleccion de fecha
    private static void comprarCuando() {
        diaSalida = Scanner.getInt("Ingrese el dia de la fecha del viaje: ");
        mesSalida = Scanner.getInt("Ingrese el mes de la fecha del viaje: ");
        anoSalida = Scanner.getInt("Ingrese el año de la fecha del viaje: ");
    }

    //Seleccion y validacion de Regreso
    private static void comprarHasta() {
        try {
            currentHasta = Scanner.getString("Ingrese el lugar de llegada: ");
            server.validarLugarDeLlegada(currentHasta);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            comprarHasta();
        }
    }

    //Seleccion y validacion de Salida
    private static void comprarDesde() {
        try {
            currentDesde = Scanner.getString("Ingrese el lugar de partida: ");
            server.validarLugarDePartida(currentDesde);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            comprarDesde();
        }
    }

}