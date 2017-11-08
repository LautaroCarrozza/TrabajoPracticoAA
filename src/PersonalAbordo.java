import java.util.ArrayList;
import java.util.List;

public class PersonalAbordo extends Persona {
    private String cargo;
    private List<Vuelo> vuelos = new ArrayList<>();
    int numeroDeEmpleado;

    public String getCargo() {
        return cargo;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public int getNumeroDeEmpleado() {
        return numeroDeEmpleado;
    }

    public PersonalAbordo(int dni, String nombre, String cargo, int numeroDeEmpleado) {
        super(dni, nombre);
        this.cargo = cargo;
        this.numeroDeEmpleado = numeroDeEmpleado;
    }

    @Override
    public String toString() {
        String result = "Nombre: " + getNombre() + "\n" + "Vuelos: ";
        for (Vuelo v:vuelos) {
            result = result + "\t" + v.getCodigoDeVuelo();
        }
        return result;
    }
}
