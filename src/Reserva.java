import java.util.ArrayList;
import java.util.List;

public class Reserva{

    private List<Pasaje> pasajes;
    private List<Asiento> asientos = new ArrayList<>();
    private int cantidadDePersonas;
    private Vuelo vuelo;


    public Reserva(List<Pasaje> pasajesReservados, Vuelo vuelo) {
        pasajes = pasajesReservados;
        this.vuelo = vuelo;
        cantidadDePersonas = pasajes.size();
        for (Pasaje pasaje:pasajes) {
            asientos.add(pasaje.getAsiento());
        }
    }

    @Override
    public String toString() {
        String result = "\n" + "Codigo de vuelo: " + vuelo.getCodigoDeVuelo() + "\n" +  "  Fecha: " + vuelo.getFechaSalida() + "\n" + "  Desde: "
                + vuelo.getUbicacionSalida() + "\n" + "  Hasta: " + vuelo.getUbicacionLlegada() + "\n" + "  Cantidad de personas: " + cantidadDePersonas + "\n  Asientos: ";

        for (Asiento asiento: asientos
                ) {
            result += "\n   "+ asiento;
        }

        return result;
    }

}