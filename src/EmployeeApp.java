import com.sun.xml.internal.bind.v2.TODO;

public class EmployeeApp {



    public static void main(String[] args) {

       server = new ServerMock();
        server.setUpTest();
       iniciarSesion();
    }
    private static ServerInterface server;
    private static int currentSesion, currentClient;
    public static String currentDesde;

    private static void iniciarSesion(){

      try {
      currentSesion = Scanner.getInt("Ingrese su numero de empleado: ");
      server.validarSesionEmpleado(currentSesion);
      mostrarMenu();
      }
      catch (RuntimeException e){
          System.out.println(e.getMessage());
          iniciarSesion();
      }
    }

    private static void mostrarMenu() {
        System.out.println("1- Ingresar tipo de avion");
        System.out.println("2- Ingresar avion");
        System.out.println("3- Ingresar Aeropuerto");
        System.out.println("4- Ingresar vuelo");
        System.out.println("- - - - - - - - - - - - - - - - - -");
        System.out.println("5- VenderPasaje");
        System.out.println("- - - - - - - - - - - - - - - - - -");
        System.out.println("6- Cerrar Sesion");
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
                    venderPasaje();
                    break;
                case 6:
                    iniciarSesion();
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

    private static void venderPasaje() {

        venderACliente();
        venderDesde();

        // TODO: 7/11/17   vendeHasta();
        // TODO: 7/11/17 venderCuando();
        // TODO: 7/11/17venderACuantosPasajeros();
        // TODO: 7/11/17for (int i = 0; i < cantidadPasajeros ; i++) {
        // TODO: 7/11/17// TODO: 7/11/17    venderAsiento();


        // TODO: 7/11/17server.guardarReserva(currentClient, vuelo);


    }

    private static void venderDesde() {
        currentDesde = Scanner.getString("Ingrese lugar de partida: ");
        server.validarLugarDePartida(currentDesde);
    }

    private static void venderACliente() {
        try{
            currentClient = Scanner.getInt("Ingresar el numero del cliente");
            server.validarCliente(currentClient);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            venderACliente();
        }
    }

    private static void ingresarVuelo() {

        try {
            String aeropuertoDeSalida = Scanner.getString("Ingrese el codigo del aeropuerto de salida: ");
            String aeropuertoDeLlegada = Scanner.getString("Ingrese el codigo del aeropuerto de llegada: ");
            int dia = Scanner.getInt("Ingrese el dia de salida del vuelo: ");
            int mes = Scanner.getInt("Ingese el mes de salida del vuelo: ");
            int ano = Scanner.getInt("Ingrese el año de salida del vuelo: ");
            int hours = Scanner.getInt("Ingrese la hora de salida del vuelo: ");
            int minutes = Scanner.getInt("ingrese los minutos de la hora de salida del vuelo: ");
            String plane = Scanner.getString("Ingrese el codigo del avion a utilizar: ");
            int flightCode = Scanner.getInt("Ingrese el codigo del vuelo: ");
            server.addVuelo(aeropuertoDeSalida, aeropuertoDeLlegada, dia, mes, ano, hours, minutes, plane, flightCode);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenu();
        }

        System.out.println("Vuelo cargado");
        mostrarMenu();
    }

    private static void ingresarAeropuerto(){

        try {

            String codigoDeAeropuerto = Scanner.getString("Ingrese el codigo del aeropuerto: ");
            String ubicacion = Scanner.getString("Ingrese la ubicacion del aeropuerto: ");
            String nombre = Scanner.getString("Ingrese el nombre del aeropuerto:");
            server.addAeropuerto(codigoDeAeropuerto, ubicacion, nombre);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenu();
        }
            System.out.println("Aeropuerto cargado");
            mostrarMenu();

    }

    private static void ingresarAvion() {

        String codigoAvion = Scanner.getString("Ingrese el codigo del avion: ");
        String tipoDeAvion = Scanner.getString("Ingrese el tipo de avion para este avion: ");
        try {
            server.addAvion(codigoAvion, tipoDeAvion);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenu();
        }
            System.out.println("Avion cargado");
            mostrarMenu();

    }

    private static void ingresarTipoDeAvion() {

        try {

            int cantidadFilasEconomy = Scanner.getInt("Ingrese la cantidad de filas para la categoria Economy: ");
            int cantidadAsientosPorFilaDeEconomy = Scanner.getInt("Ingrese la cantidad de asientos por fila para la categoria Economy: ");
            int cantidadFilasBussiness = Scanner.getInt("Ingrese la cantidad de filas para la categoria Bussiness: ");
            int cantidadAsientosPorFilaDeBussiness = Scanner.getInt("Ingrese la cantidad de asientos por fila para la categoria Bussiness: ");
            int cantidadFilasFirst = Scanner.getInt("Ingrese la cantidad de filas para la categoria First: ");
            int cantidadAsientosPorFilaDeFirst = Scanner.getInt("Ingrese la cantidad de asientos por fila para la categoria First: ");
            String nombre = Scanner.getString("Ingrese el nombre para el tipo de avion ingresado: ");

            server.addTipoDeAvion(cantidadFilasEconomy, cantidadAsientosPorFilaDeEconomy, cantidadFilasBussiness, cantidadAsientosPorFilaDeBussiness, cantidadFilasFirst, cantidadAsientosPorFilaDeFirst, nombre);
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenu();
        }
            System.out.println("Tipo de avion cargado");
            mostrarMenu();


    }

    private static void borrarPantalla() {
        // TODO: 9/10/17

        for (int i = 0; i < 100; i++) {
            System.out.println("*");
        }
    }
}
