package cats;

import com.google.gson.Gson;
import net.gpedro.integrations.slack.SlackAction;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class CatsImages {

    public static void main(String[] args) throws IOException {

        CatsImages catsImages = new CatsImages();
        catsImages.imageLoader();
     //   catsImages.imageToSlack();


    }

    public void imageLoader  () throws IOException {

        int dzialaj = 10;

        while (dzialaj > 0) {

             URL urlCat = new URL("https://aws.random.cat/meow");
             URLConnection connection = urlCat.openConnection();

             Scanner scanner = new Scanner(connection.getInputStream());
             String jsonText = scanner.nextLine();
             System.out.println(jsonText);

             Gson gson = new Gson();
             CatJson cat = gson.fromJson(jsonText, CatJson.class);
             System.out.println("Link to kotka: " + cat.catUrl);

            int dotIndex = cat.catUrl.lastIndexOf('.');
            String extension = cat.catUrl.substring(dotIndex);


            BufferedImage image = ImageIO.read(new URL(cat.catUrl));
            ImageIO.write(image, "jpg", new File("kot" + dzialaj + "." + extension));
            dzialaj--;
            }
    }

    public void imageToSlack () throws IOException {

//        SlackApi slackApi = new SlackApi("urlSlack");
//        slackApi.call(new SlackMessage("test"));
//        SlackAttachment urlSlackAttachment = new SlackAttachment("urlSlack");
//        urlSlackAttachment.setColor("#00");


        URL urlCat = new URL("https://aws.random.cat/meow");
        URLConnection connection = urlCat.openConnection();

        Scanner scanner = new Scanner(connection.getInputStream());
        String jsonText = scanner.nextLine();
        System.out.println(jsonText);

        Gson gson = new Gson();
        CatJson cat = gson.fromJson(jsonText, CatJson.class);
        System.out.println("Link to kotka: " + cat.catUrl);

        int dotIndex = cat.catUrl.lastIndexOf('.');
        String extension = cat.catUrl.substring(dotIndex);


        BufferedImage image = ImageIO.read(new URL(cat.catUrl));
     //   ImageIO.write(image, "jpg", new File("kot" + extension));


    }


}
