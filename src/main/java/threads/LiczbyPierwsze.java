package threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LiczbyPierwsze {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        LiczbyPierwsze liczbyPierwsze = new LiczbyPierwsze();
      //  liczbyPierwsze.checkNumbers();
        liczbyPierwsze.checkByExecutor();
    }


    public static boolean isPrime(long number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        for (long i = 3; i < Math.sqrt(number)-1; i+=2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void checkNumbers() throws FileNotFoundException {

        File filePierwsze = new File("src\\main\\resources\\numbers.txt");

        Scanner scanner = new Scanner(filePierwsze);
        long startTime = System.currentTimeMillis();
        int counter = 0;

        while (scanner.hasNextLong()) {
            long liczba = scanner.nextLong();
            isPrime(liczba);

            if (isPrime(liczba)) {
               // System.out.println(liczba);
                counter++;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Ilość liczb to: " + counter);
        System.out.println("Total czas działania: " + (endTime - startTime));
    }



    public void checkByExecutor () throws FileNotFoundException, InterruptedException {

        File filePierwsze = new File("src\\main\\resources\\numbers.txt");

        Scanner scanner = new Scanner(filePierwsze);
        long startTime = System.currentTimeMillis();

        ExecutorService threadPool = Executors.newFixedThreadPool(6);

        final AtomicInteger ilePierwszych = new AtomicInteger(0);

        while (scanner.hasNextLong()) {
            long liczba = scanner.nextLong();
            //isPrime(liczba);

            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    if (isPrime(liczba)) {
                        ilePierwszych.incrementAndGet();
                    }
                }
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);

        long endTime = System.currentTimeMillis();
        System.out.println("Total czas działania: " + (endTime - startTime) + " ms.");
        System.out.println("W pliku jest: " + ilePierwszych.get() + " liczb pierwszych");

    }
}
