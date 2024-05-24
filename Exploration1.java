/*
 * Author: Papa Yaw Owusu Nti
 * Date: May 12th, 2024
 * Class: CS 231 B
 * Project: Project 8
 * Description:This class runs multiple simulations of the graph game. It sets up the game,and runs a simulation and calculates statistics.
*/

public class Exploration1 {

    public static void main(String[] args) throws InterruptedException {
        // Define the settings for number of nodes and probability of an edge being drawn
        int[] nodeSettings = { 10, 20, 30}; // Example settings for number of nodes
        double[] probabilitySettings = {0.3,0.5,0.7};
        // Number of simulations to conduct for each setting
        int numSimulations = 1; // Adjust as needed

        // Loop through each setting and conduct simulations
        for (int n : nodeSettings) {
            for (double p : probabilitySettings) {
                int captures = 0;
                int totalSteps = 0;

                // Run simulations for the current setting
                for (int i = 0; i < numSimulations; i++) {
                    // Create a random graph for the current setting
                    Graph graph = new Graph(n, p);

                    // Create the pursuer and evader
                    AbstractPlayerAlgorithm pursuer = new MoveTowardsPlayerAlgorithm(graph);
                    AbstractPlayerAlgorithm evader = new MoveAwayPlayerAlgorithm(graph);

                    // Have each player choose a starting location
                    pursuer.chooseStart();
                    evader.chooseStart(pursuer.getCurrentVertex());


                    GraphDisplay display = new GraphDisplay(graph, pursuer, evader, 40);
                    display.repaint();
                    while (pursuer.getCurrentVertex() != evader.getCurrentVertex()) {
                        Thread.sleep(500);
                        pursuer.chooseNext(evader.getCurrentVertex());
                        display.repaint();
                        if (pursuer.getCurrentVertex() != evader.getCurrentVertex()) {
                            Thread.sleep(500);
                            evader.chooseNext(pursuer.getCurrentVertex());
                            display.repaint();
                        }
                        totalSteps++; 
                    }

                    // Check if pursuer caught evader and calculate steps
                    if (pursuer.getCurrentVertex() == evader.getCurrentVertex()) {
                        captures++;
                    }
                    
                    
                }

                // Calculate average percentage of captures and average number of steps
                double averageCapturePercentage = (double) captures / numSimulations * 100.0;
                double averageSteps = (double) totalSteps / numSimulations;

                // Print results for the current setting
                System.out.println("For " + n + " nodes and probability " + p + ":");
                System.out.println("Average capture percentage: " + averageCapturePercentage + "%");
                System.out.println("Average number of steps: " + averageSteps);
                System.out.println();
            }
        }
    }
}
