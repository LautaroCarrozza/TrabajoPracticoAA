public class Pasaje {
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

    public String getCategoria() {
        return asiento.getCategoria();
    }
}
