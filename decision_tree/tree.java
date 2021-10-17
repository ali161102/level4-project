import java.util.ArrayList;

public class tree {

    // private vertex[] vertices; // the vertices
    // private int numVertices = 0; // number of vertices
    // possibly other fields representing properties of the graph

    public vertex root;
    public int duplicates;
    public int returns;

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
            System.out.println("return path " + returns + " is of length " + (v.path.size()));
            // System.out.println("changes path is " + v.changes);
            
            ArrayList<String> output = new ArrayList<String>();
            String stroutput = new String();
            for (int i = 0; i < v.changes.size(); i++) {
                output.add(v.path.get(i));
                output.add(v.changes.get(i).toString());
                stroutput = String.join(" ", output);
            }
            System.out.println(stroutput + " " +  v.path.get(v.path.size()-1) + "\n");

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
        }
        // }
        // v.setVisited(false); // initialise

        // if vertex is not yet visited, then start dfs on vertex
        // -1 is used to indicate v was not found through an edge of the graph
    }
}

