package homework;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class CurrencyRate {

    public static void main(String[] args) throws IOException {

        CurrencyJson currencyJson = new CurrencyJson();

        URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/A/USD");
       // URL eur = new URL("http://api.nbp.pl/api/exchangerates/rates/A/EUR");
       // URL gbp = new URL("http://api.nbp.pl/api/exchangerates/rates/A/GBP");
      //  URL chf = new URL("http://api.nbp.pl/api/exchangerates/rates/A/CHF");
        URLConnection connection = url.openConnection();

        Scanner scanner = new Scanner(connection.getInputStream());
        String jsonCurrency = scanner.nextLine();
        System.out.println(jsonCurrency);

        Gson gson = new Gson();
        CurrencyJson.Rates rate = gson.fromJson(jsonCurrency, CurrencyJson.Rates.class);
        System.out.println(rate.mid);
       // System.out.println(currency.table);

       // currencyJson.rate();


    }
}
