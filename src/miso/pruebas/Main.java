package miso.pruebas;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author juan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://miso-4208-labs.gitlab.io/talleres/taller4.html").timeout(0).get();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
