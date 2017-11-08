public class PersonalAbordoApp {
    private static ServerInterface server;
    private static int currentSesion;

    public static void main(String[] args) {
        server = new ServerMock();
        server.setUp();
        iniciarSesion();
    }

    private static void iniciarSesion() {
        try {
            currentSesion = Scanner.getInt("Ingese el numero de empleado: ");
            server.validarSesionEmpleadoAbordo(currentSesion);
        }
        catch (RuntimeException e){
            borrarPantalla();
            System.out.println(e.getMessage());
            iniciarSesion();
        }
        borrarPantalla();
        mostrarMenu();
    }

    private static void mostrarMenu() {
        System.out.println("1- Ver itinerario de viajes");
        System.out.println("2- Cerrar sesion");
        System.out.println("- - - - - - - - - - - - - - - - - -");
        System.out.println("9- Exit");
        System.out.println();
        try {
            int opcion = Scanner.getInt("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    verItinerarioDeViajes();
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
        } catch (RuntimeException e) {
            borrarPantalla();
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }

    private static void verItinerarioDeViajes() {
        System.out.println(server.getPersonalAbordo(currentSesion).toString());
    }

    private static void cerrarSesion() {
        borrarPantalla();
        System.out.println("Sesion cerrada");
        iniciarSesion();
    }

    public static void borrarPantalla() {
        // TODO: 9/10/17

        for (int i = 0; i < 100; i++) {
            System.out.println("*");
        }
    }
}
