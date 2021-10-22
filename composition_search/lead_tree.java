import java.util.ArrayList;
import java.util.BitSet;

public class lead_tree {
    public lead_vertex root;
    public int duplicates;
    public int returns;

    /** Create a Graph with n vertices indexed 0,...,n-1 */
    public lead_tree(String r) {
        root = new lead_vertex(r, '-');
        duplicates = 0;
        returns = 0;
    }

    public void printLead(String leadhead) {
        int[][] method = new int[][]{{0, 1, 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 6, 7, 6, 7, 7, 6, 7, 6, 5, 4, 5, 4, 3, 2, 3, 2, 1, 0, 1, 0, 0}, 
                                     {1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 2, 3, 2, 3, 4, 5, 6, 7, 6, 7, 6, 7, 7, 6, 7, 6, 5, 4, 5}, 
                                     {2, 3, 4, 5, 4, 5, 6, 7, 6, 7, 6, 7, 7, 6, 7, 6, 6, 7, 6, 7, 7, 6, 7, 6, 7, 6, 5, 4, 5, 4, 3, 2, 3}, 
                                     {3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2, 3, 2, 1, 0, 1, 0, 0, 1, 0, 1, 2, 3, 4, 5, 6, 7, 6, 7, 7, 6, 7}, 
                                     {4, 5, 6, 7, 6, 7, 7, 6, 7, 6, 7, 6, 5, 4, 3, 2, 3, 2, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1}, 
                                     {5, 4, 3, 2, 1, 0, 0, 1, 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 5, 4, 4, 5, 4, 5, 5, 4, 4, 5, 4, 5, 6, 7, 6}, 
                                     {6, 7, 7, 6, 7, 6, 5, 4, 3, 2, 1, 0, 1, 0, 0, 1, 0, 1, 2, 3, 2, 3, 3, 2, 2, 3, 2, 3, 3, 2, 2, 3, 2}, 
                                     {7, 6, 5, 4, 5, 4, 4, 5, 5, 4, 5, 4, 4, 5, 5, 4, 5, 4, 3, 2, 3, 2, 1, 0, 1, 0, 0, 1, 2, 3, 4, 5, 4}};

        ArrayList<String> currentlead = new ArrayList<String>();

        for (int i=0; i < method[0].length; i++) {
            char[] row = new char[method.length];
            for (int j = 0; j < method.length; j++) {
                row[method[j][i]] = leadhead.charAt(j);
            }
            currentlead.add(new String(row));
        }
        
        //currentlead.remove(currentlead.size()-1);
        for (String row : currentlead) {
            System.out.println(row.replace("", " ").trim());
        }
        System.out.println("------------");
    }

    /** visit vertex v, with predecessor index p, during a dfs */
    private int dynamic_visit(lead_vertex v, lead_vertex p) {
        v.setVisited(true); // update as now visited
        v.setPredecessor(p); // set predecessor (indicates edge used to find vertex)

        v.generate_lead();
        lead_vertex[] successors = v.getSuccessors();

        // String predstr = p.row;
        // v.initialisePath();
        v.path = new ArrayList<String>(p.path);
        v.calls = new ArrayList<Character>(p.calls);
        v.rung = new ArrayList<Integer>(p.rung);
        //v.rung = (BitSet) p.rung.clo/ne();

        if (v.path.size() > 175) {
            System.out.println(v.path.size()*32 + " rows\t" + duplicates + "\t" + returns);
            for (lead_vertex s : successors) {
                
                s.setVisited(true);
            }
            v.path.clear();
            v.rung.clear();
        }

        if(!v.updateBitSet()) {
            duplicates++;
            //System.out.println(v.path.size()*32 + " rows\t" + duplicates + "\t" + returns);
            // for (String leadhead : v.path) {
            //     //System.out.print(leadhead + "\n" + "-------");
            //     //printLead(leadhead);
            // }

            // System.out.println("returns = " + returns + " duplicates = " + duplicates);
            for (lead_vertex s : successors) {
                s.setVisited(true);
                // v.path.clear();
                // v.changes.clear();
            }
        }

        // v.path.addAll(p.path);

        // System.out.println(p.lead_head + " -> " + v.lead_head + "\t" + v.path);
        // System.out.println("v.path = " + v.path);

        v.path.add(v.lead_head);
        v.calls.add(v.call);

        if (v.lead_head.equals("12345678")) {
            System.out.println("back to 12345678");
            returns++;

            for (lead_vertex s : successors) {
                s.setVisited(true);

            }
            System.out.println("return path " + returns + " is of length " + (v.path.size()) + " leads = " + (v.path.size()*32) + " rows");
            // System.out.println("changes path is " + v.calls);
            
            ArrayList<String> output = new ArrayList<String>();
            String stroutput = new String();
            for (int i = 0; i < v.calls.size(); i++) {
                output.add(v.path.get(i));
                output.add(v.calls.get(i).toString());
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
        for (lead_vertex s : successors) { // go through all adjacent vertices
            // System.out.println(" if s.row = 53246 " + s.row);
            // System.out.println(s.lead_head);
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
    public void dynamic_dfs(lead_vertex rt) {
        rt.generate_lead();
        lead_vertex[] L = rt.getSuccessors();
        System.out.println(L[0].lead_head + " " + L[1].lead_head);
        for (lead_vertex v : L) {
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
