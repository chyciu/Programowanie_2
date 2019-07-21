package threads;

import com.google.common.util.concurrent.AtomicDouble;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Calkowanie {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        double wynik = calkujWielowatkowoPrawidlowo(0.0, 15.0, 100_000_000);
        long endTime = System.currentTimeMillis();
        System.out.println("Wielowatkowy "+ wynik + " obliczony w ciagu " + (endTime - startTime) + " ms");

        long startTime2 = System.currentTimeMillis();
        double wynik2 = calkuj(0.0, 15.0, 100_000_000);
        long endTime2 = System.currentTimeMillis();
        System.out.println(wynik2 +  " obliczony w ciagu " + (endTime2 - startTime2) + " ms");

    }

    private static double function(double x) {
        return (3 * Math.sin(x)) - (0.2 * Math.pow(x, 3)) + (3 * Math.pow(x, 2));
    }

    private static double calkujWielowatkowoPrawidlowo(final double startX, final double endX, final int ileCzesci) {
        ExecutorService threadPool = Executors.newFixedThreadPool(8);

        final AtomicDouble sumaCalekPrzedzialow = new AtomicDouble(0.0);

        final int ileDuzychPrzedzialow = 150;
        final double szerokoscDuzegoPrzedzialu = (endX-startX) / ileDuzychPrzedzialow;
        for (int i = 0 ; i < ileDuzychPrzedzialow; i++) {
            final double startDuzegoPrzedzialu = startX + i*szerokoscDuzegoPrzedzialu;
            final double koniecDuzegoPrzedzialu = startDuzegoPrzedzialu+szerokoscDuzegoPrzedzialu;

            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    double calkaPrzedzialu = calkuj(startDuzegoPrzedzialu, koniecDuzegoPrzedzialu, ileCzesci / ileDuzychPrzedzialow);
//                    System.out.println("Watek " + Thread.currentThread().getName()
//                            + " obliczył całkę od " + startDuzegoPrzedzialu + " do "
//                            + koniecDuzegoPrzedzialu + " i wyszło " + calkaPrzedzialu);
                    sumaCalekPrzedzialow.addAndGet(calkaPrzedzialu);
                }
            });
        }
        try {
            threadPool.shutdown();
            threadPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sumaCalekPrzedzialow.get();
    }

    private static double calkujWielowatkowo(final double startX, final double endX, final int ileCzesci) {
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        final double szerokoscProstokata = (endX - startX) / ileCzesci;

        final AtomicDouble sumaPolProstokatow = new AtomicDouble(0.0);
        for (double x = startX; x < endX; x += szerokoscProstokata) {
            final double finalX = x;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    double wysokosc = function(finalX + (0.5 * szerokoscProstokata));
                    double poleProstokata = szerokoscProstokata * wysokosc;
                    sumaPolProstokatow.addAndGet(poleProstokata);
                }
            });
        }

        try {
            threadPool.shutdown();
            threadPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sumaPolProstokatow.get();
    }

    private static double calkuj(double startX, double endX, int ileCzesci) {
        double szerokoscProstokata = (endX - startX) / ileCzesci; // 0.1

        double sumaPolProstokatow = 0.0;
        for (double x = startX; x < endX; x += szerokoscProstokata) {
            double wysokosc = function(x + (0.5 * szerokoscProstokata));
            double poleProstokata = szerokoscProstokata * wysokosc;
            sumaPolProstokatow += poleProstokata;
        }
        return sumaPolProstokatow;
    }
 }

