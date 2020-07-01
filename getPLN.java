import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getPLN {
    public static Map<String, double[]> GetPLN (Map<String, double[]> final_pln) throws IOException {

        List<Double> buy_pln = new ArrayList<Double>();
        List<Double> sell_pln = new ArrayList<Double>();
        List<String> short_pln = new ArrayList<String>();
        String url = "https://internetowykantor.pl/kursy-walut/";
        Document doc = Jsoup.connect(url).get();
        
        Elements body = doc.select("tbody");
        //System.out.println(body.select("tr").size());
        for(Element e : body.select("tr")) {
            //extract text value of selected elements, parse into doubles. Commas replaced with dots for formatting
            buy_pln.add(Double.parseDouble(e.select("td.currency_table_buy").text().replace(",", ".")));
            sell_pln.add(Double.parseDouble(e.select("td.currency_table_sell").text().replace(",", ".")));
            short_pln.add(e.select("a.bem-rate-table__currency-anchor").text());
        }

        while(!buy_pln.isEmpty()) {
            double[] toMap = { buy_pln.remove(0), sell_pln.remove(0) };
            final_pln.put(short_pln.remove(0), toMap);
        }
        return final_pln;
    }
}