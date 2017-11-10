public class MainApp {

    static ServerInterface server = new ServerMock();

    public static void main(String[] args) {
        server.setUp();
        mostrarMenu();
    }

    public static void mostrarMenu() {
        try {
            System.out.println("    -1 Aplicacion para clientes");
            System.out.println("    -2 Aplicacion para empleados");
            System.out.println("    -3 Aplicacion para tripulacion");
            System.out.println();
            System.out.println("    -4 Exit");
            int app = Scanner.getInt("¿Que aplicacion desea utilizar?");

            switch (app) {
                case 1:
                    ClientApp clientApp = new ClientApp(server);
                    clientApp.menuDeInicio();
                    break;
                case 2:
                    EmployeeApp employeeApp = new EmployeeApp(server);
                    employeeApp.mostrarMenu();
                case 3:
                    PersonalAbordoApp personalAbordoApp = new PersonalAbordoApp(server);
                    personalAbordoApp.iniciarSesion();
                    break;
                case 4:
                    System.exit(0);
                default:
                    throw new RuntimeException("Ingrese una opcion valida");
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }
}
