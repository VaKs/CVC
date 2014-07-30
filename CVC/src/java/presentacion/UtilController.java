package presentacion;

/**
 *
 * @author Sergio Vacas
 */
public class UtilController {

    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }

        return false;
    }

    public static double redondear(double numero) {

        long mult = (long) Math.pow(10, 2);
        double resultado = (Math.round(numero * mult)) / (double) mult;
        return resultado;
    }

    public static Integer parseInt(String s) {
        if ((s == null) || (s.trim().equals("") == true)) {
            return null;
        }

        return Integer.parseInt(s);
    }

    public static String parseString(String s) {
        if ((s == null) || (s.trim().equals("") == true)) {
            return null;
        }

        return s;
    }
}
