/**Author: Papa Yaw Owusu Nti
*Course: CS231
*Purpose: smarter pursue algorithm
*/

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmartAlgorithm extends AbstractPlayerAlgorithm {
    private Random picker;

    public SmartAlgorithm(Graph graph) {
        super(graph);
        picker = new Random();
        curVertex = graph.getVertices().get(picker.nextInt(graph.size()));
    }

    @Override
    public Vertex chooseStart() {
        Vertex out = graph.getVertices().get(picker.nextInt(graph.size()));
        this.curVertex = out;
        return out;
    }

    @Override
    public Vertex chooseStart(Vertex other) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(other);
        Vertex minVertex = null;

        for (Vertex vertex : graph.getVertices()) {
            if (vertex != other && (minVertex == null || distances.get(vertex) < distances.get(minVertex))) {
                minVertex = vertex;
            }
        }

        curVertex = minVertex;
        return minVertex;
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        if (curVertex.adjacentVertices().contains(otherPlayer)) {
            curVertex = otherPlayer;
            return curVertex;
        }

        ArrayList<Vertex> shortestPath = graph.shortestPath(curVertex, otherPlayer);
        Vertex nextVertex = null;

        if (shortestPath != null && shortestPath.size() > 1) {
            nextVertex = shortestPath.get(1);
        } else {
            // Choose a random adjacent vertex if there's no path
            List<Vertex> adjacentVertices = curVertex.adjacentVertices();
            if (!adjacentVertices.isEmpty()) {
                nextVertex = adjacentVertices.get(picker.nextInt(adjacentVertices.size()));
            }
        }

        curVertex = nextVertex;
        return curVertex;
    }
}
