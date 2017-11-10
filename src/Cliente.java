import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona implements Saveable{
    private int numeroDeCliente;
    private List<Pasaje> pasajes = new ArrayList<>();

    public Cliente(int dni, String nombre, int numeroDeCliente) {
        super(dni, nombre);
        this.numeroDeCliente = numeroDeCliente;
    }

    public List<Reserva> getReservas() {

        List<Vuelo> vuelos = new ArrayList<>();
        for (Pasaje pasaje:pasajes) {
            if (!vuelos.contains(pasaje.getVuelo())){vuelos.add(pasaje.getVuelo());}
        }


        List<Reserva> reservas = new ArrayList<>();
        for (Vuelo vuelo: vuelos){
            List<Pasaje> aReservar = new ArrayList<>();
            for (Pasaje pasaje:pasajes) {
                if (pasaje.getVuelo().getCodigoDeVuelo() == vuelo.getCodigoDeVuelo() && pasaje.getVuelo().getFechaSalida().getDayOfMonth() == pasaje.getVuelo().getFechaSalida().getDayOfMonth()){
                    aReservar.add(pasaje);
                }
            }
            Reserva reserva = new Reserva(aReservar);
            reservas.add(reserva);
        }

        return reservas;
    }

    public int getNumeroDeCliente() {
        return numeroDeCliente;
    }

    public static List<Cliente> build(List<String> elementosStr, ServerInterface server) {
        List<Cliente> elementos = new ArrayList<>();

        for (String elemento :elementosStr ) {
            int corte1 = 0;
            int corte2 = 0;

            for (int i = 0; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte1 = i;
                    break;
                }
            }
            for (int i = corte1 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte2 = i;
                    break;
                }
            }

           Cliente cliente = new Cliente(Integer.parseInt(elemento.substring(0, corte1)),   elemento.substring(corte1 + 1, corte2),  Integer.parseInt(elemento.substring(corte2+1, elemento.length()-1)));
           elementos.add(cliente);

        }
        return elementos;
    }

    @Override
    public String getSavingFormat() {
        return super.dni + "," + super.nombre + "," + numeroDeCliente + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return numeroDeCliente == cliente.numeroDeCliente;
    }

    @Override
    public int hashCode() {
        return numeroDeCliente;
    }

    public void addPasaje(Pasaje pasaje) {
        pasajes.add(pasaje);
    }
}
