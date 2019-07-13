package languageReader;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.errors.APIError;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainLanguageReader {

    public static void main(String[] args) throws APIError, FileNotFoundException {

        DetectLanguage.apiKey = "ded8e69ae590ff8afcd10319ab394b9f ";

        File folder = new File("src\\main\\resources");
        File[] files = folder.listFiles();

        //  File file = new File("src\\main\\resources\\chunichi.txt");

         for (File file : files) {

            Scanner scanner = new Scanner(file);

            String całyTekst = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //   System.out.println("linijka: " + line);
                całyTekst += line;
            }
            String language = DetectLanguage.simpleDetect(całyTekst);
            System.out.println("Dokument:  " + file.getName() + "  /  język: " + language);
        }
    }
}
