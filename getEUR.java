import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getEUR {
    public static Map<String, double[]> GetEUR(Map<String, double[]> final_eur) throws IOException {
        List<Double> buy_eur = new ArrayList<Double>();
        List<Double> sell_eur = new ArrayList<Double>();
        List<String> short_eur = new ArrayList<String>();
        String url = "https://www.reisebank.de/reisegeld";
        Document doc = Jsoup.connect(url).get();
        
        Elements body = doc.select("div.tripmoneySection__products");
        for(Element e : body.select("a")) {
            //extract text value of selected elements, parse into doubles. Commas replaced with dots for formatting
            buy_eur.add(Double.parseDouble(e.select("div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > span:nth-child(1)").text().replaceAll(",", ".")));
            sell_eur.add(Double.parseDouble(e.select("div:nth-child(3) > div:nth-child(2) > div:nth-child(3) > span:nth-child(1)").text().replaceAll("\\.", "").replaceAll(",", ".")));
            short_eur.add(e.select("div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > span:nth-child(2)").text());
        }
        while(!buy_eur.isEmpty()) {
            double[] toMap = { buy_eur.remove(0), sell_eur.remove(0) };
            final_eur.put(short_eur.remove(0), toMap);
        }
        return final_eur;
    }
}