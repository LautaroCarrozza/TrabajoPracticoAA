public class Reserva {
    private Cliente cliente;
    private Vuelo vuelo;

    public Reserva(Cliente cliente, Vuelo vuelo) {
        this.cliente = cliente;
        this.vuelo = vuelo;
    }

    @Override
    public String toString() {
        return "\n" + "Codigo de vuelo: " + vuelo.getCodigoDeVuelo() + "\n" +  "  Fecha: " + vuelo.getFechaSalida() + "\n" + "   Desde: "
                + vuelo.getUbicacionSalida() + "\n" + "  Hasta: " + vuelo.getUbicacionLlegada() + "\n" ;

    }
}
