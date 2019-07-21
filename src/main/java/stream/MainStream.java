package stream;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStream {

    public static void main(String[] args) {

        long count = DataCollections.getSurnames().stream()
                .count();
        System.out.println(count);

        // Poniżej - Wszystkie nazwiska o długości co najwyżej 4 znaków, zapisane wielkimi literami.
        // przy "UpperCase" zamiemilismy lambdę na metodę z "::".

        List<String> krotkieNazwiska = DataCollections.getSurnames().stream()
                .filter(nazwisko -> nazwisko.length() <= 4)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(krotkieNazwiska);

        // Wszystkie nazwiska zaczynające się na literę 'B'

        List<String> nazwiskaNaB = DataCollections.getSurnames().stream()
                .filter(nazwisko -> nazwisko.startsWith("B"))
                .collect(Collectors.toList());
        System.out.println(nazwiskaNaB);

        // Początkowe trzy litery wszystkich nazwisk, bez powtórzeń, z małych liter

        List<String> pierwszeTrzyLiteryMale = DataCollections.getSurnames().stream()
                .map(nazwisko -> nazwisko.substring(0,3).toLowerCase())
                .distinct()
                .collect(Collectors.toList());

        pierwszeTrzyLiteryMale.forEach(System.out::println);

        // 10 najdłuższych nazwisk, posortowanych malejąco według długości

        List<String> tenLongestSurnamesSorted = DataCollections.getSurnames().stream()
                .sorted((nazwisko1, nazwisko2)->nazwisko2.length()-nazwisko1.length())
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(tenLongestSurnamesSorted);

        // 20 najkrótszych nazwisk, posortowanych według ostatniej litery


        List<String> twentyShortestSurnames = DataCollections.getSurnames().stream()
                .sorted((nazwisko1, nazwisko2)->nazwisko1.length()-nazwisko2.length())
                .limit(20)
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        String s1 = o1.substring(o1.length()-1);
                        String s2 = o2.substring(o2.length()-1);
                        return s1.compareToIgnoreCase(s2) ;
                    }
                })
                .collect(Collectors.toList());

        twentyShortestSurnames.forEach(System.out :: println);

       // Jaka litera pojawia się najcześciej we wszystkich nazwiskach?

        Map<Character, Integer> czestoscWystapienSlow = new HashMap<>();
        for (String surname : DataCollections.getSurnames()) {
            for (char literka : surname.toLowerCase().toCharArray()) {
                if (czestoscWystapienSlow.containsKey(literka)) {
                    Integer dotychczasBylaTyleRazy = czestoscWystapienSlow.get(literka);
                    dotychczasBylaTyleRazy++;
                    czestoscWystapienSlow.put(literka, dotychczasBylaTyleRazy);
                } else {
                    // litera pojawia sie pierwszy raz
                    czestoscWystapienSlow.put(literka, 1);
                }
            }
        }

        Map<String, Integer> countLetters = DataCollections.getSurnames().stream()
                .flatMap(new Function<String, Stream<String>>() {
                    @Override
                    public Stream<String> apply(String nazwisko) {
                        // "KOWAL" => "K", "O", "W", "A", "L"
                        return Arrays.stream(nazwisko.toLowerCase().split(""));
                    }
                })
                .collect(Collectors.toMap(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s; // klucz nowego dodanego elementu - czyli ta literka
                    }
                }, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return 1; // wartosc nowego dodanego elementu - ile razy sie pojawil (jest nowy, więc dopiero raz)
                    }
                }, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer aktualnaWartosc, Integer wartoscNowegoElementu) {
                        return aktualnaWartosc+wartoscNowegoElementu;
                    }
                }));
        System.out.println(countLetters);

    }


}

