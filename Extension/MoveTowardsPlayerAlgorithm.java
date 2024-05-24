/*
 * Author: Papa Yaw Owusu Nti
 * Date: May 12th, 2024
 * Class: CS 231 B
 * Project: Project 8
 * Description: This class represents a player algorithm that always tries to move towards the other player in the graph game simulation.
*/
import java.util.Random;
import java.util.HashMap;


public class MoveTowardsPlayerAlgorithm extends AbstractPlayerAlgorithm {

    Random rand;

    public MoveTowardsPlayerAlgorithm(Graph graph) {
        super(graph);
        rand = new Random();
        curVertex = ( graph.getVertices()).get(rand.nextInt(graph.size()));
    }

    @Override
    public Vertex chooseStart() {
        Vertex out = graph.getVertices().get(rand.nextInt(0, graph.size()));
        this.curVertex = out;
        return out;
    }

    @Override
    public Vertex chooseStart(Vertex other) {
        // setCurrentVertex(other);
        HashMap<Vertex, Double> distances = graph.distanceFrom(other);
        Vertex minVertex = null;

        for (Vertex vertex : graph.getVertices()) {
            if (minVertex == null) {
                minVertex = vertex;
            } else if (distances.get(vertex) < distances.get(minVertex) && vertex != other) {
                minVertex = vertex;
            }
        }

        curVertex = minVertex;
        return minVertex;
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(otherPlayer);
        Vertex leastNeighbor = null;

        for (Vertex vertex : curVertex.adjacentVertices()) {
            if (leastNeighbor == null) {
                leastNeighbor = vertex;
            } else if (distances.get(vertex) < distances.get(leastNeighbor)) {
                leastNeighbor = vertex;
            }
        }

        curVertex = leastNeighbor;
        return leastNeighbor;
    }
    

}