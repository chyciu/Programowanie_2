package chuck_norris;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ChuckNorris {

    public static void main(String[] args) throws IOException {

        URL url = new URL ("https://api.chucknorris.io/jokes/random");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla");

        Scanner scanner = new Scanner(connection.getInputStream());
        String jasonText = scanner.nextLine();
        System.out.print(jasonText);

        Gson gson = new Gson();
        JokeNorris joke = gson.fromJson(jasonText, JokeNorris.class);
        System.out.println(joke.value);

    }



}
