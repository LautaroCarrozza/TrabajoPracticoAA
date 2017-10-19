public abstract class Persona {

    private int dni;
    private String nombre;

    public Persona(int dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
