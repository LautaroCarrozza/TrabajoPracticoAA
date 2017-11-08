import java.util.ArrayList;
import java.util.List;

public class TipoDeAvion {

    private int cantidadFilasEconomy;
    private int cantidadAsientosPorFilaEconomy;
    private int cantidadFilasBussiness;
    private int cantidadAsientosPorFilaBussiness;
    private int cantidadFilasFirst;
    private int cantidadDeAsientosPorFilaFirst;
    private ArrayList<ArrayList<Asiento>> asientos = new ArrayList<ArrayList<Asiento>>();
    private String nombre;
    private int cantidadDePersonalAbordo;

    public TipoDeAvion(int cantidadFilasEconomy, int cantidadAsientosPorFilaEconomy, int cantidadFilasBussiness, int cantidadAsientosPorFilaBussiness, int cantidadFilasFirst, int cantidadDeAsientosPorFilaFirst, int cantidadDePersonalAbordo, String nombre) {
        this.cantidadFilasEconomy = cantidadFilasEconomy;
        this.cantidadAsientosPorFilaEconomy = cantidadAsientosPorFilaEconomy;
        this.cantidadFilasBussiness = cantidadFilasBussiness;
        this.cantidadAsientosPorFilaBussiness = cantidadAsientosPorFilaBussiness;
        this.cantidadFilasFirst = cantidadFilasFirst;
        this.cantidadDeAsientosPorFilaFirst = cantidadDeAsientosPorFilaFirst;
        this.cantidadDePersonalAbordo = cantidadDePersonalAbordo;
        this.nombre = nombre;
    }


    public int getCantidadFilasBussiness() {
        return cantidadFilasBussiness;
    }

    public int getCantidadAsientosPorFilaBussiness() {
        return cantidadAsientosPorFilaBussiness;
    }

    public int getCantidadFilasFirst() {
        return cantidadFilasFirst;
    }

    public int getCantidadDeAsientosPorFilaFirst() {
        return cantidadDeAsientosPorFilaFirst;
    }


    public int getCantidadAsientosPorFilaEconomy() {
        return cantidadAsientosPorFilaEconomy;
    }

    public int getCantidadFilasEconomy() {
        return cantidadFilasEconomy;
    }

    public String getNombre() {
        return nombre;
    }
}
