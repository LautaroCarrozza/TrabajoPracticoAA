public class ClientApp {

    private static int currentCliente;
    static ServerInterface server;

    public static void main(String[] args) {

    server = new ServerMock();
    server.setUpTest();
    iniciarSesion();

    }

    private static void mostrarMenu() {
        System.out.println("1- Ver reservas");
        System.out.println("2- Cerrar sesion");
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
                case 9:
                    System.exit(0);
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        }
        catch (RuntimeException e){
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

    public static void iniciarSesion(){
        System.out.println("Bienvenido a AustralisAirlines: ingrese su numero de Cliente ");
        System.out.println();
        try{
             currentCliente = Scanner.getInt("Numero de cliente: ");
            server.validarSesion(currentCliente);
        }
        catch (RuntimeException e){
            borrarPantalla();
            System.out.println(e.getMessage());
            iniciarSesion();
        }
        borrarPantalla();

        mostrarMenu();
    }

    public static void borrarPantalla() {
        // TODO: 9/10/17

        for (int i = 0; i <100 ; i++) {
            System.out.println("*");
        }
    }
}
