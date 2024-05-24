import java.security.InvalidParameterException;

public class Edge {

    private Vertex u, v;
    private double distance;

    public Edge(Vertex u, Vertex v, double distance) {
        this.u = u;
        this.v = v;
        this.distance = distance;
    }

    public Vertex other(Vertex w) {
        if (w == u)
            return v;
        if (w == v)
            return u;
        throw new InvalidParameterException("Given vertex not an endpoint of the edge.");
    }

    public double distance(){
        return distance;
    }

    public Vertex[] vertices() {
        return new Vertex[] { u, v };
    }
}
