import java.util.ArrayList;
import java.util.List;

public class Pasaje implements Saveable {
    static ServerInterface server = new ServerMock();
    private int codigo;
    private Vuelo vuelo;
    private Asiento asiento;
    private Cliente cliente;

    public Pasaje(Vuelo vuelo, Asiento asiento, Cliente cliente) {
        this.vuelo = vuelo;
        this.asiento = asiento;
        this.cliente = cliente;
        codigo = vuelo.hashCode()*7 + asiento.hashCode()*5 + cliente.hashCode()*11;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String getSavingFormat() {
        return vuelo.getCodigoDeVuelo() + "," + asiento.getCode() + "," + cliente.getNumeroDeCliente() + ".";
    }

    @Override
    public String toString() {
        return asiento.toString();
    }

    public static List<Pasaje> build(List<String> elementosStr) {
        List<Pasaje> elementos = new ArrayList<>();
        for (String elemento : elementosStr) {
            int corte1 = 0;
            int corte2 = 0;

            for (int i = 0; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ',') {
                    corte1 = i;
                    break;
                }
            }
            for (int i = corte1 + 1; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ',') {
                    corte2 = i;
                }
            }
            Vuelo vuelo = server.getVuelo(Integer.parseInt(elemento.substring(0, corte1)));
            Pasaje pasaje = new Pasaje(vuelo, vuelo.getAsiento(elemento.substring(corte1 + 1, corte2)), server.getCliente(Integer.parseInt(elemento.substring(corte2 + 1, elemento.length() - 1))));
            elementos.add(pasaje);
        }
        return elementos;
    }
}
