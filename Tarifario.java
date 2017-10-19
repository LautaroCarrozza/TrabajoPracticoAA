import java.util.HashMap;
import java.util.Map;

public class Tarifario {

    Map<String, Tarifa> tarifas = new HashMap<>();


    private class Tarifa{
        String tarifaStr;
        String categoria;
        Vuelo vuelo;
        int precio;

        public Tarifa( String categoria, Vuelo vuelo, int precio) {
            this.categoria = categoria;
            this.precio = precio;
            this.vuelo = vuelo;

            tarifaStr = vuelo.getCodigoDeVuelo() + categoria;
        }
    }

    public void addTarifa(String categoria, Vuelo vuelo, int precio){
        Tarifa t = new Tarifa(categoria, vuelo, precio);
        tarifas.put(t.tarifaStr, t);
    }




}




/*

public class Tarifario {

    private Map<Tarifa, Double> tarifas = new HashMap<Tarifa, Double>();

    public Double getPrecio(Vuelo vuelo, categoria){
        Tarifa tarifa = new Tarifa(categoria, vuelo);

        return tarifas.get(tarifa);
    }

    public void agregarTarifa(Categoria categoria, Vuelo vuelo, Double precio){
        Tarifa tarifa = new Tarifa(categoria, vuelo);
        tarifas.put(tarifa, precio);
    }

    public void editarTarifa(Categoria categoria, Vuelo vuelo, Double precio){
        Tarifa tarifa = new Tarifa(categoria, vuelo);
        tarifas.remove(tarifa);
        tarifas.put(tarifa, precio);
    }

    private class Tarifa {
        Categoria categoria;
        Vuelo vuelo;

        public Tarifa(Categoria categoria, Vuelo vuelo) {
            this.categoria = categoria;
            this.vuelo = vuelo;
        }

        @Override
        public int hashCode() {
            return 997 * categoria.hashCode() + 31 * vuelo.hashCode();
        }

        @Override
        public boolean equals(Object object) {
            Tarifa tarifa = (Tarifa) object;
            if (categoria.equals(tarifa.categoria) && vuelo.equals(tarifa.vuelo)) {
                return true;
            }
            return false;
        }
    }

//    public void removerTarifa(Categoria categoria, Vuelo vuelo){
//        Tarifa tarifa = new Tarifa(categoria, vuelo);
//        tarifas.remove(tarifa);
//    }// a usar para posible cambio de avion en un vuelo.

}

*/



