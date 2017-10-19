public class Aeropuerto {
    String codigo;
    String ubicacion;
    String nombre;

    public Aeropuerto(String codigo, String ubicacion, String nombre) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return ubicacion + " / "+ nombre;
    }
}
