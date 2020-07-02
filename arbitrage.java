import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class arbitrage {
    public static void main(String args[]) throws IOException {
        
        Map<String, double[]> final_pln = new HashMap<String, double[]>();
        final_pln = getPLN.GetPLN(final_pln);
        System.out.println("Printing PLN: \n");
        printDict(final_pln);

        Map<String, double[]> final_sek = new HashMap<String, double[]>();
        final_sek = getSEK.GetSEK(final_sek);
        System.out.println("Printing SEK: \n");
        printDict(final_sek);

        Map<String, double[]> final_eur = new HashMap<String, double[]>();
        final_eur = getEUR.GetEUR(final_eur);
        System.out.println("Printing EUR: \n");
        printDict(final_eur);
        //make hashmaps array of strings
    }
    
    public static void printDict (Map<String, double[]> dict) {
        dict.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + ", " + Arrays.toString(entry.getValue()));  
        });
    }
}
//javac -cp ".;jsoup-1.13.1.jar" arbitrage.java
//on mac: use : instead of ;