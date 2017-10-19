import java.util.*;

public class Vuelo {

    Calendar calendar = new GregorianCalendar();

    Aeropuerto aeropuertoSalida;
    Aeropuerto aeropuertoLlegada;
    Date proximaSalida;
    Avion avion;
    String codigoDeVuelo;
    Map<Asiento,Boolean> ocupacion = new HashMap<>();




    public Vuelo(Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, int dia, int mes, int ano, Avion avion, String codigoDeVuelo) {
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        proximaSalida = new Date(dia, mes, ano);

        this.avion = avion;
        this.codigoDeVuelo = codigoDeVuelo;

        for (Asiento a: avion.asientos) {
            ocupacion.put(a, false);
        }

    }

    public Date getProximaSalida() {

        return proximaSalida;
    }

    public String getStringAeropuertoSalida() {
        return aeropuertoSalida.toString();
    }

    public String getStringAeropuertoLlegada() {
        return aeropuertoLlegada.toString();
    }

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public int cantidadAsientosDisponibles(String categoria) {
        int result = 0;
        for (Asiento a:avion.asientos) {
            if(!ocupacion.get(a)){
                result ++;
            }
        }
        return result;
    }

    @Override
    public String toString(){

        String result = "Fecha: " + proximaSalida + "  Desde: " + aeropuertoSalida.ubicacion + "  Hasta: " + aeropuertoLlegada.ubicacion +  " Codigo: " + codigoDeVuelo;

        return result;
    }

    public void ocupar(int fila, char columna) {
        for (Asiento a:avion.asientos) {
            if (a.fila == fila && a.columna == columna){
                ocupacion.put(a, true);
            }
        }
    }

    public String asientosDisponibles(){
        List<Asiento> asientosDisponibles = new ArrayList<>();
        for (Asiento a:avion.asientos) {
            if (ocupacion.get(a)){asientosDisponibles.add(a);}
        }

        String result = "Asientos disponibles: ";
        int count = 1;
        for (Asiento a: asientosDisponibles) {
            result += count + " " + a;
        }
    return  result;
    }

}
