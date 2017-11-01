import java.time.LocalDate;
import java.util.*;

public class Vuelo {

    private Aeropuerto aeropuertoSalida;
    private Aeropuerto aeropuertoLlegada;
    private LocalDate fechaSalida;
    private Avion avion;
    private int codigoDeVuelo;
    private Map<Asiento, Boolean> ocupacion = new HashMap<>();

    public Vuelo(Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, int dia, int mes, int ano, Avion avion, int codigoDeVuelo) {
        fechaSalida = LocalDate.of(ano,mes,dia);
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.avion = avion;
        this.codigoDeVuelo = codigoDeVuelo;
        for (Asiento a : avion.getAsientos()) {
            ocupacion.put(a, false);
        }


    }

    public String getUbicacionSalida() {
        return aeropuertoSalida.getUbicacion();
    }

    public String getUbicacionLlegada() {
        return aeropuertoLlegada.getUbicacion();
    }

    public LocalDate getFechaSalida() {

        return fechaSalida;
    }

    public int getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public int cantidadAsientosDisponibles(String categoria) {
        int result = 0;
        for (Asiento a : avion.getAsientos()) {
            if (!ocupacion.get(a) && a.getCategoria().equals(categoria)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String toString() {

        String result = "Fecha: " + fechaSalida.getMonthValue() + "/" + (fechaSalida.getDayOfMonth()  + "/" + fechaSalida.getYear() + "  Desde: " + aeropuertoSalida.getUbicacion() + "  Hasta: " + aeropuertoLlegada.getUbicacion()
                + " Codigo: " + codigoDeVuelo);

        return result;
    }

    public void ocupar(Asiento asiento) {
        ocupacion.put(asiento, true);
    }

    public List<Asiento> asientosDisponibles(String categoria) {
        List<Asiento> asientosDisponibles = new ArrayList<>();
        for (Asiento a : avion.getAsientos()) {
            if (!ocupacion.get(a) && a.getCategoria().equals(categoria)) {
                asientosDisponibles.add(a);
            }
        }
        return asientosDisponibles;
    }

    public Asiento getAsiento(int fila, char columna){
        for (Asiento asiento: avion.getAsientos()) {
            if(asiento.getFila() == (fila) && asiento.getColumna() == columna){
                return asiento;
            }
        }
        throw new RuntimeException("El avion asignado al vuelo no cuenta con ese asiento");
    }

    public boolean getOcupacion(Asiento asiento) {
        return ocupacion.get(asiento);
    }
}
