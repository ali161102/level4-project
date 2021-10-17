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
    public vertex m;

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
        String middle = init.substring(0, 2) + init.substring(3, 5) + init.charAt(2);
        w = new vertex(wrong, 'W');
        h = new vertex(home, 'H');
        m = new vertex(middle, 'M');
    }

    public vertex[] getSuccessors() {
        vertex[] successors = { w, h, m };
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

