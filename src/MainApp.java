public class MainApp {

    public static void main(String[] args) {
        mostrarMenu();

    }

    private static void mostrarMenu() {
        try {
            ServerInterface server = new ServerMock();
            System.out.println("    -1 Aplicacion para clientes");
            System.out.println("    -2 Aplicacion para empleados");
            System.out.println("    -3 Aplicacion para tripulacion");
            System.out.println();
            System.out.println("    -4 Exit");
            int app = Scanner.getInt("¿Que aplicacion desea utilizar?");


            switch (app) {
                case 1:
                    ClientApp clientApp = new ClientApp(server);
                    clientApp.menuDeinicio();
                    break;
                case 2:
                    EmployeeApp employeeApp = new EmployeeApp(server);
                    employeeApp.mostrarMenu();
                case 3:
                    PersonalAbordoApp personalAbordoApp = new PersonalAbordoApp(server);
                    employeeApp.mostrarMenu();
                    break;
                case 4:
                    System.exit(0);
                default:
                    throw new RuntimeException("Ingrese una opcion valida");
                    mostrarMenu();
            }
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            mostrarMenu();
        }
    }
}
