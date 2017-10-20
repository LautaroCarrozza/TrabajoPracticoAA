public class Asiento {

    private String categoria;
    private int fila;
    private char columna;

    public Asiento(String categoria, int fila, int columnaINT) {
        this.categoria = categoria;
        this.fila = fila;

        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char columna = abc.charAt(columnaINT - 1);
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Fila:  " + fila + " Columna: " + columna + " Categoria: " + categoria;
    }

    public int getFila() {
        return fila;
    }

    public char getColumna() {
        return columna;
    }
}
