import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getSEK {
    public static Map<String, double[]> GetSEK(Map<String, double[]> final_sek) throws IOException {
        List<Double> buy_sek = new ArrayList<Double>();
        List<Double> sell_sek = new ArrayList<Double>();
        List<String> short_sek = new ArrayList<String>();
        String url = "https://www.swedbank.se/privat/rantor-priser-och-kurser/valutakurser-betalningar.html";
        Document doc = Jsoup.connect(url).get();
        
        Elements body = doc.select("tbody > tr:nth-child(-n+2)");
        //Skip first row
        for(Element e : body.select("tr")) {
            //extract text value of selected elements, parse into doubles. Commas replaced with dots for formatting
            buy_sek.add(Double.parseDouble(e.select("td:nth-child(4)").text().trim()));
            sell_sek.add(Double.parseDouble(e.select("td:nth-child(5)").text().trim()));
            short_sek.add(e.select("td:nth-child(3)").text().trim());
        }
        while(!buy_sek.isEmpty()) {
            double[] toMap = { buy_sek.remove(0), sell_sek.remove(0) };
            final_sek.put(short_sek.remove(0), toMap);
        }
        return final_sek;
    }
}