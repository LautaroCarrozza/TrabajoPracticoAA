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
        String result = "Reserva para " + pasajes.get(0).getCliente().getNombre() +
                "\n" + ", Desde: " + pasajes.get(0).getVuelo().getUbicacionSalida().toString()
                + " Hasta: " + pasajes.get(0).getVuelo().getUbicacionLlegada().toString();
        for (Pasaje pasaje: pasajes
             ) {
            result += pasaje.toString();
        }
        return result;
    }

    public List<Pasaje> getPasajes() {
        return pasajes;
    }

    public void setPasajes(List<Pasaje> pasajes) {
        this.pasajes = pasajes;
    }
}