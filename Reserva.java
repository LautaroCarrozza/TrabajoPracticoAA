public class Reserva {
    Cliente cliente;
    Vuelo vuelo;

    public Reserva(Cliente cliente, Vuelo vuelo) {
        this.cliente = cliente;
        this.vuelo = vuelo;
    }

    @Override
    public String toString() {
        return "\n" + "Codigo de vuelo: " + vuelo.getCodigoDeVuelo() + "\n" +  "   Fecha: " + vuelo.getProximaSalida() + "\n" + "   Desde: " + vuelo.getStringAeropuertoSalida() + "\n" + "  Hasta: " + vuelo.getStringAeropuertoLlegada() + "\n" ;

    }
}
