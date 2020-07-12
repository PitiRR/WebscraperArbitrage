import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getEURESP {
    /**
     * Extracts exchange data from eurochange.es. Ignores the first "row" (no html table used, hence classname is enough)
     * Changes comma formatting to double-friendly.
     * @param myGraph
     * @throws IOException
     */
    public static void GetEURESP(Graph myGraph) throws IOException {
        double tempBuyEuro;
        double tempSellEuro;
        String short_name;
        String url = "https://eurochange.es/cotizaciones";
        Document doc = Jsoup.connect(url).get();
        
        Elements body = doc.select("ul.tabla > li.info_entrada");
        for(Element e : body.select("li")) {
            //extract text value of selected elements, parse into doubles. Cantor uses German comma formatting, numbers are formatted into double format
            tempBuyEuro = Double.parseDouble(e.select("div.info_divisa > div:nth-child(2) > div:nth-child(3) > span:nth-child(1)").text().replaceAll(",", "."));
            tempSellEuro = Double.parseDouble(e.select("div:nth-child(1) > div:nth-child(3) > div:nth-child(3) > span:nth-child(1)").text().replaceAll(",", "."));
            short_name = e.select("div:nth-child(1) > div:nth-child(2) > span:nth-child(2) > span:nth-child(4) > label:nth-child(1) > em:nth-child(1) > span:nth-child(1)").text();
            myGraph.insertOrImproveEdge(new Edge("EUR", short_name, arbitrage.encodeRatio(tempSellEuro), url));
            myGraph.insertOrImproveEdge(new Edge(short_name, "EUR", arbitrage.encodeRatio(tempBuyEuro), url));
        }
    }
}