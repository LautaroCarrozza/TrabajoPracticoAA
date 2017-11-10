import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Tarifario {


    private List<Tarifa> tarifaList = new ArrayList<>();
    Saver tarifaSaver;


    int getPreciodeTarifa(String codigoTarifa){
        for (Tarifa tarifa:tarifaList) {
            if (tarifa.getCodigoTarifa().equals(codigoTarifa)){
            return tarifa.getPrecio();
            }
        }

        throw new RuntimeException("Tarifa no registrada");
    }

    void addTarifa(String categoria, int codigoVuelo, int precio){
        Tarifa tarifa = new Tarifa(categoria, codigoVuelo, precio );

        if (!tarifaList.contains(tarifa)){
        tarifaList.add(tarifa);
        tarifaSaver.save(tarifa);
        }
    }

    public Tarifario() {
        tarifaSaver = new Saver("Tarifas");
        tarifaList = Tarifa.build(tarifaSaver.get());
    }
}
