import java.util.HashMap;
import java.util.Map;

public class Tarifario {

    private Map<String, Tarifa> tarifas = new HashMap<>();

    private class Tarifa{
        String codigoTarifa;
        String categoria;
        int codigoDeVuelo;
        int precio;

        public Tarifa(String categoria, int vuelo, int precio) {
            this.categoria = categoria;
            this.precio = precio;
            this.codigoDeVuelo = vuelo;

            codigoTarifa = codigoDeVuelo + "-" + categoria;
        }

        public String getCodigoTarifa() {
            return codigoTarifa;
        }

        public int getPrecio() {
            return precio;
        }
    }

    public int getPreciodeTarifa (String codigoTarifa){
        if (tarifas.containsKey(codigoTarifa)) {
            return tarifas.get(codigoTarifa).getPrecio();
        }
        throw new RuntimeException("Tarifa no registrada");
    }

    public void addTarifa(String categoria, int codigoVuelo, int precio){
        Tarifa tarifa = new Tarifa(categoria, codigoVuelo, precio );
        tarifas.put(tarifa.getCodigoTarifa(), tarifa);
        System.out.println(tarifas);
    }

}
