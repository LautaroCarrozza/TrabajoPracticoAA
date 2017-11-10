import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reserva{

    private List<Pasaje> pasajes;
    private Cliente cliente;
    private List<Asiento> asientos = new ArrayList<>();
    private int cantidadDePersonas;


    public Reserva(List<Pasaje> pasajesReservados) {
        pasajes = pasajesReservados;
        cantidadDePersonas = pasajes.size();
        for (Pasaje pasaje:pasajes) {
            asientos.add(pasaje.getAsiento());
        }
        cliente = pasajes.get(0).getCliente();
    }

    @Override
    public String toString() {
        Set<Pasaje> pasajes = new HashSet<>();
        pasajes.addAll(this.pasajes);
        List<Pasaje> uniques= new ArrayList<>();
        uniques.addAll(pasajes);


        String result = "Reserva para " + uniques.get(0).getCliente().getNombre() +
                "\nDesde: " + uniques.get(0).getVuelo().getUbicacionSalida()
                + " Hasta: " + uniques.get(0).getVuelo().getUbicacionLlegada() + "\n";
        for (Pasaje pasaje: uniques) {
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

    public Cliente getCliente() {
        return cliente;
    }
}