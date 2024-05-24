/*
 * Author: Papa Yaw Owusu Nti
 * Date: May 12th, 2024
 * Class: CS 231 B
 * Project: Project 8
 * Description: This class represents a player algorithm that makes random moves in the graph game simulation.
*/

import java.util.Random;

public class RandomPlayer extends AbstractPlayerAlgorithm{

    Random rand;

    public RandomPlayer(Graph graph) {
        super(graph);
        rand = new Random();
    
    }

    @Override
    public Vertex chooseStart() {
        Vertex out = graph.getVertices().get(rand.nextInt(0, graph.size()));
        this.curVertex = out;
        return out;
    }

    @Override
    public Vertex chooseStart(Vertex other) {
        this.curVertex = graph.getVertices().get(rand.nextInt(0, graph.size()));
        Vertex out = curVertex;
        while (out == other) {
            out = graph.getVertices().get(rand.nextInt(0, graph.size()));
        }

        this.curVertex = out;
        return out;
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        Vertex out = curVertex;
        while (this.curVertex.equals(out)) {
            out = curVertex.adjacentVertices().get(rand.nextInt(0, curVertex.adjacentVertices().size()));
        }

        this.curVertex = out;
        return out;
    }

}
