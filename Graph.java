import java.util.HashMap;

public class Graph {
    /**
     * Object containing a nested HashMap. Read it as "HashMap<source, HashMap<target, Edge>>"
     */
    public HashMap<String, HashMap<String, Edge>> edgeSet = new HashMap<String, HashMap<String, Edge>>();
    // performance-critical

    public void insertOrImproveEdge(Edge edge) {
        /**
         * Used when extracting data to update (notice this is HashMap not a HashSet) a better edge than an already existing one, if one exists. 
         * For example, getEURESP and getEUR both are base euro, but one may have a better ratio than the other. This method takes care of this.
         */
        if (edgeSet.containsKey(edge.from)) {
            //if there is an edge with the same source
            if (edgeSet.get(edge.from).containsKey(edge.to)) {
                //if there is an edge with the same source and the same target
                if (edge.weight > edgeSet.get(edge.from).get(edge.to).weight) {
                    //choose the edge with better conversion rate (weight)
                    edgeSet.get(edge.from).put(edge.to, edge);
                }
            } else {
                //if source exists, but target doesn't
                edgeSet.get(edge.from).put(edge.to, edge);
            }
        } else {
            //if source node doesn't exist at all
            edgeSet.put(edge.from, new HashMap<String, Edge>());
            edgeSet.get(edge.from).put(edge.to, edge);
        }
    }
}