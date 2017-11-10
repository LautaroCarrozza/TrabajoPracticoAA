import sun.applet.Main;

import java.util.ArrayList;
import java.util.Comparator;
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
    private static int currentMaxEscalas;
    private static ServerInterface server;
    private static List<Vuelo> vuelosDisponibles, vuelosDisponiblesVuelta;
    private static ArrayList<ArrayList<Vuelo>> vuelosCombinados;
    private static Vuelo vueloDeseado, vueloDeseadoVuelta;
    private static String currentDesde, currentHasta, currentCategoria;
    public static ArrayList<Vuelo> combinacionDeVuelos;

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
                System.out.println("Reserva para " + r.getCliente().getNumeroDeCliente());
                System.out.println("Desde: " + r.getPasajes().get(0).getVuelo().getUbicacionSalida());
                System.out.println("Hasta: " + r.getPasajes().get(0).getVuelo().getUbicacionLlegada());
                System.out.println("Fecha de salida (año/mes/dia): " + r.getPasajes().get(0).getVuelo().getFechaSalida());
                for (Pasaje pasaje:r.getPasajes()) {
                    System.out.println(pasaje.toString() + " Precio: $" + server.getPreciodeTarifa(pasaje.getVuelo().getCodigoDeVuelo(), pasaje.getAsiento().getCategoria()));
                }

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
            String codigoString = "" + dni;
            int codigo = Integer.parseInt(codigoString.substring(codigoString.length()-3));
            System.out.println("Su codigo de cliente es:" + codigo);
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

        comprarCategoria();
        comprarcuantos();
        comprarDesde();
        comprarHasta();
        comprarCuando();
        menudeEscalas();
        validarVuelo();
        menuDeVueloIda();

        if (currentMaxEscalas == 0) {
            for (int i = 0; i < cantidadDePersonas; i++) {
                asientosDisponiblesIda();
            }

        }
        else {
            for (int i = 0; i < cantidadDePersonas; i++) {
                int counter = 1;
                for (Vuelo vuelo:combinacionDeVuelos) {
                    System.out.println("\nPasajero " + (i + 1));
                    System.out.println("Vuelo " + counter +"\n");
                    vueloDeseado = vuelo;
                    asientosDisponiblesIda();
                    counter ++;
                }
            }
        }
        System.out.println("Operacion realizada satisfactoriamente");
        mostrarMenu();
    }

    private void menudeEscalas() {
        currentMaxEscalas = Scanner.getInt("Ingrese la cantidad maxima de escalas: ");
    }

    private void comprarCategoria() {
        System.out.println("1- Economy");
        System.out.println("2- Bussiness");
        System.out.println("3- First");
        try {
            int option = Scanner.getInt("Igrese la categoria para su(s) pasajes: ");
            switch (option) {
                case 1:
                    currentCategoria = "Economy";break;
                case 2:
                    currentCategoria = "Bussiness";break;
                case 3:
                    currentCategoria = "First";break;
                default:
                    throw new RuntimeException("Categoria invalida");
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            comprarCategoria();
        }
    }

    private  void buscarIdayVuelta() {

        comprarCategoria();
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
        System.out.println("La compra se realizo exitosamente");
        mostrarMenu();
    }

    private  void asientosDisponiblesIdayVuelta() {
        //asientos ida
        List<Asiento> asientosDisponiblesIda = vueloDeseado.asientosDisponibles();
        System.out.println("Asientos disponibles para el vuelo de Ida: \n");

        for (Asiento asientoIda : asientosDisponiblesIda) {
            if (asientoIda.getCategoria().equals(currentCategoria)) {
                System.out.println(asientoIda);
            }
        }

        comprarAsiento(vueloDeseado);

        //Asientos Vuelta
        List<Asiento> asientosDisponiblesVuelta = vueloDeseadoVuelta.asientosDisponibles();
        System.out.println("\nAsientos disponibles para el vuelo de Regreso:\n");
        for (Asiento asientoVuelta : asientosDisponiblesVuelta) {
            if (asientoVuelta.getCategoria().equals(currentCategoria)) {
                System.out.println(asientoVuelta);
            }
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

            menuSort();

            vuelosDisponibles = server.buscarVuelos(diaSalida, mesSalida, anoSalida, currentDesde, currentHasta, cantidadDePersonas);
            //El lugar de salida y llegada intercambiado para la vuelta
            vuelosDisponiblesVuelta = server.buscarVuelos(diaVuelta, mesVuelta, anoVuelta, currentHasta, currentDesde, cantidadDePersonas);
            int opcion = 1;
            System.out.println("Vuelos de ida: \n");
            for (Vuelo v : vuelosDisponibles) {
                System.out.println(opcion + " " + v);
                opcion++;
            }

            menuSort();

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

        asientosDisponibles.sort(new Comparator<Asiento>() {
            @Override
            public int compare(Asiento o1, Asiento o2) {
                return  o1.getFila() - o2.getFila();
            }
        });

        System.out.println("Asientos disponibles: ");
        System.out.println();

        for (Asiento asiento : asientosDisponibles) {
            if (asiento.getCategoria().equals(currentCategoria)) {
                System.out.println(asiento + " Precio: $ " + server.getPreciodeTarifa(vueloDeseado.getCodigoDeVuelo(), asiento.getCategoria()));
            }
        }
        comprarAsiento(vueloDeseado);
    }

    private  void comprarAsiento(Vuelo vuelo) {
        try {
            int fila = Scanner.getInt("Ingresar fila deseada: ");
            String columna = Scanner.getString("Ingresar columna deseada: ");
            if (!(vuelo.getOcupacion(fila, columna))) {
                String nombre = Scanner.getString("Ingrese el nombre del pasajero: ");
                int dni = Scanner.getInt("Ingrese el DNI del pasajero: ");
                server.comprarAsiento(vuelo.getCodigoDeVuelo(), currentCliente, fila, columna, cantidadDePersonas, nombre, dni);
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
           if (currentMaxEscalas == 0) {
               vueloDeseado = vuelosDisponibles.get(intVueloDeseado); // Selecciona el vuelo elegido dentro de la lista de posibles vuelos segun los criterios de busqueda
           }
           else {
               combinacionDeVuelos = vuelosCombinados.get(intVueloDeseado);
           }
        }
        catch (Exception e) {
            System.out.println("Opcion invalida");
            menuDeVueloIda();
        }
    }

    //Lista de vuelos que matchean con los criterios de busqueda y los imprime en pantalla
    private  void validarVuelo() {
        try {
            if (currentMaxEscalas == 0) {
                vuelosDisponibles = server.buscarVuelos(diaSalida, mesSalida, anoSalida, currentDesde, currentHasta, cantidadDePersonas);

                menuSort();
                int opcion = 1;
                for (Vuelo v : vuelosDisponibles) {
                    System.out.println(opcion + " " + v);
                    opcion++;
                }
            }
            else {
                vuelosCombinados = server.buscarVuelosconEscala(diaSalida, mesSalida, anoSalida, currentDesde, currentHasta, cantidadDePersonas);

                for (int i = 0; i < vuelosCombinados.size(); i++) {
                    System.out.print((i+1)+"- ");
                    for (Vuelo vuelo:vuelosCombinados.get(i)) {
                        System.out.print(vuelo.toString() +", ");
                    }
                }
            }
        }
         catch(RuntimeException e) {
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

    private void menuSort(){
        System.out.println("1-Precio");
        System.out.println("2-Escalas");
        System.out.println("3-Duracion");

        int sortOption = Scanner.getInt("Ingrese criterio para ordenar los resultados de su busqueda");

        switch (sortOption){
            case 1: server.sort(vuelosDisponibles, "Precio");break;
            case 2: server.sort(vuelosDisponibles, "Escalas");break;
            case 3: server.sort(vuelosDisponibles, "Duracion");break;
            default:
                System.out.println("Categoria invalida");
                menuSort();
        }
    }
}