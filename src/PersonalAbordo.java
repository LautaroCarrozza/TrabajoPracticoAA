import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonalAbordo extends Persona implements Saveable{
    private String cargo;
    private List<Vuelo> vuelos = new ArrayList<>();
    private int numeroDeEmpleado;

    public String getCargo() {
        return cargo;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public int getNumeroDeEmpleado() {
        return numeroDeEmpleado;
    }

    public PersonalAbordo(int dni, String nombre, String cargo, int numeroDeEmpleado) {
        super(dni, nombre);
        this.cargo = cargo;
        this.numeroDeEmpleado = numeroDeEmpleado;
    }

    public boolean available(LocalDate fechaDeSalida){
        for (Vuelo vuelo:vuelos) {
            if (vuelo.getFechaSalida().equals(fechaDeSalida))return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "Nombre: " + getNombre() + "\n" + "Vuelos: ";
        for (Vuelo v:vuelos) {
            result = result + v.getCodigoDeVuelo();
        }
        return result;
    }

    @Override
    public String getSavingFormat() {
        return dni + "," + nombre + ","+ cargo + "," + numeroDeEmpleado + ".";
    }

    public static List<PersonalAbordo> build(List<String> elementosStr){

        List<PersonalAbordo> elementos = new ArrayList<>();
        for (String elemento :elementosStr ) {
            int corte1 = 0;
            int corte2 = 0;
            int corte3 = 0;

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

            int field1 = Integer.parseInt(elemento.substring(0, corte1));
            String field2 = elemento.substring(corte1 + 1 , corte2);
            String field3 = elemento.substring(corte2+1, corte3);
            int field4 = Integer.parseInt(elemento.substring(corte3+1, elemento.length()-1));
            PersonalAbordo personalAbordo= new PersonalAbordo(field1,field2, field3, field4);
            elementos.add(personalAbordo);
        }
        return elementos;

    }

    public void addVuelo(Vuelo vuelo){
        vuelos.add(vuelo);
    }
}
