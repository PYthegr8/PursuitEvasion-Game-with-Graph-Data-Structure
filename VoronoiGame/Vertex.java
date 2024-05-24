import java.util.HashMap;

public class Vertex {

    static int num = 0;
    private HashMap<Vertex, Edge> edgeMap;
    private int index;

    public Vertex(){
        edgeMap = new HashMap<>();
        index = num++;
    }

    public int degree(){
        return edgeMap.size();
    }

    public Edge getEdgeTo(Vertex v){
        return edgeMap.get(v);
    }

    public void addEdge(Edge e){
        edgeMap.put(e.other(this), e);
    }

    public void removeEdge(Edge e){
        edgeMap.remove(e.other(this));
    }

    public Iterable<Vertex> adjacentVertices(){
        return edgeMap.keySet();
    }

    public Iterable<Edge> incidentEdges(){
        return edgeMap.values();
    }

    public String toString(){
        return "v" + index;
    }
}
