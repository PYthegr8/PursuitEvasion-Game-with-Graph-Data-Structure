/*
 * Author: Papa Yaw Owusu Nti
 * Date: May 12th, 2024
 * Class: CS 231 B
 * Project: Project 8
 * Description:This class represents the driver for running simulations of the graph game. It sets up the game,and runs a simulation.
*/

public class Driver {

    public Driver(int n, double p) throws InterruptedException{
        // Create a random graph on which to play
        Graph graph = new Graph(n, p);

        // Create the pursuer and evader
        
        AbstractPlayerAlgorithm pursuer = new SmartAlgorithm(graph);
        AbstractPlayerAlgorithm evader = new MoveAwayPlayerAlgorithm(graph);

        // Have each player choose a starting location
        pursuer.chooseStart();
        // Since the evader has a harder objective, they get to play second
        // and see where the pursuer chose
        evader.chooseStart(pursuer.getCurrentVertex());

        // Make the display
        GraphDisplay display = new GraphDisplay(graph, pursuer, evader, 40);
        display.repaint();

        // Play the game until the pursuer captures the evader
        while (pursuer.getCurrentVertex() != evader.getCurrentVertex()){
            Thread.sleep(500);
            pursuer.chooseNext(evader.getCurrentVertex());
            display.repaint();
            if (pursuer.getCurrentVertex() != evader.getCurrentVertex()){
                Thread.sleep(500);
                evader.chooseNext(pursuer.getCurrentVertex());
                display.repaint();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        int n = 10;
        double p = .3;
        new Driver(n, p);
    }
}
