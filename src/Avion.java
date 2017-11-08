import java.util.ArrayList;
import java.util.List;

public class Avion {
    private String codigo;
    private TipoDeAvion tipoDeAvion;
    private List<Asiento> asientos = new ArrayList<>();

    public TipoDeAvion getTipoDeAvion() {
        return tipoDeAvion;
    }

    public Avion(String codigo, TipoDeAvion tipoDeAvion) {
        this.codigo = codigo;
        this.tipoDeAvion = tipoDeAvion;

        for (int fila = 1; fila <= tipoDeAvion.getCantidadFilasEconomy(); fila++) {

            for (int columna = 1; columna <= tipoDeAvion.getCantidadAsientosPorFilaEconomy(); columna++) {
                Asiento asiento = new Asiento("Economy", fila + tipoDeAvion.getCantidadFilasBussiness() + tipoDeAvion.getCantidadFilasBussiness(), columna);
                asientos.add(asiento);
            }
        }

        for (int fila = 1; fila <= tipoDeAvion.getCantidadFilasBussiness(); fila++) {

            for (int columna = 1; columna <= tipoDeAvion.getCantidadAsientosPorFilaBussiness(); columna++) {
                Asiento asiento = new Asiento("Bussiness", fila + tipoDeAvion.getCantidadFilasFirst() , columna);
                asientos.add(asiento);
            }
        }

        for (int fila = 1; fila <= tipoDeAvion.getCantidadFilasFirst(); fila++) {

            for (int columna = 1; columna <= tipoDeAvion.getCantidadDeAsientosPorFilaFirst(); columna++) {
                Asiento asiento = new Asiento("First", fila, columna);
                asientos.add(asiento);
            }
        }
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }
    public String getCodigo() {
        return codigo;
    }
}
