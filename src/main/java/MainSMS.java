
import java.util.Scanner;

public class MainSMS {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadź treść sms-a.");
        String trescSms = scanner.nextLine();
        System.out.println("Twój sms to: "+ trescSms);

        String[] slowa = trescSms.split(" ");

        for (int i = 0; i < slowa.length; i++) {
            String duzeSlowo = slowa[i];
            if (duzeSlowo.isEmpty()) {
                continue;
            }
            System.out.print(duzeSlowo.substring(0,1).toUpperCase() + duzeSlowo.substring(1).toLowerCase());
        }
    }
}
