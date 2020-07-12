import java.io.IOException;
import java.util.List;

public class arbitrage {
    /**
     * arbitrage.java finds negative cycle given currency exchanges scraped online, or for future development, provides a base and a guide to calculate a negative cycle for 
     * any graph that uses String source, String target and double weight.
     * @author Piotr Wojciechowski
     * @since 12/07/2020
     */
    public static void main(String args[]) throws IOException {
        
        Graph myGraph = new Graph();

        getPLN.GetPLN(myGraph);
        getEURESP.GetEURESP(myGraph);
        getSEK.GetSEK(myGraph);
        getEUR.GetEUR(myGraph);

        List<Edge> cycleOrNull = new NegativeCycleExtractor().negativeCycleExtractor(myGraph);
        if (cycleOrNull != null)
            System.out.println("Negative cycle found!");
        else
            System.out.println("No negative cycle found! :(");
    }
    public static double encodeRatio(double num) {
        /**
         * Encodes Edge weights. If one wants to test-run with a flat or random values, this method can alternate the values completely.
         */
        return -Math.log(num);
    }
}
//javac -cp ".;jsoup-1.13.1.jar" arbitrage.java