package homework;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class CurrencyRate {

    public static void main(String[] args) throws IOException {

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.checkExRate("USD", Currency.MID);

    }


    public void checkExRate (String currencyName, Enum rate) throws IOException {

        String tabela = "";

        if (rate == Currency.MID) {
            tabela = "A";
        }

        URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/"+ tabela +"/"+ currencyName +" ");
        URLConnection connection = url.openConnection();

        Scanner scanner = new Scanner(connection.getInputStream());
        String jsonCurrency = scanner.nextLine();
        System.out.println(jsonCurrency);

        Gson gson = new Gson();
        CurrencyJson currencyJ = gson.fromJson(jsonCurrency, CurrencyJson.class);
        Rates rateClass = gson.fromJson(jsonCurrency,Rates.class);
        System.out.println(rateClass.mid);

        // System.out.println(currency.table);

    }
}
