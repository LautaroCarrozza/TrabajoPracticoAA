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
        mostrarMenuAdentro();
    }
    private void mostrarMenuAfuera(){
        System.out.println("1- Iniciar Sesion");
        System.out.println("2-Exit");


        try{
            int opcion = Scanner.getInt("Seleccione una opcion: ");
            switch (opcion){
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    MainApp.mostrarMenu();
                    break;
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenuAfuera();
        }

    }
    private void mostrarMenuAdentro() {
        System.out.println("1- Ver itinerario de viajes");
        System.out.println("2- Cerrar sesion");
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
                default:
                    throw new RuntimeException("Opcion invalida");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            mostrarMenuAdentro();
        }
    }

    private void verItinerarioDeViajes() {
        System.out.println(server.getPersonalAbordo(currentSesion).toString());
        mostrarMenuAdentro();
    }

    private void cerrarSesion() {
        System.out.println("Sesion cerrada");
        mostrarMenuAfuera();
    }

    public  void borrarPantalla() {
        // TODO: 9/10/17

        for (int i = 0; i < 100; i++) {
            System.out.println("*");
        }
    }
}
