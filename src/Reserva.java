import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private Cliente cliente;
    private int cantidadDePersonas;
    private Vuelo vuelo;
    private String categoria;
    List<Asiento> asientos = new ArrayList<>();

    public Reserva(Cliente cliente, Vuelo vuelo, int cantidadDePersonas, String categoria, List<Asiento> asientos) {
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.cantidadDePersonas = cantidadDePersonas;
        this.categoria = categoria;
        this.asientos = asientos;
    }

    @Override
    public String toString() {
        String result = "\n" + "Codigo de vuelo: " + vuelo.getCodigoDeVuelo() + "\n" +  "  Fecha: " + vuelo.getFechaSalida() + "\n" + "  Desde: "
                + vuelo.getUbicacionSalida() + "\n" + "  Hasta: " + vuelo.getUbicacionLlegada() + "\n" + "  Cantidad de personas: " + cantidadDePersonas + "\n" + "  Categoria: " + categoria
                + "\n  Asientos: ";

        for (Asiento asiento:asientos
             ) {
            result += "\n   "+ asiento;
        }

        return result;
    }
}
