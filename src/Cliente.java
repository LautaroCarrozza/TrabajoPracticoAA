import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona{
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

    public void addReserva(Reserva reserva){
        reservas.add(reserva);
    }
}
