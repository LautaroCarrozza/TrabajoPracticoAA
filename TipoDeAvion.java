import java.util.ArrayList;
import java.util.List;

public class TipoDeAvion {

    int cantidadFilasEconomy;
    int cantidadAsientosPorFilaEconomy;


    public TipoDeAvion(int caantidadFilasEconomy, int cantidadAsientosPorFilaEconomy, String nombre) {
        this.cantidadFilasEconomy = caantidadFilasEconomy;
        this.cantidadAsientosPorFilaEconomy = cantidadAsientosPorFilaEconomy;
        this.asientos = asientos;
        this.nombre = nombre;
    }

    ArrayList<ArrayList<Asiento>> asientos = new ArrayList<ArrayList<Asiento>>();
    String nombre;

    public int getCantidadAsientosPorFilaEconomy() {
        return cantidadAsientosPorFilaEconomy;
    }

    public int getCantidadFilasEconomy() {
        return cantidadFilasEconomy;
    }
}
