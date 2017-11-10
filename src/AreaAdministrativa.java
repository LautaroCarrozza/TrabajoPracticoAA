import java.util.ArrayList;
import java.util.List;

public class AreaAdministrativa implements Saveable {
    private String nombre;
    private boolean habilitacionVenta;

    public AreaAdministrativa(String nombre, boolean habilitacionVenta) {
        this.nombre = nombre;
        this.habilitacionVenta = habilitacionVenta;
    }

    public boolean isHabilitacionVenta() {
        return habilitacionVenta;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String getSavingFormat() {
        return nombre + "," + habilitacionVenta + ".";
    }
    public static List<AreaAdministrativa> build(List<String> elements, ServerInterface server){
        List<AreaAdministrativa> elementos = new ArrayList<>();
        for (String elemento:elements) {
            int corte1 = 0;

            for (int i = 0; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte1 = i;
                    break;
                }
            }
            String field1 = elemento.substring(0, corte1);
            String field2 = elemento.substring(corte1+1,elemento.length()-1);
            AreaAdministrativa areaAdministrativa = new AreaAdministrativa(field1,Boolean.parseBoolean(field2));
            elementos.add(areaAdministrativa);
        }
        return elementos;
    }
}
