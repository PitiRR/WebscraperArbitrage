class Edge {
    String vertex1;
    String vertex2;
    double weight;
    String cantor;

    public Edge(String vertex1, String vertex2, double weight, String cantor) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
        this.cantor = cantor;
    }
    public String getToNode() {
        return vertex2;
    }
    public String getFromNode() {
        return vertex1;
    }
}