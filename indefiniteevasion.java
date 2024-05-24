public class indefiniteevasion {
    public static void main(String[] args) throws InterruptedException {
        // Create a graph from the custom graph file
        // Graph graph = new Graph("customgraph.txt");
        Graph graph = new Graph("custom_graph2.txt");


        // Create the pursuer and evader
        AbstractPlayerAlgorithm pursuer = new MoveTowardsPlayerAlgorithm(graph);
        AbstractPlayerAlgorithm evader = new MoveAwayPlayerAlgorithm(graph);

        // Have each player choose a starting location
        pursuer.chooseStart();
        evader.chooseStart(pursuer.getCurrentVertex());

        // Display the initial graph state
        // System.out.println("Initial Graph State:");
        // System.out.println(graph);
        GraphDisplay display = new GraphDisplay(graph, pursuer, evader, 40);
        display.repaint();


        // Play the game until the pursuer captures the evader
        while (!pursuer.getCurrentVertex().equals(evader.getCurrentVertex())) {
            // Purser's turn
            Thread.sleep(500);
            Vertex nextPursuerVertex = pursuer.chooseNext(evader.getCurrentVertex());
            System.out.println("Pursuer moves to: " + nextPursuerVertex);
            display.repaint();
            // Evader's turn
            Vertex nextEvaderVertex = evader.chooseNext(pursuer.getCurrentVertex());
            Thread.sleep(500);
            System.out.println("Evader moves to: " + nextEvaderVertex);
            display.repaint();
            // Check if the evader is caught
            if (nextPursuerVertex.equals(nextEvaderVertex)) {
                System.out.println("Evader got caught!");
                break;
            }
        }
    }
}
