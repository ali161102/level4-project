import java.util.ArrayList;

public class tree {

    // private vertex[] vertices; // the vertices
    // private int numVertices = 0; // number of vertices
    // possibly other fields representing properties of the graph

    public vertex root;
    private int duplicates;
    private int returns;

    /** Create a Graph with n vertices indexed 0,...,n-1 */
    public tree(String r) {
        root = new vertex(r, '-');
        duplicates = 0;
        returns = 0;
    }

    /** visit vertex v, with predecessor index p, during a dfs */
    private int dynamic_visit(vertex v, vertex p) {
        v.setVisited(true); // update as now visited
        v.setPredecessor(p); // set predecessor (indicates edge used to find vertex)

        v.setSuccessors();
        vertex[] successors = v.getSuccessors();

        // String predstr = p.row;
        // v.initialisePath();
        v.path = new ArrayList<String>(p.path);
        v.changes = new ArrayList<Character>(p.changes);

        // v.path.addAll(p.path);

        // System.out.println(p.row + " -> " + v.row + "\t" + v.path);
        // System.out.println("v.path = " + v.path);

        if (v.path.contains(v.row)) {
            // System.out.println("duplicate");
            duplicates++;
            // System.out.println("returns = " + returns + " duplicates = " + duplicates);
            for (vertex s : successors) {
                s.setVisited(true);
                // v.path.clear();
                // v.changes.clear();
            }
            // dynamic_visit(p, p.predecessor);
        }
        v.path.add(v.row);
        v.changes.add(v.changeType);

        if (v.row.equals("53246")) {
            // System.out.println("back to 53246");
            returns++;

            for (vertex s : successors) {
                s.setVisited(true);
                // v.path.clear();
                // v.changes.clear();
            }
            System.out.println("returns = " + returns + " duplicates = " + duplicates);
            System.out.println("return path is of length " + (v.path.size()) + " - " + v.path);
            System.out.println("changes path is " + v.changes);
            System.out.println(" ");
            // + v.path + " 53246");

        }

        // System.out.println("new v.path = " + v.path);

        // System.out.println("current path is " + v.path);
        // System.out.println("changes path is " + v.changes);
        // if (v.path.size() > 20) {

        // for (vertex s : successors) {
        // s.setVisited(true);
        // v.path.clear();
        // }
        // // System.out.println("dupes = " + duplicates + "\treturns = " + returns);
        // // System.out.println("-1");
        // }

        // LinkedList<AdjListNode> L = v.getAdjList(); // get adjacency list
        for (vertex s : successors) { // go through all adjacent vertices
            // System.out.println(" if s.row = 53246 " + s.row);

            if (!s.getVisited()) {// if vertex has not been visited
                // System.out.println(" ");
                dynamic_visit(s, v); // continue dfs search from it
            }
            // setting the predecessor vertex index to the index of v
        }
        // System.out.println("ends");
        return (returns);
    }

    /** carry out a depth first search/traversal of the graph */
    public void dynamic_dfs(vertex rt) {
        rt.setSuccessors();
        vertex[] L = rt.getSuccessors();
        // System.out.println(L[0].row + " " + L[1].row + " " + L[2].row);
        for (vertex v : L) {
            // vertex v = L[0];
            // System.out.println(v.getVisited());
            // System.out.println(v.row);
            if (!v.getVisited()) {
                dynamic_visit(v, rt);
            }

            System.out.println("---- end of dfs from " + v.row + " ---------");
        }
        // }
        // v.setVisited(false); // initialise

        // if vertex is not yet visited, then start dfs on vertex
        // -1 is used to indicate v was not found through an edge of the graph
    }
}

// import java.util.*;

// /**
// class to represent an undirected graph using adjacency lists
// */
// public class Graph {

// private Vertex[] vertices; // the (array of) vertices
// private int numVertices = 0; // number of vertices
// private HashSet<Vertex> S = new HashSet<>();
// // set S of which the shortest path is known
// PriorityQueue<AdjListNode> pqueue;
// // priority queue to be used in Dijkstra implementation

// /**
// * creates a new instance of Graph with n vertices
// */
// public Graph(int n, String[] dict) {
// numVertices = n;
// vertices = new Vertex[n];
// for (int i = 0; i < n; i++) {
// String word = dict[i];
// vertices[i] = new Vertex(i, word);
// }
// }

// public int size() {
// return numVertices;
// }

// public Vertex getVertex(int i) {
// return vertices[i];
// }

// public void setVertex(int i, String s) {
// vertices[i] = new Vertex(i, s);
// }

// public Vertex findVertex(String s) {
// for (Vertex v : vertices) {
// if (s.equals(v.getWord())) {
// return v;
// }
// }
// return null;
// }

// /**
// * carry out Dijkstra's shortest path algorithm
// */
// public void dijkstra(Vertex start, Vertex finish) {

// for (Vertex v : vertices) {
// v.distance = Integer.MAX_VALUE;
// // initialise distance for all vertices as 'infinity'
// }

// pqueue.add(new AdjListNode(start, start.getWord(), start.getIndex())); // add
// source vertex to priority queue

// start.setDistance(0); // distance from source to itself is of course 0
// while (S.size() != numVertices) {
// // there are still unchecked vertices
// Vertex u = pqueue.remove().vertex;

// // process u

// S.add(u);
// }

// // output lowest cost path from start -> finish
// List<String> shortestPath = new ArrayList<>();
// String output = "";
// Vertex node = finish;
// while(node != null) {
// shortestPath.add(node.getWord());
// node = node.getPredecessor();
// }
// Collections.reverse(shortestPath);
// output = output.join(" -> ", shortestPath);
// System.out.println("\n" + "Shortest distance path has cost " +
// finish.distance);
// System.out.println(output);
// }

// // helper function for Dijkstra's, processes a vertex
// private void process(Vertex u) {
// int edgeWeight = -1;
// int newWeight = -1;
// // process all adjacent nodes to u
// for (int i = 0; i < u.getAdjList().size(); i++) {
// Vertex v = u.getAdjList().get(i).vertex;

// if (!S.contains(v)) {
// // v has not already been checked
// edgeWeight = u.getAdjList().get(i).getWeight();
// newWeight = u.distance + edgeWeight;

// if (newWeight < v.distance) {
// // the new distance is shorter than the current path to v
// v.distance = newWeight;
// pqueue.add(new AdjListNode(v, v.getWord(), v.getIndex()));
// }
// }
// }
// }

// }