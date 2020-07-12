import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getPLN {
    /**
     * Extracts exchange values from internetowykantor.pl.
     * Fixes Continental European digit comma separator.
     * Adds extracted edges to the graph.
     * @param myGraph
     * @throws IOException
     */
    public static void GetPLN (Graph myGraph) throws IOException {
        double tempBuyPLN;
        double tempSellPLN;
        String shortName;
        String url = "https://internetowykantor.pl/kursy-walut/";
        Document doc = Jsoup.connect(url).get();
        
        Elements body = doc.select("tbody");
        for(Element e : body.select("tr")) {
            tempBuyPLN = Double.parseDouble(e.select("td.currency_table_sell").text().replace(",", "."));
            tempSellPLN = 1 / Double.parseDouble(e.select("td.currency_table_buy").text().replace(",", "."));
            shortName = e.select("a.bem-rate-table__currency-anchor").text();

            myGraph.insertOrImproveEdge(new Edge("PLN", shortName, arbitrage.encodeRatio(tempSellPLN), url));
            myGraph.insertOrImproveEdge(new Edge(shortName, "PLN", arbitrage.encodeRatio(tempBuyPLN), url));
        }
    }
}