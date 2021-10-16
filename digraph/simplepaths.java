import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class simplepaths {

    public static ArrayList<String> permutation(String str) {
        ArrayList<String> permutations = new ArrayList<String>();
        permutation("", str, permutations);
        return permutations;
    }

    private static void permutation(String prefix, String str, ArrayList<String> permutations) {
        int n = str.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), permutations);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> perms = permutation("23456");

        graph G = new graph(perms.size(), perms);

        for (adjListVertex v : G.vertices) {

            String init = v.row;
            String wrong = init.substring(1, 3) + init.charAt(0) + init.substring(3, 5);
            String home = init.charAt(0) + init.substring(2, 4) + init.charAt(1) + init.charAt(4);
            String middle = init.substring(0, 2) + init.substring(3, 5) + init.charAt(2);
            v.addToAdjList(G.getVertex(wrong), 'W');
            v.addToAdjList(G.getVertex(home), 'H');
            // v.addToAdjList(G.getVertex(middle), 'M');

            System.out.println(v.row + " -> " + v.getAdjList());
        }

        System.out.println(G.numVertices);

        G.initialise();

        for (adjListNode v : G.getVertex("53246").adjList) {
            G.DFS(G.getVertex(v.getVertexRow()), G.getVertex("53246"));
        }
        // G.DFS(G.getVertex("32546"), G.getVertex("53246"));
        System.out.println(G.getNumPaths());
        // System.out.println(G.getSimplePaths());
    }

}
