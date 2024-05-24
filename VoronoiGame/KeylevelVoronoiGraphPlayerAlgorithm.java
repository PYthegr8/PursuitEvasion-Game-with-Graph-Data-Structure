import java.util.*;

public class KeylevelVoronoiGraphPlayerAlgorithm extends VoronoiGraphPlayerAlgorithm {

    public KeylevelVoronoiGraphPlayerAlgorithm(VoronoiGraph g) {
        super(g);
        
    }

    public Vertex chooseVertex(int playerIndex, int numRemainingTurns) {
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

    private int getKeylevel(Vertex root) {
        return calculateHeight(root, new HashSet<>());
    }

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

    private Vertex getRoot() {
        for (Vertex v : graph.getVertices()) {
            if (v.adjacentVertices() != null) {
                return v;
            }
        }
        return null; // If no root is found (graph is empty or improperly constructed)
    }
}
