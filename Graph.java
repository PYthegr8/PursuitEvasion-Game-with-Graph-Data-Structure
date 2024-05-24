/*
 * Author: Papa Yaw Owusu Nti
 * Date: May 12th, 2024
 * Class: CS 231 B
 * Project: Project 8
 * Description: This class represents a Graph data structure. It allows for the creation of graphs with specified vertices and edges, as well as various operations on the graph.
 * 
*/

import java.io.* ;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;


public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;

    // Creates a graph of n vertices where each pair of vertices has an edge between them of distance 1 with probability given by the supplied probability.
    public Graph(int n, double probability){
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex());
        }
        
    
        if(probability > 0.0 && probability <1.0){
            for (int i = 0; i< n;i++){
                for (int j=i+1; j<n; j++){
                    double rand = Math.random();
                    if (rand < probability) {
                        Edge ranEdge = new Edge(vertices.get(i), vertices.get(j), 1);
                        edges.add(ranEdge);
                        vertices.get(i).addEdge(ranEdge);
                        vertices.get(j).addEdge(ranEdge);
                        
                    }
                }
            }
        }
    }

    public Graph(){
        this(0);
    }

    public Graph(int n){
        this(n, 0.0);
    }


    /* *
	 * A graph constructor that takes in a filename and builds
	 * the graph with the number of vertices and specific edges 
	 * specified.  
	 * */
	@SuppressWarnings("unused")
    public Graph( String filename ) {

    	try {
    		//Setup for reading the file
    		FileReader fr = new FileReader(filename);
    		BufferedReader br = new BufferedReader(fr);

    		//Get the number of vertices from the file and initialize that number of verticies
			vertices = new ArrayList<>();
        	Integer numVertices = Integer.valueOf( br.readLine().split( ": " )[ 1 ] ) ;
			for ( int i = 0 ; i < numVertices ; i ++ ) {
				vertices.add( new Vertex() );
			}

			//Read in the edges specified by the file and create them
        	edges = new ArrayList<>(); //If you used a different data structure to store Edges, you'll need to update this line
			String header = br.readLine(); //We don't use the header, but have to read it to skip to the next line
			//Read in all the lines corresponding to edges
        	String line = br.readLine();
       		while(line != null){
       			//Parse out the index of the start and end vertices of the edge
 	           	String[] arr = line.split(",");
 	           	Integer start = Integer.valueOf( arr[ 0 ] ) ;
 	           	Integer end = Integer.valueOf( arr[ 1 ] ) ;
 	           	
 	           	//Make the edge that starts at start and ends at end with weight 1
 	           	Edge edge = new Edge( vertices.get( start ) , vertices.get( end ) , 1. ) ;
 				//Add the edge to the set of edges for each of the vertices
 				vertices.get( start ).addEdge( edge ) ;
				vertices.get( end ).addEdge( edge ) ;
				//Add the edge to the collection of edges in the graph
            	this.edges.add( edge );
            	
            	//Read the next line
            	line = br.readLine();
            }
        	// call the close method of the BufferedReader:
        	br.close();
        	System.out.println( this.edges );
      	}
      	catch(FileNotFoundException ex) {
        	System.out.println("Graph constructor:: unable to open file " + filename + ": file not found");
      	}
      	catch(IOException ex) {
        	System.out.println("Graph constructor:: error reading file " + filename);
      	}
	}


    // returns the number of vertices
    public int size(){
        return vertices.size();
    }


    // returns an Iterable object that iterates over the vertices (don't re-invent the wheel here, this should be as simple as returning whatever structure you're using to keep track of the vertices)
    public List<Vertex> getVertices(){
        return vertices;
    }
    


    // returns an Iterable object that iterates over the edges.
    public Iterable<Edge> getEdges(){
        return edges;
    }


    // Creates a new Vertex, adds it to the Graph, and returns it.
    public Vertex addVertex(){
        Vertex out = new Vertex();
        vertices.add(out);
        return out;
    }

    public Vertex getVertex(int index) {
        return vertices.get(index);
    }


    // Creates a new Edge, adds it to the Graph (make sure the endpoints are aware of this new Edge), and returns it.
    public Edge addEdge(Vertex u, Vertex v, double distance){
        Edge edge = new Edge(u, v, distance);
        edges.add(edge);
        u.addEdge(edge);
        v.addEdge(edge);
        return edge;
    }



    // returns the Edge between u and v if such an Edge exists, otherwise returns null.
    public Edge getEdge(Vertex u, Vertex v){
        return u.getEdgeTo(v);
    }

    // if the given Vertex vertex is in this Graph, removes it and returns true. Otherwise, returns false. Remember to think about how removing a vertex impacts the edges
    public boolean remove(Vertex u){
        if (vertices.contains(u)) {
            for (Edge e : u.incidentEdges()) {
                e.other(u).removeEdge(e);
                edges.remove(e);
            }
            return vertices.remove(u);
        }
        return false;
    }

    // if the given Edge is in the Graph, removes it and returns true. Otherwise, returns false.
    public boolean remove(Edge e){
        if (edges.contains(e)) {
            for (Vertex v : e.vertices()) {
                v.removeEdge(e);
            }
            return edges.remove(e);
        }
        return false;
    }


    public HashMap<Vertex, Double> distanceFrom(Vertex source){
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

