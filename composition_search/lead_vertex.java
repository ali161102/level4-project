import java.util.ArrayList;
import java.util.BitSet;

public class lead_vertex {
    
    public String lead_head;

    // * moved to lead_tree for efficiency *
    //public BitSet rung;
    //public ArrayList<String> lead;
    // public ArrayList<Character> calls;
    // public ArrayList<String> path;

    public Boolean visited;
    //
    
    //public ArrayList<Integer> rung;
    
    
    public char call;

    public lead_vertex predecessor, plain, bob;

    public lead_vertex(String row, char c) {
        lead_head = row;
        visited = false;
        call = c;
    }

    // public static Boolean tenorsTogether (String lh) {
    //     // 17864523 -> 8765324
    //     //             21357642
    //     String course_order = "" + lh.charAt(2) + lh.charAt(1) + lh.charAt(3) + lh.charAt(5) + lh.charAt(7) + lh.charAt(6) + lh.charAt(4) + lh.charAt(2);
    //     if (course_order.contains("87")) {
    //         return true;
    //     }
    //     return false;
    // }

    // public void generate_lead() {
        
    //     int[][] method = new int[][]{{0, 1, 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 6, 7, 6, 7, 7, 6, 7, 6, 5, 4, 5, 4, 3, 2, 3, 2, 1, 0, 1, 0, 0}, 
    //                                  {1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 2, 3, 2, 3, 4, 5, 6, 7, 6, 7, 6, 7, 7, 6, 7, 6, 5, 4, 5}, 
    //                                  {2, 3, 4, 5, 4, 5, 6, 7, 6, 7, 6, 7, 7, 6, 7, 6, 6, 7, 6, 7, 7, 6, 7, 6, 7, 6, 5, 4, 5, 4, 3, 2, 3}, 
    //                                  {3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2, 3, 2, 1, 0, 1, 0, 0, 1, 0, 1, 2, 3, 4, 5, 6, 7, 6, 7, 7, 6, 7}, 
    //                                  {4, 5, 6, 7, 6, 7, 7, 6, 7, 6, 7, 6, 5, 4, 3, 2, 3, 2, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1}, 
    //                                  {5, 4, 3, 2, 1, 0, 0, 1, 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 5, 4, 4, 5, 4, 5, 5, 4, 4, 5, 4, 5, 6, 7, 6}, 
    //                                  {6, 7, 7, 6, 7, 6, 5, 4, 3, 2, 1, 0, 1, 0, 0, 1, 0, 1, 2, 3, 2, 3, 3, 2, 2, 3, 2, 3, 3, 2, 2, 3, 2}, 
    //                                  {7, 6, 5, 4, 5, 4, 4, 5, 5, 4, 5, 4, 4, 5, 5, 4, 5, 4, 3, 2, 3, 2, 1, 0, 1, 0, 0, 1, 2, 3, 4, 5, 4}};

    //     ArrayList<String> currentlead = new ArrayList<String>();

    //     for (int i=0; i < method[0].length; i++) {
    //         char[] row = new char[method.length];
    //         for (int j = 0; j < method.length; j++) {
    //             row[method[j][i]] = lead_head.charAt(j);
    //         }
    //         currentlead.add(new String(row));
    //     }
        
    //     // this.lead = lead;
    //     //System.out.println(lead);
    //     //return(lead);
    //     String next_lh_plain = currentlead.get(currentlead.size()-1).toString();
    //     // System.out.println(next_lead_head_plain);
    //     String next_lh_bob = "" + next_lh_plain.charAt(0) + next_lh_plain.charAt(3) + next_lh_plain.substring(1, 3) + next_lh_plain.substring(4);


    //     plain = new lead_vertex(next_lh_plain, 'p');
    //     bob = new lead_vertex(next_lh_bob, 'b');

    //     // System.out.println(next_lh_plain + " " + next_lh_bob);
    //     currentlead.remove(currentlead.size()-1);
    //     lead = currentlead;

    // }

    // public static int rowToInt(String rowstr) {
    //     int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040}; // lookup table for n!, 0 <= n <= 7
    //     int numbells = rowstr.length();                     // number of bells in the row
        
    //     // turn string into array of int
    //     char[] rowchars = rowstr.toCharArray();
    //     int[] row = new int[numbells];

    //     // the "value" of this row as an int, [0, (n!)-1]
    //     int value = 0;

    //     // subtract 1 from every digit
    //     for (int i = 0; i < numbells; i++) {
    //         row[i] = Character.getNumericValue(rowchars[i]) - 1;
    //     }

    //     for (int i = 0; i < numbells; i++) {                // for each digit
    //         value += row[i] * factorial[numbells - 1 - i];  // multiply by (len-1-index)! and add to total
    //         for (int j = i+1; j < numbells; j++) { 
    //             if (row[j] > row[i]) {                      // if any digits to the right are greater
    //                 row[j]--;                               // subtract 1 from them
    //             }
    //         }
    //     }
    //     return value;
    // }

    // public boolean updateBitSet() {
    //     for (String row : lead) {
            
    //         int rowInt = rowToInt(row);

    //         if (rung.get(rowInt)) {
    //             return false;
    //         }

    //         rung.set(rowInt);
    //         // if (rung.contains(rowInt)) {
    //         //     // System.out.println(rung + "\n" + "contains " + rowInt + " = " + row);
    //         //     return false;
    //         // }
    //         //rung.add(rowInt);
    //         // System.out.println("updateBitSet - adding " + row + " = " + rowInt);
    //     }
    //     return true;
    // }

    // public void initialiseRoot() {
    //     path = new ArrayList<String>();
    //     calls = new ArrayList<Character>();
    //     rung = new BitSet();
    // }

    // public void initialisePath() {
    //     // path = new ArrayList<String>();
    //     // calls = new ArrayList<Character>();
    //     // rung = new ArrayList<Integer>();

    //     path = predecessor.path;
    //     calls = predecessor.calls;
    //     rung = predecessor.rung;
    //     //rung = new BitSet(40320);
    //     // p.add(r);
    //     // path.add("53246");
    // }

    // public void addToPath(String r) {
    //     path.add(r);
    // }

    public void setVisited(boolean b) {
        visited = b;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setPredecessor(lead_vertex v) {
        predecessor = v;
    }

    public lead_vertex[] getSuccessors() {
        lead_vertex[] successors = { plain, bob };
        return (successors);
    }

}
