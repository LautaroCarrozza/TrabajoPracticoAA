import java.util.ArrayList;
import java.util.List;

public class Pasaje implements Saveable {
    private int codigo;
    private Vuelo vuelo;
    private Asiento asiento;
    private Cliente cliente;
    private String nombre;
    private int dni;

    public Pasaje(Vuelo vuelo, int fila, String columna, Cliente cliente, String nombre, int dni) {
        this.vuelo = vuelo;
        this.asiento = asiento;
        this.cliente = cliente;
        this.asiento = vuelo.getAsiento(fila, columna.charAt(0));
        this.dni = dni;
        this.nombre = nombre;
        codigo = Math.abs(vuelo.hashCode()*7 + asiento.hashCode()*5 + cliente.hashCode()*11 / 1000);
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
        return vuelo.getCodigoDeVuelo() + "," + asiento.getFila() + "," + ("" + asiento.getColumna()) + "," + cliente.getNumeroDeCliente()+ "," + nombre + "," + dni + ".";
    }

    @Override
    public String toString() {
        return "Asiento: " + asiento.toString() + " " + nombre + " DNI: " + dni + "\nCodigo de ticket: " + codigo;
    }

    public static List<Pasaje> build(List<String> elementosStr, ServerInterface server) {
        List<Pasaje> elementos = new ArrayList<>();
        for (String elemento : elementosStr) {
            int corte1 = 0;
            int corte2 = 0;
            int corte3 = 0;
            int corte4 = 0;
            int corte5 = 0;

            for (int i = 0; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ',') {
                    corte1 = i;
                    break;
                }
            }
            for (int i = corte1 + 1; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ',') {
                    corte2 = i;
                    break;
                }
            }
            for (int i = corte2 + 1; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ',') {
                    corte3 = i;
                    break;
                }
            }
            for (int i = corte3 + 1; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ',') {
                    corte4 = i;
                    break;
                }
            }
            for (int i = corte4 + 1; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ',') {
                    corte5 = i;
                    break;
                }
            }

            Vuelo vuelo = server.getVuelo(Integer.parseInt(elemento.substring(0, corte1))) ;
            int fila = Integer.parseInt(elemento.substring(corte1+1, corte2));
            String columna = elemento.substring(corte2+1, corte3);
            Cliente cliente = server.getCliente(Integer.parseInt(elemento.substring(corte3+1, elemento.length() -1 )));
            String nombre = elemento.substring(corte4+1, corte5);
            int dni = Integer.parseInt(elemento.substring(corte5, elemento.length()-1));

            Pasaje pasaje = new Pasaje(vuelo, fila, columna, cliente, nombre, dni);
            elementos.add(pasaje);
        }
        return elementos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pasaje pasaje = (Pasaje) o;

        if (codigo != pasaje.codigo) return false;
        if (!vuelo.equals(pasaje.vuelo)) return false;
        if (!asiento.equals(pasaje.asiento)) return false;
        return cliente.equals(pasaje.cliente);
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + vuelo.hashCode();
        result = 31 * result + asiento.hashCode();
        result = 31 * result + cliente.hashCode();
        return result;
    }
}
