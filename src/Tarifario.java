import java.util.HashMap;
import java.util.Map;

class Tarifario {

    private Map<String, Tarifa> tarifas = new HashMap<>();

    private class Tarifa{
        String codigoTarifa;
        String categoria;
        int codigoDeVuelo;
        int precio;

        Tarifa(String categoria, int vuelo, int precio) {
            this.categoria = categoria;
            this.precio = precio;
            this.codigoDeVuelo = vuelo;

            codigoTarifa = codigoDeVuelo + "-" + categoria;
        }

        String getCodigoTarifa() {
            return codigoTarifa;
        }

        int getPrecio() {
            return precio;
        }
    }

    int getPreciodeTarifa(String codigoTarifa){
        if (tarifas.containsKey(codigoTarifa)) {
            return tarifas.get(codigoTarifa).getPrecio();
        }
        throw new RuntimeException("Tarifa no registrada");
    }

    void addTarifa(String categoria, int codigoVuelo, int precio){
        Tarifa tarifa = new Tarifa(categoria, codigoVuelo, precio );
        tarifas.put(tarifa.getCodigoTarifa(), tarifa);
        System.out.println(tarifas);
    }

}
