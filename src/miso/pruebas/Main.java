package miso.pruebas;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author juan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main();
        //m.consultaTopPagasFinanzas();
        m.consultaTopTodas();
    }

    public void consultaTopPagasFinanzas() {
        try {
            Document doc = Jsoup.connect("https://play.google.com/store/apps/category/FINANCE/collection/topselling_paid").timeout(0).get();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consultaTopTodas() {
        try {
            Set<String> hrefs = new HashSet<>();
            Document doc = Jsoup.connect("https://play.google.com/store/apps/category/FINANCE/collection/topselling_paid").timeout(0).get();
            Elements anchors = doc.getElementsByClass("card-click-target");
            for (Element e : anchors) {
                String href = "https://play.google.com/" + e.attr("href");
                hrefs.add(href);
            }

            for (String url : hrefs) {
                Document paginaApp = Jsoup.connect(url).timeout(0).get();
                String descripcion = paginaApp.select("[itemprop='description']").text();
                System.out.println(descripcion + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
