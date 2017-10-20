public class Aeropuerto {
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
        return ubicacion + " / "+ nombre;
    }
}
