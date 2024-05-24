public class GraphTest {

    public static void testGraphFromFile() {
        Graph graph = new Graph("graph1.txt");
        graph.toString();
        // Check if the graph is constructed
        assert graph != null : "Graph not constructed";

        // Check if the graph has the expected number of vertices
        assert graph.size() == 4: "Incorrect number of vertices";

        // Check if the graph has the expected number of edges
        int expectedNumEdges = 4; // Number of edges in graph1.txt
        int actualNumEdges = 0;
        for (Edge edge : graph.getEdges()) {
            actualNumEdges++;
        }
        System.out.println("txt file has "+actualNumEdges + " edges");
        assert actualNumEdges == expectedNumEdges : "Incorrect number of edges";
    }

    public static void ProbabilityConstructorTest() {
        int n = 4;
        Graph graph = new Graph(n,0.5);
        graph.toString();
        // Check if the graph is constructed
        assert graph != null : "Graph not constructed";

        // Check if the graph has the expected number of vertices
        assert graph.size() == n: "Incorrect number of vertices";

        // Check if the graph has the expected number of edges
        int expectedNumEdges = (n * (n-1)) /2; // Number of edges in graph1.txt
        // System.out.println(expectedNumEdges);
        int actualNumEdges = 0;
        for (Edge edge : graph.getEdges()) {
            actualNumEdges++;
        }
        System.out.println("My Graph has "+actualNumEdges + " edges");
        assert actualNumEdges <= expectedNumEdges : "Incorrect number of edges";
    }



    public static void main(String[] args) {
        testGraphFromFile();
        ProbabilityConstructorTest();
        System.out.println("All tests passed successfully!");
    }
}
