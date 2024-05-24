/**
 * Papa Yaw Owusu Nti
 * Voronoi Game Solution
 * This class implements a hybrid algorithm for a Voronoi graph player. 
 * It chooses between key-level strategy and greedy strategy based on the characteristics of the graph.
 */
import java.util.*;

public class HybridVoronoiGraphPlayerAlgorithm extends VoronoiGraphPlayerAlgorithm {



    /**
     * Constructs a new HybridVoronoiGraphPlayerAlgorithm with the given Voronoi graph.
     * @param g The Voronoi graph to operate on.
     */
    public HybridVoronoiGraphPlayerAlgorithm(VoronoiGraph g) {
        super(g);
    }



    /**
     * Chooses the next vertex for the player based on the selected strategy.
     * @param playerIndex The index of the player.
     * @param numRemainingTurns The number of remaining turns in the game.
     * @return The chosen vertex.
     */
    public Vertex chooseVertex(int playerIndex, int numRemainingTurns) {
        int k = calculateK();
        if (isLargeOddKTree(k)) {
            return keyLevelStrategy(playerIndex, numRemainingTurns);
        } else {
            return greedyStrategy(playerIndex, numRemainingTurns);
        }
    }



    /**
     * Implements the key-level strategy for choosing the next vertex.
     * @param playerIndex The index of the player.
     * @param numRemainingTurns The number of remaining turns in the game.
     * @return The chosen vertex.
     */
    private Vertex keyLevelStrategy(int playerIndex, int numRemainingTurns) {
        Vertex out = null;
        int keylevel = getKeylevel(getRoot());

        if (playerIndex == 0 && numRemainingTurns == keylevel) {
            for (Vertex v : graph.getVertices()) {
                if (!graph.hasToken(v) && v.adjacentVertices()!=null) {
                    out = v;
                    break;
                }
            }
        } else {
            for (Vertex v : graph.getVertices()) {
                if (!graph.hasToken(v) && (out == null || graph.getValue(v) > graph.getValue(out))) {
                    out = v;
                }
            }
        }
        return out;
    }



    /**
     * Implements the greedy strategy for choosing the next vertex.
     * @param playerIndex The index of the player.
     * @param numRemainingTurns The number of remaining turns in the game.
     * @return The chosen vertex.
     */
    private Vertex greedyStrategy(int playerIndex, int numRemainingTurns) {
        Vertex out = null;
        for (Vertex v : graph.getVertices())
            if (!graph.hasToken(v) && (out == null || graph.getValue(v) > graph.getValue(out)))
                out = v;
        return out;
    }



    /**
     * Calculates the maximum degree of any vertex in the graph.
     * @return The maximum degree.
     */
    private int calculateK() {
        int maxDegree = 0;
        for (Vertex v : graph.getVertices()) {
            int degree = 0;
            for (Vertex neighbor : v.adjacentVertices()) {
                degree++;
            }
            maxDegree = Math.max(maxDegree, degree);
        }
        return maxDegree;
    }




    /**
     * Checks if the graph is a large odd-k tree.
     * @param k The maximum degree of any vertex.
     * @return True if the graph is a large odd-k tree, false otherwise.
     */
    private boolean isLargeOddKTree(int k) {
        return k % 2 == 1 && k > 3;
    }




    /**
     * Computes the key level of the graph.
     * @param root The root vertex of the graph.
     * @return The key level of the graph.
     */
    private int getKeylevel(Vertex root) {
        return calculateHeight(root, new HashSet<>());
    }




    /**
     * Calculates the height of a vertex in the graph.
     * @param root The vertex whose height is to be calculated.
     * @param visited A set of visited vertices to prevent cycles.
     * @return The height of the vertex.
     */
    private int calculateHeight(Vertex root, Set<Vertex> visited) {
        if (root == null || visited.contains(root)) {
            return 0;
        }
        visited.add(root);
        int maxHeight = 0;
        for (Vertex neighbor : root.adjacentVertices()) {
            int height = calculateHeight(neighbor, visited);
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight + 1;
    }



    /**
     * Retrieves the root vertex of the graph.
     * @return The root vertex, or null if not found.
     */
    private Vertex getRoot() {
        for (Vertex v : graph.getVertices()) {
            if (v.adjacentVertices() != null) {
                return v;
            }
        }
        return null;
    }


}
