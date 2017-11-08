import java.util.ArrayList;
import java.util.List;

public class Pasaje implements Saveable {
    static ServerInterface server = new ServerMock();
    private Vuelo vuelo;
    private Asiento asiento;
    private Cliente cliente;

    public Pasaje(Vuelo vuelo, Asiento asiento, Cliente cliente) {
        this.vuelo = vuelo;
        this.asiento = asiento;
        this.cliente = cliente;
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

    @Override
    public String getSavingFormat() {
        return vuelo.getCodigoDeVuelo() + "," + asiento.getCode() + "," + cliente.getNumeroDeCliente() + ".";
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
