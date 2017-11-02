import java.util.ArrayList;
import java.util.List;

public class Reserva {

    List<Pasaje> pasajes;
    Vuelo vuelo = pasajes.get(0).getVuelo();
    List<Asiento> asientos = new ArrayList<>();
    int cantidadDePersonas = pasajes.size();
    String categoria = pasajes.get(0).getCategoria();

    public Reserva(List<Pasaje> pasajes) {
        this.pasajes = pasajes;
        for (Pasaje pasaje:pasajes
             ) {
            asientos.add(pasaje.getAsiento());
        }
    }

    @Override
    public String toString() {
        String result = "\n" + "Codigo de vuelo: " + vuelo.getCodigoDeVuelo() + "\n" +  "  Fecha: " + vuelo.getFechaSalida() + "\n" + "  Desde: "
                + vuelo.getUbicacionSalida() + "\n" + "  Hasta: " + vuelo.getUbicacionLlegada() + "\n" + "  Cantidad de personas: " + cantidadDePersonas + "\n" + "  Categoria: " + categoria
                + "\n  Asientos: ";

        for (Asiento asiento: asientos
                ) {
            result += "\n   "+ asiento;
        }

        return result;
    }
}