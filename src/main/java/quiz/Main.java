package quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File plikKategorii = wybierzKategorie();
        System.out.println("Wybrana kategoria: " + plikKategorii.getName());
        List<ZadaniaQuizowe> zadania = wczytajPlik(plikKategorii);
        graj(zadania);

    }

    private static void graj(List<ZadaniaQuizowe> zadania) {
        Collections.shuffle(zadania);

        int punkty = 0;
        for (int i =0; i < 10; i++) {
            ZadaniaQuizowe zadanie = zadania.get(i);
            System.out.println("Zadanie " + i + ": " + zadanie.pytanie);
            List<String> odpowiedzi = new ArrayList<String>(zadanie.odpowiedzi);
            String dobraOdpowiedz = odpowiedzi.get(0);
            Collections.shuffle(odpowiedzi);
            for (int j = 0; j < odpowiedzi.size(); j++) {
                System.out.println("  " + j + "] " + odpowiedzi.get(j));
            }
            Scanner scanner = new Scanner(System.in);
            int wyborUzytkownika = scanner.nextInt();
            String odpowiedzUzytkownika = odpowiedzi.get(wyborUzytkownika);
            if (odpowiedzUzytkownika.equals(dobraOdpowiedz)) {
                System.out.println("Dobra odpowiedź!");
                punkty++;
            } else {
                System.out.println("Zła odpowiedź!");
            }
        }
        System.out.println("Gratulacje, zdobyłeś " + punkty + " punktów!");
    }

    private static File wybierzKategorie() {
        System.out.println("Wybierz kategorię pytań:");

        File folder = new File("src/main/resources");
        File[] pliki = folder.listFiles();

        for (int i = 0; i < pliki.length; i++) {
            System.out.println(i + ") " + pliki[i].getName());
        }
        Scanner scanner = new Scanner(System.in);
        int wyborUzytkownika = scanner.nextInt();
        return pliki[wyborUzytkownika];
    }

    private static List<ZadaniaQuizowe> wczytajPlik(File plik) throws FileNotFoundException {
        Scanner scanner = new Scanner(plik);

        List<ZadaniaQuizowe> zadania = new ArrayList<ZadaniaQuizowe>();
        while (scanner.hasNextLine()) {
            ZadaniaQuizowe zadanie = new ZadaniaQuizowe();

            zadanie.pytanie = scanner.nextLine();

            //System.out.println("Pytanie: " + zadanie.pytanie);
            int ileOdp = Integer.parseInt(scanner.nextLine());
            List<String> mozliweOdpowiedzi = new ArrayList<String>(ileOdp);
            for (int i = 0; i < ileOdp; i++) {
                String odp = scanner.nextLine();
                //System.out.println("   Odpowiedź: " + odp);
                mozliweOdpowiedzi.add(odp);
            }
            zadanie.odpowiedzi = mozliweOdpowiedzi;
            zadania.add(zadanie);
        }

        return zadania;
    }

 }



