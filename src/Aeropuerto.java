import java.util.ArrayList;
import java.util.List;

public class Aeropuerto implements Saveable{
    private String codigo;
    private String ubicacion;
    private String nombre;

    public Aeropuerto(String codigo, String ubicacion, String nombre) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return ubicacion + "-"+ nombre;
    }

    @Override
    public String getSavingFormat() {
        return codigo + "," + ubicacion + "," + nombre + ".";
    }

    public static List<Aeropuerto> build (List<String> elementosStr){
        List<Aeropuerto> elementos = new ArrayList<>();
        for (String elemento :elementosStr ) {
        int corte1 = 0;
        int corte2 = 0;

            for (int i = 0; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte1 = i;
                    break;
                }
            }
            for (int i = corte1 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte2 = i;
                }
            }
        Aeropuerto aeropuerto = new Aeropuerto(elemento.substring(0, corte1),elemento.substring(corte1 + 1, corte2), elemento.substring(corte2+1, elemento.length()-1));
        elementos.add(aeropuerto);

        }
        return elementos;


    }
}
