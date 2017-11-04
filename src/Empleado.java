public class Empleado extends Persona {
    private AreaAdministrativa area;

    private int codigoEmpleado;
    private boolean habilitadoParaVender;

    public Empleado(int dni, String nombre, int codigoEmpleado, boolean habilitadoParaVender) {
        super(dni, nombre);
        this.codigoEmpleado = codigoEmpleado;
        this.habilitadoParaVender = habilitadoParaVender;
    }

    public AreaAdministrativa getArea() {
        return area;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public boolean isHabilitadoParaVender() {
        return habilitadoParaVender;
    }
}
