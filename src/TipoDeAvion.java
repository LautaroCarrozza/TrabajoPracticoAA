import java.util.ArrayList;
import java.util.List;

public class TipoDeAvion implements Saveable{

    private int cantidadFilasEconomy;
    private int cantidadAsientosPorFilaEconomy;
    private int cantidadFilasBussiness;
    private int cantidadAsientosPorFilaBussiness;
    private int cantidadFilasFirst;
    private int cantidadDeAsientosPorFilaFirst;
    private ArrayList<ArrayList<Asiento>> asientos = new ArrayList<ArrayList<Asiento>>();
    private String nombre;
    private int cantidadDePersonalAbordo;

    public int getCantidadDePersonalAbordo() {
        return cantidadDePersonalAbordo;
    }

    public TipoDeAvion(int cantidadFilasEconomy, int cantidadAsientosPorFilaEconomy, int cantidadFilasBussiness, int cantidadAsientosPorFilaBussiness, int cantidadFilasFirst, int cantidadDeAsientosPorFilaFirst, int cantidadDePersonalAbordo, String nombre) {
        this.cantidadFilasEconomy = cantidadFilasEconomy;
        this.cantidadAsientosPorFilaEconomy = cantidadAsientosPorFilaEconomy;
        this.cantidadFilasBussiness = cantidadFilasBussiness;
        this.cantidadAsientosPorFilaBussiness = cantidadAsientosPorFilaBussiness;
        this.cantidadFilasFirst = cantidadFilasFirst;
        this.cantidadDeAsientosPorFilaFirst = cantidadDeAsientosPorFilaFirst;
        this.cantidadDePersonalAbordo = cantidadDePersonalAbordo;
        this.nombre = nombre;
    }


    public int getCantidadFilasBussiness() {
        return cantidadFilasBussiness;
    }

    public int getCantidadAsientosPorFilaBussiness() {
        return cantidadAsientosPorFilaBussiness;
    }

    public int getCantidadFilasFirst() {
        return cantidadFilasFirst;
    }

    public int getCantidadDeAsientosPorFilaFirst() {
        return cantidadDeAsientosPorFilaFirst;
    }


    public int getCantidadAsientosPorFilaEconomy() {
        return cantidadAsientosPorFilaEconomy;
    }

    public int getCantidadFilasEconomy() {
        return cantidadFilasEconomy;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String getSavingFormat() {
        return cantidadFilasEconomy + "," + cantidadAsientosPorFilaEconomy + "," + cantidadFilasBussiness + "," + cantidadAsientosPorFilaBussiness + "," + cantidadFilasFirst + "," + cantidadDeAsientosPorFilaFirst + "," +cantidadDePersonalAbordo + "," + nombre;
    }
    public static List<TipoDeAvion> build(List<String> elementosStr){
        List<TipoDeAvion> elementos = new ArrayList<>();
        for (String elemento :elementosStr ) {
            int corte1 = 0;
            int corte2 = 0;
            int corte3 = 0;
            int corte4 = 0;
            int corte5 = 0;
            int corte6 = 0;
            int corte7 = 0;

            for (int i = 0; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte1 = i;
                    break;
                }
            }
            for (int i = corte1 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte2 = i;
                    break;
                }
            }
            for (int i = corte2 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte3 = i;
                    break;
                }
            }
            for (int i = corte3 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte4 = i;
                    break;
                }
            }
            for (int i = corte4 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte5 = i;
                    break;
                }
            }
            for (int i = corte5 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte6 = i;
                    break;
                }
            }
            for (int i = corte6 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte7 = i;
                    break;
                }
            }

            String field1 = elemento.substring(0, corte1);
            String field2 = elemento.substring(corte1 + 1 , corte2);
            String field3 = elemento.substring(corte2+1, corte3);
            String field4 = elemento.substring(corte3+1, corte4);
            String field5 = elemento.substring(corte4+1, corte5);
            String field6 = elemento.substring(corte5+1, corte6);
            String field7 = elemento.substring(corte6+1, corte7);
            String field8 = elemento.substring(corte7+1, elemento.length()-1);

            TipoDeAvion tipoDeAvion = new TipoDeAvion(Integer.parseInt(field1),Integer.parseInt(field2),Integer.parseInt(field3),Integer.parseInt(field4),Integer.parseInt(field5),Integer.parseInt(field6),Integer.parseInt(field7),field8);
            elementos.add(tipoDeAvion);
        }
        return elementos;
    }
}
