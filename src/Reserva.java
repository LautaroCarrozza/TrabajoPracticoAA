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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reserva reserva = (Reserva) o;

        if (cantidadDePersonas != reserva.cantidadDePersonas) return false;
        if (!pasajes.equals(reserva.pasajes)) return false;
        if (!asientos.equals(reserva.asientos)) return false;
        return vuelo != null ? vuelo.equals(reserva.vuelo) : reserva.vuelo == null;
    }

    @Override
    public int hashCode() {
        int result = pasajes.hashCode();
        result = 31 * result + asientos.hashCode();
        result = 31 * result + cantidadDePersonas;
        result = 31 * result + (vuelo != null ? vuelo.hashCode() : 0);
        return result;
    }
}