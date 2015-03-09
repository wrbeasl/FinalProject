/**
 * Created by chris on 3/5/15.
 *
 * requires JSoup library from http://jsoup.org/download
 *
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class dateGetter {
    public dateGetter() {}
    public String getDate(String ASIN) {
        try {
            doc = Jsoup.connect("http://www.amazon.com/exec/obidos/ASIN/" + ASIN).get();
        }
        catch (java.io.IOException e) {
            System.err.println(e);
        }

        Elements lines = doc.select("li");
        String rDate = null;

        for(Element e: lines) {

            if(e.toString().contains("Release Date")) {
                rDate = e.toString();
                rDate = rDate.substring(rDate.lastIndexOf("b>") + 2, rDate.lastIndexOf("<"));
            }

        }


    return rDate;
    }

    private static Document doc;


}
