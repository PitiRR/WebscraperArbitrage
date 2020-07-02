import java.util.ArrayList;
import java.util.Map;

public class Graph {
    private int vertex;
    private ArrayList<Node> graphList = new ArrayList<Node>();

    public Graph(Map<String, double[]> dict) {
        // conscious contamination
        for ( Map.Entry<String, double[]> i : dict.entrySet()) {
            Node node = new Node(i.getKey());
            Edge edge = new Edge()
        }

    }

    insertEdge(fromnode, tonode, weight)
    {
        if a node does not yet exist, just add it
        if node already exists, get better value (make sure what it means exactly), choose larger value (and remember the source ID, where it came from)
    }
}