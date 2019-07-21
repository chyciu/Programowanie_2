package threads;

public class Watek {

    private static volatile boolean stopeRequested = false;

    public static void main(String[] args) throws Exception {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                long i = 0;
                while (!stopeRequested) {
                    i++;
                    System.out.println(i);
                }
              //  System.out.println(i);
            }

        });

        t.start();
        Thread.sleep(1000);
        stopeRequested = true;
    }
}
