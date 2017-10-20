import java.util.ArrayList;
import java.util.List;

public class Avion {
    private String codigo;
    private TipoDeAvion tipoDeAvion;
    private List<Asiento> asientos = new ArrayList<>();

    public Avion(String codigo, TipoDeAvion tipoDeAvion) {
        this.codigo = codigo;
        this.tipoDeAvion = tipoDeAvion;

        for (int fila = 1; fila <= tipoDeAvion.getCantidadFilasEconomy(); fila++) {

            for (int columna = 1; columna <= tipoDeAvion.getCantidadAsientosPorFilaEconomy(); columna++) {
                Asiento asiento = new Asiento("Economy", fila, columna);
                asientos.add(asiento);
            }


        }



    }

    public List<Asiento> getAsientos() {
        return asientos;
    }
}
