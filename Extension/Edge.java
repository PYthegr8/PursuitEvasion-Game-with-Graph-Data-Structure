/*
 * Author: Papa Yaw Owusu Nti
 * Date: May 12th, 2024
 * Class: CS 231 B
 * Project: Project 8
 * Description: This class represents an Edge in the graph. It connects two vertices and has a distance associated with it.
*/


public class Edge {
    
    private Vertex start;
    private Vertex end;
    private double distance;

    // constructs an Edge consisting of the two vertices with a distance of the given distance.
    public Edge(Vertex u, Vertex v, double distance){
        this.start = u;
        this.end = v;
        this.distance = distance;
    }
    

    // returns the distance of this edge.
    public double distance(){
        return distance;
    }
    

    // if vertex is one of the endpoints of this edge, returns the other end point. Otherwise returns null.
    public Vertex other(Vertex vertex){
        if (vertex == start) {
            return end;
        } else if (vertex == end) {
            return start;
        } else {
            return null;
        }
    }
    

    // returns an array of the two Vertices comprising this Edge. Order is arbitrary.
    public Vertex[] vertices(){
        return new Vertex[]{start, end};
    }

    public Vertex getStart() {
        return this.start;
    }
        
    public Vertex getEnd() {
        return this.end;
    }

    

}
