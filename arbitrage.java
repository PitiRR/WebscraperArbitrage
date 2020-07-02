import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class arbitrage {
    public static void main(String args[]) throws IOException {
        
        Map<String[], double[]> final_pln = new HashMap<String, double[]>();
        final_pln = getPLN.GetPLN(final_pln);
        printDict(final_pln);

        Map<String[], double[]> final_sek = new HashMap<String, double[]>();
        final_sek = getSEK.GetSEK(final_sek);
        printDict(final_sek);

        Map<String[], double[]> final_eur = new HashMap<String, double[]>();
        final_eur = getEUR.GetEUR(final_eur);
        printDict(final_eur);
    }
    
    public static void printDict (Map<String, double[]> dict) {
        dict.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + ", " + Arrays.toString(entry.getValue()));  
        });
    }
}
//javac -cp ".;jsoup-1.13.1.jar" arbitrage.java