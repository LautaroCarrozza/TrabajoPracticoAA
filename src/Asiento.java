public class Asiento {

    private String categoria;
    private int fila;
    private char columna;
    private String code = "" + fila + (int)columna;
    public Asiento(String categoria, int fila, int columnaINT) {
        this.categoria = categoria;
        this.fila = fila;

        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char columna = abc.charAt(columnaINT - 1);
        this.columna = columna;
    }

    @Override
    public String toString() {
        return " Fila:  " + fila + " Columna: " + columna + " Categoria: " + categoria;
    }

    public int getFila() {
        return fila;
    }

    public char getColumna() {
        return columna;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Asiento asiento = (Asiento) o;

        if (fila != asiento.fila) return false;
        if (columna != asiento.columna) return false;
        if (categoria != null ? !categoria.equals(asiento.categoria) : asiento.categoria != null) return false;
        return code != null ? code.equals(asiento.code) : asiento.code == null;
    }

    @Override
    public int hashCode() {
        int result = categoria != null ? categoria.hashCode() : 0;
        result = 31 * result + fila;
        result = 31 * result + (int) columna;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
