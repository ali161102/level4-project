import java.io.Console;
import java.util.ArrayList;

/**
 * class to represent a vertex in a graph
 */

public class vertex {

    public String row;

    public boolean visited;

    public ArrayList<String> path;
    public ArrayList<Character> changes;

    public char changeType;
    public vertex predecessor;

    public vertex w;
    public vertex h;
    // public vertex m;

    public vertex(String r, char type) {
        row = r;
        visited = false;
        changeType = type;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String r) {
        row = r;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean b) {
        visited = b;
    }

    public vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(vertex v) {
        predecessor = v;
    }

    public void setSuccessors() {
        String init = row;
        String wrong = init.substring(1, 3) + init.charAt(0) + init.substring(3, 5);
        String home = init.charAt(0) + init.substring(2, 4) + init.charAt(1) + init.charAt(4);
        // String middle = init.substring(0, 2) + init.substring(3, 5) + init.charAt(2);
        w = new vertex(wrong, 'W');
        h = new vertex(home, 'H');
        // m = new vertex(middle, 'M');
    }

    public vertex[] getSuccessors() {
        // vertex[] successors = { w, h, m };
        vertex[] successors = { w, h };
        return (successors);
    }

    public void initialisePath() {
        path = new ArrayList<String>();
        changes = new ArrayList<Character>();
        // p.add(r);
        // path.add("53246");
    }

    public void addToPath(String r) {
        path.add(r);
    }

    // public static void main(String[] args) {
    // System.out.println("test");
    // vertex v = new vertex("53246");
    // v.setSuccessors();
    // vertex[] vs = v.getSuccessors();
    // System.out.println(vs[0].row);
    // }
}

// public class vertex {

// // private int index; // the index of this vertex in the graph
// private String row; // the row (permutation of bells), as a string

// //possibly other fields, for example representing data
// // stored at the node, whether the vertex has been visited
// // in a traversal, its predecessor in such a traversal, etc.

// boolean visited; // whether vertex has been visited in a traversal
// vertex predecessor; // index of predecessor vertex in a traversal

// // the vertices after applying a
// public vertex w; // wrong to the row
// public vertex h; // home to the row
// public vertex m; // middle to the row

// /**
// creates a new instance of Vertex
// */
// public Vertex(int n, String s) {
// index = n;
// word = s;
// visited = false;
// }

// /**
// copy constructor
// */
// public Vertex(Vertex v){
// adjList = v.getAdjList();
// index = v.getIndex();
// word = v.getWord();
// visited = v.getVisited();
// }

// public LinkedList<AdjListNode> getAdjList(){
// return adjList;
// }

// public int getIndex(){
// return index;
// }

// public String getWord() { return word; }

// public void setIndex(int n){
// index = n;
// }

// public void setWord(String s) { word = s; }

// public void setDistance(int d) { distance = d; }

// public boolean getVisited(){
// return visited;
// }

// public void setVisited(boolean b){
// visited = b;
// }

// public Vertex getPredecessor(){
// return predecessor;
// }

// public void setPredecessor(Vertex n){
// predecessor = n;
// }

// public void addToAdjList(Vertex v, String s, int w){
// adjList.addLast(new AdjListNode(v, s, w));
// }

// public int vertexDegree(){
// return adjList.size();
// }
// }
