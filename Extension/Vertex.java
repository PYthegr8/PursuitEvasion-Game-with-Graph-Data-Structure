/*
 * Author: Papa Yaw Owusu Nti
 * Date: May 12th, 2024
 * Class: CS 231 B
 * Project: Project 8
 * Description: This class represents a Vertex in the graph. It stores information about edges incident to it and provides methods to manipulate them.
 * 
*/

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private ArrayList<Edge> edges;
    // private int data;

    public Vertex(){
        this.edges = new ArrayList<Edge>();
    }


    // returns the Edge which connects this vertex and the given Vertex vertex if such an Edge exists, otherwise returns null.
    public Edge getEdgeTo(Vertex vertex){
        for (Edge edge : edges) {
            if (edge.vertices()[0] == this && edge.vertices()[1] == vertex || 
                edge.vertices()[1] == this && edge.vertices()[0] == vertex) {
                return edge;
            }
        }
        return null;
    }

    // adds the specified Edge edge to the collection of Edges incident to this Vertex.
    public void addEdge(Edge edge){
        this.edges.add(edge);
    }
    
    // removes this Edge from the collection of Edges incident to this Vertex. Returns true if this Edge was connected to this Vertex, otherwise returns false.
    public boolean removeEdge(Edge edge){
        return this.edges.remove(edge);
    }
    
    // returns a collection of all the Vertices adjacent to this Vertex (the return type of this method is unimportant - it just needs to be something that is Iterable).
    public List<Vertex> adjacentVertices(){
        List<Vertex> adjacent_vertices = new ArrayList<>();
        for (Edge edge : edges) {
            adjacent_vertices.add(edge.other(this));
        }
        return adjacent_vertices;
    }
    

    // returns a collection of all the Edges incident to this Vertex (the return type of this method is unimportant - it just needs to be something that is Iterable).
    public List<Edge> incidentEdges(){
        return edges;
    }





}
