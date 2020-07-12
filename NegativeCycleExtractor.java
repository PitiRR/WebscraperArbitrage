import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class NegativeCycleExtractor {
    /**
     * Finds negative cycle using Bellman-Ford algorithm in a graph. 
     * @return List<Edge> 
     */
    private HashMap<String, Double> distanceSet = new HashMap<String, Double>();
    private Graph graph;
    private HashSet<String> newLayer = new HashSet<String>();
    private HashSet<String> currentLayer = new HashSet<String>();
    private HashMap<String, Edge> predecessorMap = new HashMap<String, Edge>();
    private HashSet<String> visitedSet = new HashSet<String>();
    private String currentNode;
    private List<Edge> cycle = new ArrayList<Edge>();
    
    public List<Edge> negativeCycleExtractor(Graph edges) {
        /** 
         * Initializes graph, distanceSet and starts the search.
         */
        this.graph = edges;
        for (String entry : edges.edgeSet.keySet())
            distanceSet.put(entry, 0.0);
        return extractNegativeCycleIfOneExists();
    }

    public List<Edge> extractNegativeCycleIfOneExists() {
        /**
         * Searches a negative cycle using Bellman-Ford algorithm. If one exists, returns it. Else, returns null list. Also calls a custom print function.
         * To save memory and time, uses hashsets and keys (nodes / edge.from) for the lookup and update complexity.
         * Reinitializes many variables per iteration, most notably predecessorMap as finding the negative cycle is based on it.
         * Switches layers so as not to repeat nodes and save memory this way.
         */
        for (String entry : graph.edgeSet.keySet()) {
            newLayer.add(entry);
        }
        int processedLayers = 0;
        boolean existsNegativeCycle = false;
        int vertexCount = graph.edgeSet.keySet().size();
        System.out.println(vertexCount);
        while (!newLayer.isEmpty()) {   
            predecessorMap.clear();
            currentLayer = newLayer;
            newLayer = new HashSet<String>();

            for (var entry : currentLayer) {
                for (var outgoingEdge : graph.edgeSet.get(entry).values()) {
                    var relaxed = relaxEdge(outgoingEdge);
                    if (relaxed && processedLayers > vertexCount) {
                        existsNegativeCycle = true;
                        currentNode = outgoingEdge.to;
                    }
                }
            }
            
            if (processedLayers > vertexCount)
                break;
            else
                processedLayers++;
        }

        System.out.println("existsNegativeCycle: " + existsNegativeCycle);
        //control statement

        if (existsNegativeCycle) {
            List<Edge> cycle = getArbitrage();
            printArbitrage(cycle);
            return cycle;
        }
        return null;
    }

    private boolean relaxEdge(Edge edge) {
        /**
         * If distance can be improved, relaxes edge and puts it in predecessor map as it may belong to a negative cycle.
         */
        double newToDistance = distanceSet.get(edge.from) + edge.weight;
        if (newToDistance < distanceSet.get(edge.to)) {
            distanceSet.put(edge.to, newToDistance);
            newLayer.add(edge.to);
            predecessorMap.put(edge.to, edge);
            return true;
        }
        return false;
    }
    private List<Edge> getArbitrage() {
        /**
         * Finds negative cycle (repeated node in a set, called sentinel). 
         * Reverses the list for convienience of reading; top to bottom.
         * Note to future self: Collections.reverse() returns void and alternates the param. Don't use it in print statements
         */
        while (!visitedSet.contains(currentNode)) {
            visitedSet.add(currentNode);
            currentNode = predecessorMap.get(currentNode).from;
        }
        String sentinel = currentNode;
        System.out.println("sentinel: " + sentinel);
        currentNode = predecessorMap.get(currentNode).from;
        cycle.add(predecessorMap.get(sentinel));
        while (currentNode != sentinel) {
            cycle.add(predecessorMap.get(currentNode));
            currentNode = predecessorMap.get(currentNode).from;
        }
        Collections.reverse(cycle);
        return cycle;
    }

    private void printArbitrage(List<Edge> cycle) {
        /**
         * Prints the arbitrage. ExchangeRatio and Total Exchange Power prints are for problems check: If they are unusually high,
         * it means that something went wrong such as an inconsistency. See getSEK.java.
         */
        double exchangeRatio = 1.0;
        for (Edge edge : cycle) {
            System.out.print(edge.from);
            System.out.print(" -> ");
            System.out.print(edge.to);
            System.out.print(" via ");
            System.out.println(Math.exp(-edge.weight));
            System.out.print(" in ");
            System.out.print(edge.cantor);
            System.out.println("\n");
            exchangeRatio *= Math.exp(-edge.weight);
        }
        System.out.println("Total exchange power: " + exchangeRatio);
    }
}