package miso.pruebas;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Juan Camilo Gaviria Acosta - 201528434
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main();
        m.consultaTopPagasFinanzas();
    }

    public void consultaTopPagasFinanzas() {
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

                //Descripcion
                Elements claseTitulo = paginaApp.getElementsByClass("id-app-title");
                System.out.println("\nTitulo: " + claseTitulo.get(0).text());

                //Numero de raitings
                Elements claseRaitings = paginaApp.getElementsByClass("rating-count");
                System.out.println("Numero de raitings: " + claseRaitings.get(0).text());

                //Score
                Elements claseScore = paginaApp.getElementsByClass("score");
                System.out.println("Score: " + claseScore.get(0).text());

                //Descripcion
                String descripcion = paginaApp.select("[itemprop='description']").text();
                System.out.println("Descripcion: " + descripcion);

                //Cambios recientes
                Elements claseCambiosRecientes = paginaApp.getElementsByClass("recent-change");
                System.out.println("Cambios recientes: " + claseCambiosRecientes.get(0).text());

                //Ratings con 5 estrellas
                Elements claseCincoEstrellas = paginaApp.getElementsByClass("rating-bar-container five");
                System.out.println("Ratings con 5 estrellas: " + claseCincoEstrellas.get(0).text());

                //Ratings con 4 estrellas
                Elements claseCuatroEstrellas = paginaApp.getElementsByClass("rating-bar-container four");
                System.out.println("Ratings con 4 estrellas: " + claseCuatroEstrellas.get(0).text());

                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
