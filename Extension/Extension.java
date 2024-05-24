/*
Name: Papa Yaw
Course: CS231
Section: B
Project: Pursuit Evasion on a Graph
Date: 05/13/2024
Description: This extension simulates multiple pursuers on the graph
*/
public class Extension {
    

    //Create multiple pursuers

    /**
     * @param n
     * @param p
     * @param multiPursuers
     * @throws InterruptedException
     * Create multiple pursuers
     */
    public Extension(int n, Double p, boolean multiPursuers) throws InterruptedException {

        /**
         * Initialize graph
         */
        Graph graph = new Graph(n, p);

        //if multiPursuers is true
        if (multiPursuers) {

            //An array to hold pursuer objects
            AbstractPlayerAlgorithm[] pursuers = new AbstractPlayerAlgorithm[2];

            // Create the pursuer and evader
            for (int i = 0; i < 2; i++) {
                pursuers[i] = new MoveTowardsPlayerAlgorithm(graph);
            }
       
            MoveAwayPlayerAlgorithm evader = new MoveAwayPlayerAlgorithm(graph);

            // Have each player choose a starting location
            for (AbstractPlayerAlgorithm pursuer: pursuers) {
                pursuer.chooseStart();
            }

            // Since the evader has a harder objective, they get to play second
            // and see where the pursuer chose
            evader.chooseStart(pursuers[0].getCurrentVertex());

            //display graph
            GraphDisplay display = new GraphDisplay(graph, pursuers[0], pursuers[1], evader, 40);
            display.repaint();


            //Conditions for gameplay
            while ((pursuers[0].getCurrentVertex() != evader.getCurrentVertex())
                    || (pursuers[1].getCurrentVertex() != evader.getCurrentVertex())) {
                Thread.sleep(500);
                pursuers[0].chooseNext(evader.getCurrentVertex());
                display.repaint();

                // Check if pursuer 1 or pursuer 2 has captured the evader
                if ((pursuers[0].getCurrentVertex() == evader.getCurrentVertex())
                        || (pursuers[1].getCurrentVertex() == evader.getCurrentVertex()))
                    break;
                Thread.sleep(500);
                evader.chooseNext(pursuers[0].getCurrentVertex(), pursuers[1].getCurrentVertex());
                display.repaint();

                // Check if any of the pursuers has captured the evader
                if ((pursuers[0].getCurrentVertex() == evader.getCurrentVertex())
                        || (pursuers[1].getCurrentVertex() == evader.getCurrentVertex()))
                    break;
                Thread.sleep(500);
                pursuers[1].chooseNext(evader.getCurrentVertex());
                display.repaint();

                if ((pursuers[0].getCurrentVertex() == evader.getCurrentVertex()) || (pursuers[1].getCurrentVertex() == evader.getCurrentVertex())) {
                    break;
                }
                Thread.sleep(500);
                evader.chooseNext(pursuers[0].getCurrentVertex(), pursuers[1].getCurrentVertex());
                display.repaint();
            }

        } else {
            new Driver(n, p);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        int n = 15;
        double p = 0.3;

        if (args.length > 0 && args[0].equals("mpursue")) {
            new Extension(n, p, true);
        }
        else {
            new Extension(n, p, false);
        }
    }
}
