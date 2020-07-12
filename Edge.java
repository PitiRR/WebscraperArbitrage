class Edge {
    String from; //startinVertex, source
    String to; //targetVertex, target
    double weight;
    String cantor;

    public Edge(String from, String to, double weight, String cantor) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.cantor = cantor;
    }
}