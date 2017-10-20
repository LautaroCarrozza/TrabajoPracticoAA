import java.util.ArrayList;
import java.util.List;

public class TipoDeAvion {

    private int cantidadFilasEconomy;
    private int cantidadAsientosPorFilaEconomy;
    private ArrayList<ArrayList<Asiento>> asientos = new ArrayList<ArrayList<Asiento>>();
    private String nombre;


    public TipoDeAvion(int caantidadFilasEconomy, int cantidadAsientosPorFilaEconomy, String nombre) {
        this.cantidadFilasEconomy = caantidadFilasEconomy;
        this.cantidadAsientosPorFilaEconomy = cantidadAsientosPorFilaEconomy;
        this.asientos = asientos;
        this.nombre = nombre;
    }



    public int getCantidadAsientosPorFilaEconomy() {
        return cantidadAsientosPorFilaEconomy;
    }

    public int getCantidadFilasEconomy() {
        return cantidadFilasEconomy;
    }
}
