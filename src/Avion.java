import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Avion implements Saveable{
    private String codigo;
    private TipoDeAvion tipoDeAvion;
    private List<Asiento> asientos = new ArrayList<>();
    private Map <LocalDate, Boolean> disponibilidad = new HashMap<>();
    private List<Vuelo> vuelos = new ArrayList<>();

    public void confirmarDisponibilidad(LocalDate dia) {
        if(disponibilidad.get(dia)){
            return;
        }
        throw new RuntimeException("El avion no esta disponible ese dia");
    }

    public void agregarVuelo(Vuelo vuelo){
        vuelos.add(vuelo);
        disponibilidad.put(vuelo.getFechaSalida(), true);
    }


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

    @Override
    public String getSavingFormat() {
        return codigo + "," + tipoDeAvion.getNombre() + ".";
    }

    public static List<Avion> build(List<String> elementosStr, ServerInterface server){

        List<Avion> elementos = new ArrayList<>();
        for (String elemento :elementosStr ) {
            int corte1 = 0;

            for (int i = 0; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ',') {
                    corte1 = i;
                    break;
                }
            }
            String codigo = elemento.substring(0, corte1);
            TipoDeAvion tipoDeAvion =  server.getTipoDeAvion(elemento.substring(corte1 +1, elemento.length() -1));
            Avion avion = new Avion(codigo, tipoDeAvion);

            elementos.add(avion);
        }
        return elementos;
    }

    public int getCantidadDePersonal() {
       return tipoDeAvion.getCantidadDePersonalAbordo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avion avion = (Avion) o;

        if (!codigo.equals(avion.codigo)) return false;
        if (!tipoDeAvion.equals(avion.tipoDeAvion)) return false;
        return asientos != null ? asientos.equals(avion.asientos) : avion.asientos == null;
    }

    @Override
    public int hashCode() {
        int result = codigo.hashCode();
        result = 31 * result + tipoDeAvion.hashCode();
        result = 31 * result + (asientos != null ? asientos.hashCode() : 0);
        return result;
    }
}

