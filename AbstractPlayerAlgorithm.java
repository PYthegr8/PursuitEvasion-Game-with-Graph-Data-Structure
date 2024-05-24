/*
 * Author: Papa Yaw Owusu Nti
 * Date: May 12th, 2024
 * Class: CS 231 B
 * Project: Project 8
 * Description: This class represents an abstract player algorithm for the graph game simulation. It provides methods for players to choose starting positions and make moves.
 * 
*/

public abstract class AbstractPlayerAlgorithm {

    protected Graph graph;
    protected Vertex curVertex;

    // : Constructs the necessary fields of the class
    public AbstractPlayerAlgorithm(Graph graph){
        this.graph = graph;
        this.curVertex = new Vertex();
    }


    // : returns the underyling Graph.
    public Graph getGraph(){
        return this.graph;
    }


    // : returns the current Vertex of the player.
    public Vertex getCurrentVertex(){
        return this.curVertex;
    }


    // : updates the current Vertex of the player to be the given Vertex vertex.
    public void setCurrentVertex(Vertex vertex){
        this.curVertex = vertex;
    }


    // returns a Vertex for the player to start at and updates the current Vertex to that location.
    public abstract Vertex chooseStart();


    // returns a Vertex for the player to start at based on where the other player chose to start. Updates the current Vertex to the chosen location.
    public abstract Vertex chooseStart(Vertex other);


    // returns a Vertex for the player to move to based on where the other player currently is. Updates the current Vertex to the given next location.
    public abstract Vertex chooseNext(Vertex otherPlayer);
}
