import java.util.ArrayList;
import java.util.List;

    public class Tarifa implements Saveable {
        String codigoTarifa;
        String categoria;
        String codigoDeVuelo;
        int precio;

        Tarifa(String categoria, String vuelo, int precio) {
            this.categoria = categoria;
            this.precio = precio;
            this.codigoDeVuelo = vuelo;

            codigoTarifa = codigoDeVuelo + "-" + categoria;
        }

        String getCodigoTarifa() {
            return codigoTarifa;
        }

        int getPrecio() {
            return precio;
        }

        @Override
        public String getSavingFormat() {
            return categoria + "," + codigoDeVuelo + "," + precio + ".";
        }

        public static List<Tarifa> build(List<String> elements) {
            List<Tarifa> elementos = new ArrayList<>();
            for (String elemento :elements ) {
                int corte1 = 0;
                int corte2 = 0;

                for (int i = 0; i < elemento.length(); i++) {
                    if (elemento.charAt(i) == ',') {
                        corte1 = i;
                        break;
                    }
                }
                for (int i = corte1 + 1; i < elemento.length(); i++) {
                    if (elemento.charAt(i) == ',') {
                        corte2 = i;
                        break;
                    }
                }


                String field1 = elemento.substring(0, corte1);
                String field2 = elemento.substring(corte1 + 1, corte2);
                String field3 = elemento.substring(corte2 + 1, elemento.length() - 1);

                Tarifa tarifa = new Tarifa(field1,field2, Integer.parseInt(field3));
                elementos.add(tarifa);
            }
            return elementos;
        }
    }


