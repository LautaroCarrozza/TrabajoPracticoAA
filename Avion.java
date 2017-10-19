import java.util.ArrayList;
import java.util.List;

public class Avion {
    String codigo;
    TipoDeAvion tipoDeAvion;
    List<Asiento> asientos = new ArrayList<>();

    public Avion(String codigo, TipoDeAvion tipoDeAvion) {
        this.codigo = codigo;
        this.tipoDeAvion = tipoDeAvion;

        for (int i = 1; i < tipoDeAvion.getCantidadFilasEconomy(); i++) {
            for (int j = 1; j < tipoDeAvion.getCantidadAsientosPorFilaEconomy(); j++) {
                Asiento asiento = new Asiento("Economy", i, j);
                asientos.add(asiento);
            }
        }


    }
}
