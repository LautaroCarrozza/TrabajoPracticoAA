import sun.applet.Main;

import java.util.List;

public class ClientApp {

    private static int currentCliente;
    private static int intVueloDeseado;
    private static int cantidadDePersonas;
    private static int diaSalida;
    private static int mesSalida;
    private static int anoSalida;
    private static int diaVuelta;
    private static int mesVuelta;
    private static int anoVuelta;
    private static ServerInterface server;
    private static List<Vuelo> vuelosDisponibles, vuelosDisponiblesVuelta;
    private static Vuelo vueloDeseado, vueloDeseadoVuelta;
    private static String currentDesde, currentHasta;

    public ClientApp(ServerInterface server) {
        this.server = server;
    }

    public  void menuDeInicio() {
        System.out.println("1- Iniciar sesion: ");
        System.out.println("2- Registrarse");

        System.out.println("9- Exit");

        int opcion = Scanner.getInt("Seleccione una opcion: ");
        try {
            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registroDeCliente();
                    break;
                case 9:
                    MainApp.mostrarMenu();
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            menuDeInicio();
        }
    }

    private  void mostrarMenu() {
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
                    menuDeBusqueda();
                    break;
                case 9:
                    MainApp.mostrarMenu();
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        } catch (RuntimeException e) {
            borrarPantalla();
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }

    private  void cerrarSesion() {
        borrarPantalla();
        System.out.println("Sesion cerrada");
        iniciarSesion();
    }

    private  void verReservas() {

        List<Reserva> reservas = server.getReservas(currentCliente);

        if (reservas.size() != 0) {
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        } else {
            System.out.println("Aun no tiene ninguna reserva");

        }
        mostrarMenu();
    }

    private  void registroDeCliente() {
        try{
            int dni = Scanner.getInt("Ingresar DNI: ");
            String nombre = Scanner.getString("Ingrese su nombre: ");
            int codigo = Scanner.getInt("Ingrese su numero de cliente: ");
            for (Cliente cliente:server.getClientes()) {
                if(cliente.getNumeroDeCliente() == codigo){
                    throw new RuntimeException("Ya existe un cliente con ese codigo");
                }
            }
            server.addCliente(dni, nombre, codigo);

            menuDeInicio();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            menuDeInicio();
        }

    }

    private  void iniciarSesion() {
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

    private  void borrarPantalla() {
        // TODO: 9/10/17

        for (int i = 0; i < 100; i++) {
            System.out.println("*");
        }
    }

    //Opcion de compra de solo ida o ida y vuelta
    private  void menuDeBusqueda() {
        System.out.println("\n1- Busqueda pasaje solo ida");
        System.out.println("2- Busqueda pasaje ida y vuelta");
        System.out.println("3- Volver al menu\n");

        int opcion = Scanner.getInt("Seleccione una opcion: ");
        try {
            switch (opcion) {
                case 1:
                    buscarSoloIda();
                    break;
                case 2:
                    buscarIdayVuelta();
                    break;
                case 3:
                    mostrarMenu();
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        } catch (RuntimeException e) {
            e.getMessage();
            menuDeBusqueda();
        }
    }

    private  void buscarSoloIda() {

        comprarcuantos();
        comprarDesde();
        comprarHasta();
        comprarCuando();
        validarVuelo();
        menuDeVueloIda();

        for (int i = 0; i < cantidadDePersonas; i++) {
            asientosDisponiblesIda();
        }
        //falta guardar reserva
        System.out.println("Operacion realizada satisfactoriamente");
        mostrarMenu();
    }

    private  void buscarIdayVuelta() {

        comprarcuantos();
        comprarDesde();
        comprarHasta();
        comprarCuando();
        comprarCuandoVuelta();
        validarVueloIdayVuelta();
        menuDeVueloIdayVuelta();

        for (int i = 0; i < cantidadDePersonas; i++) {
            asientosDisponiblesIdayVuelta();
        }

        mostrarMenu();
    }

    private  void asientosDisponiblesIdayVuelta() {
        //asientos ida
        List<Asiento> asientosDisponiblesIda = vueloDeseado.asientosDisponibles();
        System.out.println("Asientos disponibles para el vuelo de Ida: \n");

        for (Asiento asientoIda : asientosDisponiblesIda) {
            System.out.println(asientoIda);
        }
        comprarAsiento(vueloDeseado);

        //Asientos Vuelta
        List<Asiento> asientosDisponiblesVuelta = vueloDeseadoVuelta.asientosDisponibles();
        System.out.println("\nAsientos disponibles para el vuelo de Regreso:\n");
        for (Asiento asientoVuelta : asientosDisponiblesVuelta) {
            System.out.println(asientoVuelta);
        }
        comprarAsiento(vueloDeseadoVuelta);

    }

    private  void menuDeVueloIdayVuelta() {

        System.out.println("1 - Comprar vuelo");
        System.out.println("2 - Volver al menu");
        int opcion = Scanner.getInt("Seleccione una opcion: ");
        try {
            switch (opcion) {
                case 1:
                    seleccionarVueloIdayVuelta();
                    break;
                case 2:
                    mostrarMenu();
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            menuDeVueloIda();
        }
    }

    private  void validarVueloIdayVuelta() {
        try {
            vuelosDisponibles = server.buscarVuelos(diaSalida, mesSalida, anoSalida, currentDesde, currentHasta, cantidadDePersonas);
            //El lugar de salida y llegada intercambiado para la vuelta
            vuelosDisponiblesVuelta = server.buscarVuelos(diaVuelta, mesVuelta, anoVuelta, currentHasta, currentDesde, cantidadDePersonas);
            int opcion = 1;
            System.out.println("Vuelos de ida: \n");
            for (Vuelo v : vuelosDisponibles) {
                System.out.println(opcion + " " + v);
                opcion++;
            }
            System.out.println("\nVuelos de vuelta: \n");
            int opcionvuelta = 1;
            for (Vuelo v : vuelosDisponiblesVuelta) {
                System.out.println(opcionvuelta + " " + v);
                opcionvuelta++;
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }

    //Fecha de Regreso
    private  void comprarCuandoVuelta() {
        diaVuelta = Scanner.getInt("Ingrese el dia de la fecha de su vuelta de viaje: ");
        mesVuelta = Scanner.getInt("Ingrese el mes de la fecha de su vuelta de viaje: ");
        anoVuelta = Scanner.getInt("Ingrese el año de la fecha de su vuelta de viaje: ");
    }

    //Selecciona cantidad de pasajeros
    private  void comprarcuantos() {
        cantidadDePersonas = Scanner.getInt("Ingrese la cantidad de pasajeros: ");
    }

    private  void asientosDisponiblesIda() {
        List<Asiento> asientosDisponibles = vueloDeseado.asientosDisponibles();

        System.out.println("Asientos disponibles: ");
        System.out.println();

        for (Asiento asiento : asientosDisponibles) {
            System.out.println(asiento);
        }
        comprarAsiento(vueloDeseado);
    }

    private  void comprarAsiento(Vuelo vuelo) {
        try {
            int fila = Scanner.getInt("Ingresar fila deseada: ");
            String columna = Scanner.getString("Ingresar columna deseada: ");
            if (!(vuelo.getOcupacion(fila, columna))) {
                server.comprarAsiento(vuelo.getCodigoDeVuelo(), currentCliente, fila, columna, cantidadDePersonas);
                mostrarMenu();
            } else {
                throw new RuntimeException("Seleccion de asiento no disponible");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            comprarAsiento(vuelo);
        }
    }

    //Opcion de compra o volver a menu - la opcion de compra va a seleccionar vuelo.
    private  void menuDeVueloIda() {
        System.out.println();
        System.out.println("1 - Comprar vuelo");
        System.out.println("2 - Volver al menu");
        int opcion = Scanner.getInt("Seleccione una opcion: ");
        try {
            switch (opcion) {
                case 1:
                    seleccionarVuelo();
                    break;
                case 2:
                    mostrarMenu();
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            menuDeVueloIda();
        }
    }

    private  void seleccionarVueloIdayVuelta() {
        try {
            intVueloDeseado = Scanner.getInt("Seleccione su vuelo de Ida: ") - 1;/// -1 por que los vuelos empiezan con 0 y se los imprime con un +1
            int intVueloDeseadoVuelta = Scanner.getInt("Seleccione su vuelo de Regreso: ") - 1;
            vueloDeseado = vuelosDisponibles.get(intVueloDeseado); // Selecciona el vuelo elegido dentro de la lista de posibles vuelos segun los criterios de busqueda
            vueloDeseadoVuelta = vuelosDisponiblesVuelta.get(intVueloDeseadoVuelta);

        } catch (Exception e) {
            System.out.println("Opcion invalida");
            menuDeVueloIda();
        }
    }

    //CLiente selecciona el vuelo que desee mostrados en la consola
    private  void seleccionarVuelo() {
        try {
            intVueloDeseado = Scanner.getInt("¿Que vuelo desea comprar? : ") - 1;/// -1 por que los vuelos empiezan con 0 y se los imprime con un +1
            vueloDeseado = vuelosDisponibles.get(intVueloDeseado); // Selecciona el vuelo elegido dentro de la lista de posibles vuelos segun los criterios de busqueda
        } catch (Exception e) {
            System.out.println("Opcion invalida");
            menuDeVueloIda();
        }
    }

    //Lista de vuelos que matchean con los criterios de busqueda y los imprime en pantalla
    private  void validarVuelo() {
        try {
            vuelosDisponibles = server.buscarVuelos(diaSalida, mesSalida, anoSalida, currentDesde, currentHasta, cantidadDePersonas);
            int opcion = 1;
            for (Vuelo v : vuelosDisponibles) {
                System.out.println(opcion + " " + v);
                opcion++;
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }

    //Seleccion de fecha
    private  void comprarCuando() {
        diaSalida = Scanner.getInt("Ingrese el dia de la fecha del viaje: ");
        mesSalida = Scanner.getInt("Ingrese el mes de la fecha del viaje: ");
        anoSalida = Scanner.getInt("Ingrese el año de la fecha del viaje: ");
    }

    //Seleccion y validacion de Regreso
    private  void comprarHasta() {
        try {
            currentHasta = Scanner.getString("Ingrese el lugar de llegada: ");
            server.validarLugarDeLlegada(currentHasta);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            comprarHasta();
        }
    }

    //Seleccion y validacion de Salida
    private  void comprarDesde() {
        try {
            currentDesde = Scanner.getString("Ingrese el lugar de partida: ");
            server.validarLugarDePartida(currentDesde);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            comprarDesde();
        }
    }
}