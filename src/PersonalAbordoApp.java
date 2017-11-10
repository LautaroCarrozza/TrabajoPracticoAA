public class PersonalAbordoApp {
    private  ServerInterface server;
    private  int currentSesion;

    public PersonalAbordoApp(ServerInterface server) {
        this.server= server;
    }

    public void iniciarSesion() {
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

    private void mostrarMenu() {
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
                    MainApp.mostrarMenu();
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }

    private void verItinerarioDeViajes() {
        System.out.println(server.getPersonalAbordo(currentSesion).toString());
        mostrarMenu();
    }

    private void cerrarSesion() {
        borrarPantalla();
        System.out.println("Sesion cerrada");
        iniciarSesion();
    }

    public  void borrarPantalla() {
        // TODO: 9/10/17

        for (int i = 0; i < 100; i++) {
            System.out.println("*");
        }
    }
}
