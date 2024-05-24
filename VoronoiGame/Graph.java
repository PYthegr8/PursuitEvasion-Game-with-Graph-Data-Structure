import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Graph(int n) {
        this();
        for (int i = 0; i < n; i++) {
            addVertex();
        }
    }

    public Graph(int n, double density) {
        this();
        Random rand = new Random();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++)
            vertices[i] = addVertex();

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (vertices[i] != vertices[j] && rand.nextDouble() < density)
                    addEdge(vertices[i], vertices[j], 1);
    }

    public int size() {
        return vertices.size();
    }

    public Iterable<Vertex> getVertices() {
        return vertices;
    }

    public Iterable<Edge> getEdges() {
        return edges;
    }

    public Vertex getVertex(int index) {
        return vertices.get(index);
    }

    public Vertex addVertex() {
        Vertex out = new Vertex();
        vertices.add(out);
        return out;
    }

    public Edge addEdge(Vertex u, Vertex v, double distance) {
        Edge edge = new Edge(u, v, distance);
        edges.add(edge);
        u.addEdge(edge);
        v.addEdge(edge);
        return edge;
    }

    public Edge getEdge(Vertex u, Vertex v) {
        return u.getEdgeTo(v);
    }

    public boolean remove(Vertex u) {
        if (vertices.contains(u)) {
            for (Edge e : u.incidentEdges()) {
                e.other(u).removeEdge(e);
                edges.remove(e);
            }
            return vertices.remove(u);
        }
        return false;
    }

    public boolean remove(Edge e) {
        if (edges.contains(e)) {
            for (Vertex v : e.vertices()) {
                v.removeEdge(e);
            }
            return edges.remove(e);
        }
        return false;
    }

    public HashMap<Vertex, Double> distanceFrom(Vertex source) {
        if (!vertices.contains(source))
            return null;

        HashMap<Vertex, Double> distances = new HashMap<>();
        for (Vertex v : vertices)
            distances.put(v, Double.POSITIVE_INFINITY);

        distances.put(source, 0.0);

        PriorityQueue<Vertex> queue = new PriorityQueue<>(new Comparator<>() {

            @Override
            public int compare(Vertex o1, Vertex o2) {
                if (distances.get(o1).equals(distances.get(o2)))
                    return 0;
                return (int) (distances.get(o1) - distances.get(o2));
            }

        });
        queue.offer(source);
        while (queue.size() > 0) {
            Vertex cur = queue.poll();

            for (Vertex next : cur.adjacentVertices()) {
                if (distances.get(next) > distances.get(cur) + cur.getEdgeTo(next).distance()) {
                    distances.put(next, distances.get(cur) + cur.getEdgeTo(next).distance());
                    queue.remove(next);
                    queue.offer(next);
                }
            }
        }

        return distances;
    }
}