package threads;


import java.util.concurrent.CountDownLatch;

public class JasAndMalgosia {

    private static CountDownLatch latch;

    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread jas = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Sniadanie przygotownaie i jedzenie Jasia");
                    Thread.sleep(5000);
                    System.out.println("Prysznic Jasia");
                    Thread.sleep(3000);
                    System.out.println("Ubranie się Jasia");
                    Thread.sleep(1000);
                    System.out.println("Wyjście na zakupy Jasia");
                    Thread.sleep(15000);
                    System.out.println("Granie na konsoli Jasia");
                    Thread.sleep(5000);
                    System.out.println("Koniec dnia Jasia");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread malgosia = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Poranne bieganie Gosi");
                    Thread.sleep(6000);
                    System.out.println("Prysznic Gosi");
                    Thread.sleep(2000);
                    System.out.println("Jedzenie śniadanie Gosi");
                    Thread.sleep(1000);
                    System.out.println("Ubranie się Gosi");
                    Thread.sleep(1000);
                    System.out.println("Spotkanie z koleżanką Gosi");
                    Thread.sleep(25000);
                    System.out.println("Koniec dnia Gosi");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        jas.start();
        malgosia.start();

//        try {
//            jas.join();
//            malgosia.join();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//            System.out.println("Koniec dnia dla obojga.");


    }
 }


