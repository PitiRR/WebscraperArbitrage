import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getEUR {
    /**
     * Extracts exchange values from reisebank.de and adds edges to the graph.
     * Notice dots are removed first. This is because this ferex uses point as a digit grouping given small enough number. 
     * ReiseBank.de uses Continental European comma formatting, so numbers are formatted into double format this way.
     * @param myGraph
     * @throws IOException
     */
    public static void GetEUR(Graph myGraph) throws IOException {
        double tempBuyEuro;
        double tempSellEuro;
        String shortName;
        String url = "https://www.reisebank.de/reisegeld";
        Document doc = Jsoup.connect(url).get();
        
        Elements body = doc.select("div.tripmoneySection__products");
        for(Element e : body.select("a")) {
            tempBuyEuro = Double.parseDouble(e.select("div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > span:nth-child(1)").text().replaceAll("\\.", "").replaceAll(",", "."));
            tempSellEuro = Double.parseDouble(e.select("div:nth-child(3) > div:nth-child(2) > div:nth-child(3) > span:nth-child(1)").text().replaceAll("\\.", "").replaceAll(",", "."));
            shortName = e.select("div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > span:nth-child(2)").text();
            myGraph.insertOrImproveEdge(new Edge("EUR", shortName, arbitrage.encodeRatio(tempSellEuro), url));
            myGraph.insertOrImproveEdge(new Edge(shortName, "EUR", arbitrage.encodeRatio(tempBuyEuro), url));
        }
    }
}