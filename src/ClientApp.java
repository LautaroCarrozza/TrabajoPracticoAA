import java.util.ArrayList;
import java.util.List;

public class ClientApp {

    private static int currentCliente;
    private static ServerInterface server;
    private static List<Vuelo> posiblesVuelos;
    private static String category = "";
    private static int intVueloDeseado;
    private static int cantidadDePersonas;
    private static Vuelo vueloDeseado;
    private static List<Asiento> asientosSeleccionados = new ArrayList<Asiento>();

    public static void main(String[] args) {
        server = new ServerMock();
        server.setUpTest();
        iniciarSesion();
    }

    private static void mostrarMenu() {
        System.out.println("1- Ver reservas");
        System.out.println("2- Cerrar sesion");
        System.out.println("3- Buscar Vuelo");
        System.out.println("- - - - - - - - - - - - - - - - - -");
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

        System.out.println(server.printReservas(currentCliente));
        mostrarMenu();
    }

    public static void iniciarSesion() {
        System.out.println("Bienvenido a AustralisAirlines: ingrese su numero de Cliente ");
        System.out.println();
        try {
            currentCliente = Scanner.getInt("Numero de cliente: ");
            server.validarSesionCliente(currentCliente);
        } catch (RuntimeException e) {
            borrarPantalla();
            System.out.println(e.getMessage());
            iniciarSesion();
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
        int dia = Scanner.getInt("Ingrese el dia de la fecha del viaje: ");
        int mes = Scanner.getInt("Ingrese el mes de la fecha del viaje: ");
        int ano = Scanner.getInt("Ingrese el año de la fecha del viaje: ");

        System.out.println("1- Economy");
        System.out.println("2- Bussiness");
        System.out.println("3- First");
        System.out.println("9- Volver al menu");

        while (category.isEmpty()) {
            int option = 0;
            option = Scanner.getInt("Ingrese la categoria para su(s) pasaje: ");
            switch (option) {
                case 1:
                    category = "Economy";
                    break;
                case 2:
                    category = "Bussiness";
                    break;
                case 3:
                    category = "First";
                    break;
                case 9:
                    borrarPantalla();
                    mostrarMenu();
                default:
                    System.out.println("Categoria invalida");
            }
        }

        String lugarDeSalida = Scanner.getString("Ingrese el lugar de partida: ");
        String lugarDeLlegada = Scanner.getString("Ingrese el lugar de llegada: ");
        cantidadDePersonas = Scanner.getInt("Ingrese la cantidad de pasajeros: ");
        System.out.println();
        System.out.println();
        posiblesVuelos = server.buscarVuelos(dia, mes, ano, lugarDeSalida, lugarDeLlegada, cantidadDePersonas, category);

            for (int i = 0; i < posiblesVuelos.size(); i++) {
                System.out.println((i+1) + "- " + posiblesVuelos.get(i));
            }///imprimo la lista de buelos disponibles con esas especificaciones.

        System.out.println();
        System.out.println();

        try{
            mostrarMenuCompraPasaje();
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuCompraPasaje();
        }
    }

    private static void mostrarMenuCompraPasaje(){
        System.out.println("1- Comprar pasaje: (numero del vuelo en la lista) ");
        System.out.println("9- Volver al menu");
        int option = Scanner.getInt("Ingrese su opcion: ");
        switch (option) {
            case 1:
                comprarPasajes();
                break;
            case 9:
                category = "";
                posiblesVuelos = null;
                borrarPantalla();
                mostrarMenu();
            default:
                throw new RuntimeException("Opcion invalida");
        }
    }

    private static void comprarPasajes() {

        try {
            intVueloDeseado = Scanner.getInt("¿Que vuelo desea comprar? : ") -1 ;/// -1 por que los vuelos empiezan con 0 y se los imprime con un +1
            vueloDeseado = posiblesVuelos.get(intVueloDeseado);
            List<Asiento> asientosDisponibles = vueloDeseado.asientosDisponibles(category);

            System.out.println("Asientos disponibles: ");
            System.out.println();
            int count = 1;
            for (Asiento asiento : asientosDisponibles) {
                System.out.println( count + " " + asiento);
                count ++;
            }


            for (int i = 0; i < cantidadDePersonas; i++) {
                Asiento asiento = seleccionarAsiento();
                server.comprarAsiento(vueloDeseado.getCodigoDeVuelo(), currentCliente, asiento ,cantidadDePersonas , category);
            }

            server.guardarReserva(currentCliente, vueloDeseado);

            System.out.println("La compra se realizo exitosamente");
            category = "";
            posiblesVuelos = null;
            mostrarMenu();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            comprarPasajes();
        }
    }

    private static Asiento seleccionarAsiento(){

        try {
            int fila = Scanner.getInt("¿En que fila desea viajar?: ");
            char columna = Scanner.getChar("¿En que asiento de la fila desea viajar?: ");
             if( vueloDeseado.getAsiento(fila, columna).getCategoria().equals(category) && !vueloDeseado.getOcupacion(vueloDeseado.getAsiento(fila, columna))){
                 return vueloDeseado.getAsiento(fila, columna);
             }

            throw new RuntimeException("Seleccion de asiento invalida");
        }

        catch (RuntimeException e){
            System.out.println(e.getMessage());
            return seleccionarAsiento();
        }
    }
}
