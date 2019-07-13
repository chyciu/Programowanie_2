import java.util.Arrays;
import java.util.Scanner;

public class MainSumaLiczb {

    public static void main(String[] args) {

        String tekst = "akaka77uu77aa88 9a";

        int suma = sumujLiczbyZeStringa(tekst);

        System.out.println("Suma liczba to:" + suma);

    }


    public static int sumujLiczbyZeStringa(String teks) {
        String[] strings = teks.split("[^0-9\\-]+");
        System.out.println("Twoje słowo bez liczb to: " + Arrays.toString(strings));

        int suma = 0;

        for (String s : strings) {
            int minusIndex = s.indexOf('-');
            if (minusIndex > 0) {
                String liczba1Str = s.substring(0, minusIndex);
                String liczba2Str = s.substring((minusIndex));
                int liczba1 = Integer.parseInt(liczba1Str);
                suma += liczba1;
                int liczba2 = Integer.parseInt(liczba2Str);
                suma += liczba2;
            }
            if (!s.isEmpty() && s.matches("[\\-]?[0-9]")) {
                int liczba = Integer.parseInt(s);
                suma += liczba;

            }
        } return suma;

    }
}




