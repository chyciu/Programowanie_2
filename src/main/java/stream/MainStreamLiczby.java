package stream;

import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainStreamLiczby {

    public static void main(String[] args) {

       // Ile jest liczb parzystych?

        System.out.println(DataCollections.getNumbers(10_000).stream()
                .filter(liczba -> liczba % 2  == 0)
                .count());

        // Ile jest liczb pięciocyfrowych?

        System.out.println(DataCollections.getNumbers(10_000).stream()
                .filter(liczba -> liczba >= 10_000 && liczba < 10_000)
                .count());

        // Jaka jest największa i najmniejsza liczba?

        Optional<Integer> minimum = (DataCollections.getNumbers(10_000).stream()
                .sorted().findFirst());
        System.out.println(minimum.get());

        System.out.println(DataCollections.getNumbers(10_000).stream()
                .max(Integer::compareTo).get());

        //Jaka jest średnia wszystkich liczb?

        DataCollections.getNumbers(10_000).stream()
                .mapToInt(i -> i)
                .summaryStatistics();

        //Jaka cyfra pojawia się najczęściej we wszystkcih liczbach?

        Map<Integer, Integer> integerMap = DataCollections.getNumbers(10000).stream()
                .flatMap(new Function<Integer, Stream<Integer>>() {
                    @Override
                    public Stream<Integer> apply(Integer integer) {
                        String liczbaString = integer.toString();
                        String[] liczbaPoszatkowana = liczbaString.split("");
                        Stream.Builder builder = Stream.builder();
                        for (int i = 0; i < liczbaPoszatkowana.length; i++) {
                            int digit = Integer.parseInt(liczbaPoszatkowana[i]);
                            builder.add(digit);
                        }
                        return builder.build();
                    }

                })
                .collect(Collectors.toMap(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer s) {
                        return s; // klucz nowego dodanego elementu - czyli ta literka
                    }
                }, new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer s) {
                        return 1; // wartosc nowego dodanego elementu - ile razy sie pojawil (jest nowy, więc dopiero raz)
                    }
                }, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer aktualnaWartosc, Integer wartoscNowegoElementu) {
                        return aktualnaWartosc + wartoscNowegoElementu;
                    }
                }));

        System.out.println(integerMap);


    }
}



