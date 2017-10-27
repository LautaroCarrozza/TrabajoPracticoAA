import java.util.Calendar;
import java.util.List;

public class ClientApp {

    private static int currentCliente;
    private static ServerInterface server;
    private static  List<Vuelo> posiblesVuelos;
    private static String category = "";
    private static int vueloDeseado;

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
            server.validarSesion(currentCliente);
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
            option = Scanner.getInt("Ingrese la categoria para su pasaje: ");
            switch (option) {
                case 1:
                    category = "Economy";
                    break;
                case 2:
                    category = "Bussines";
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
        int cantidadDePersonas = Scanner.getInt("Ingrese la cantidad de pasajeros: ");
        System.out.println();
        System.out.println();

        posiblesVuelos = server.buscarVuelos(dia, mes, ano, lugarDeSalida, lugarDeLlegada, cantidadDePersonas, category);
        if(!posiblesVuelos.equals(null)) {
            for (int i = 0; i < posiblesVuelos.size(); i++) {
                System.out.println(i + "- " + posiblesVuelos.get(i));
            }
        }

        System.out.println();
        System.out.println();

        System.out.println("1- Comprar vuelo: (numero del vuelo en la lista) ");
        System.out.println("9- Volver al menu");

        boolean ok = false;
        while (!ok) {
            int option = Scanner.getInt("Ingrese su opcion: ");
            switch (option) {
                case 1:
                    comprarPasaje();
                    ok = true;
                    break;
                case 9:
                    ok = true;
                    borrarPantalla();
                    mostrarMenu();
                default:
                    System.out.println("Opcion invalida");
            }
        }

    }

    private static void comprarPasaje() {

        try {
             vueloDeseado = Scanner.getInt("¿Que vuelo desea comprar? : ");
            System.out.println(posiblesVuelos.get(vueloDeseado).asientosDisponibles(category));


        int fila = Scanner.getInt("¿En que fila desea viajar?: ");
        char columna = Scanner.getChar("¿En que asiento de la fila desea viajar?: ");

        server.comprarPasaje(posiblesVuelos.get(vueloDeseado).getCodigoDeVuelo(), currentCliente, fila, columna);

        System.out.println("La compra se realizo exitosamente");
        category = "";
        posiblesVuelos = null;
        mostrarMenu();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            comprarPasaje();
        }


    }
}
