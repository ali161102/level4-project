import java.util.ArrayList;

public class graph {
    public boolean debug = true;

    public adjListVertex[] vertices;
    public int numVertices = 0;

    // ArrayList<adjListVertex> currentPath;
    ArrayList<String> currentPath;
    ArrayList<Character> currentChanges;
    ArrayList<ArrayList<String>> simplePaths;
    public int numSimplePaths = 0;

    public graph(int n, ArrayList<String> permutations) {
        numVertices = n;
        vertices = new adjListVertex[n];
        for (int i = 0; i < n; i++) {
            String row = permutations.get(i);
            vertices[i] = new adjListVertex(row);
        }
    }

    public void initialise() {
        for (adjListVertex v : vertices) {
            v.setVisited(false);
        }
        // currentPath = new ArrayList<adjListVertex>();
        currentPath = new ArrayList<String>();
        currentChanges = new ArrayList<Character>();
        simplePaths = new ArrayList<ArrayList<String>>();

    }

    //// initialise with false visited values
    public void DFS(adjListVertex u, adjListVertex v) {
        // System.out.println(u);

        if (u.getVisited()) {
            return;
        }

        u.setVisited(true);
        currentPath.add(u.row);

        if (debug) {
            System.out.println("row = " + u.row + " path = " + currentPath);
            // System.out.println("if u.row = v.row, " + u.row + ", " + v.row);
        }

        if (u.row.equals("53246")) {
            if (debug) {
                System.out.println("u.row = 53246, currentpath = " + currentPath);
            }
            currentPath.add(0, "53246");
            simplePaths.add(currentPath);
            numSimplePaths++;
            if (debug) {
                System.out.println("simplePaths.add = " + currentPath);
                System.out.println(numSimplePaths);
            }
            currentPath.remove(0);

            u.setVisited(false);
            currentPath.remove(currentPath.lastIndexOf(u.row));
            currentPath.remove(currentPath.size() - 1);
            return;
            // }
        }

        for (adjListNode next : u.adjList) {
            // System.out.println(u.getAdjList());
            // System.out.println("dfs(" + next.vertex.row + ", " + v.row);
            DFS(next.vertex, v);
        }
        // currentPath.remove(currentPath.size() - 1);
        u.setVisited(false);
    }

    public adjListVertex getVertex(String row) {
        for (adjListVertex v : vertices) {
            if (row.equals(v.row)) {
                return v;
            }
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getSimplePaths() {
        return simplePaths;
    }

    public int getNumPaths() {
        return numSimplePaths;
    }
}
