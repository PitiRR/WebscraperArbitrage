import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getSEK {
    /**
     * !Important! This currency exchange uses inconsistent formatting. Some currencies are written as 1:1 unit, other times
     * it's 100:100 ratio. This messes up the purchasing power.
     * TODO: compare exchange value with a currency exchange API to see which exchange is inconsistent and fix it to 1:1 ratio.
     * @param myGraph
     * @throws IOException
     */
    public static void GetSEK(Graph myGraph) throws IOException {
        /**
         * Ignores first element of the table (headings), trims whitespaces
         */
        double tempBuySDK;
        double tempSellSDK;
        String shortName;
        String url = "https://www.swedbank.se/privat/rantor-priser-och-kurser/valutakurser-betalningar.html";
        Document doc = Jsoup.connect(url).get();
        
        Elements body = doc.select("tbody > tr:nth-child(-n+2)");
        //Skip first row
        for(Element e : body.select("tr")) {
            tempBuySDK = Double.parseDouble(e.select("td:nth-child(4)").text().trim());
            tempSellSDK = 1 / Double.parseDouble(e.select("td:nth-child(5)").text().trim());
            shortName = e.select("td:nth-child(3)").text().trim();

            myGraph.insertOrImproveEdge(new Edge("SEK", shortName, arbitrage.encodeRatio(tempSellSDK), url));
            myGraph.insertOrImproveEdge(new Edge(shortName, "SEK", arbitrage.encodeRatio(tempBuySDK), url));
        }
    }
}