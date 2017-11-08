import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona implements Saveable{
    private int numeroDeCliente;
    private List<Reserva> reservas = new ArrayList<>();

    public Cliente(int dni, String nombre, int numeroDeCliente) {
        super(dni, nombre);
        this.numeroDeCliente = numeroDeCliente;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public int getNumeroDeCliente() {
        return numeroDeCliente;
    }

    public void guardarReserva(List<Pasaje> pasajes, Vuelo vuelo) {
        Reserva reserva = new Reserva(pasajes, vuelo);
        reservas.add(reserva);
    }

    public static List<Cliente> build(List<String> elementosStr) {
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

}
