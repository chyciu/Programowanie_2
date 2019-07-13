import java.util.Scanner;

public class mainSMSDwa {

    public static void main(String[] args) {
        System.out.println("Podaj treść SMSa: ");
        Scanner scanner = new Scanner(System.in);
        String naszSms = scanner.nextLine();
        System.out.println("Twój SMS to: " + naszSms);

        String skroconySMS = skrocSMS(naszSms);

        System.out.println("Skrocony SMS: " + skroconySMS);
    }

    public static String skrocSMS(String oryginalnySMS) {
        if (oryginalnySMS == null) {
            return null;
        }
        String[] slowa = oryginalnySMS.split(" ");

        String skroconySMS = "";
        for (String slowo : slowa) {
            if (slowo.isEmpty()) {
                continue;
            }
            String pierwszaLitera = slowo.substring(0, 1).toUpperCase();
            String resztaLiter = slowo.substring(1).toLowerCase();
            String caleSlowo = pierwszaLitera + resztaLiter;
//            System.out.println("Słowo: " + caleSlowo);
            skroconySMS = skroconySMS + caleSlowo;
        }
        return skroconySMS;
    }
}
