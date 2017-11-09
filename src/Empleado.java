import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona implements Saveable {
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

    @Override
    public String getSavingFormat() {
        return dni + "," + nombre + "," + codigoEmpleado + "," + habilitadoParaVender + ".";
    }
    public static List<Empleado> build(List<String> elementosStr){
        List<Empleado> elementos = new ArrayList<>();
        for (String elemento :elementosStr ) {
            int corte1 = 0;
            int corte2 = 0;
            int corte3 = 0;

            for (int i = 0; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte1 = i;
                    break;
                }
            }
            for (int i = corte1 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte2 = i;
                    break;
                }
            }
            for (int i = corte2 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte3 = i;
                    break;
                }
            }

            String field1 = elemento.substring(0, corte1);
            String field2 = elemento.substring(corte1 + 1 , corte2);
            String field3 = elemento.substring(corte2+1, corte3);
            String field4 = elemento.substring(corte3+1, elemento.length()-1);

            Empleado empleado= new Empleado(Integer.parseInt(field1),field2,Integer.parseInt(field3),Boolean.parseBoolean(field4));
            elementos.add(empleado);
        }
        return elementos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleado empleado = (Empleado) o;

        if (codigoEmpleado != empleado.codigoEmpleado) return false;
        if (habilitadoParaVender != empleado.habilitadoParaVender) return false;
        return area != null ? area.equals(empleado.area) : empleado.area == null;
    }

    @Override
    public int hashCode() {
        int result = area != null ? area.hashCode() : 0;
        result = 31 * result + codigoEmpleado;
        result = 31 * result + (habilitadoParaVender ? 1 : 0);
        return result;
    }
}
