import java.util.ArrayList;

public class methods {
    /* class for generating leads and courses based on methods */

    public static ArrayList<String> CSM_lead(String head) {
        int[][] csm = new int[][]{{0, 1, 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 5, 4, 5, 4, 3, 2, 3, 2, 1, 0, 1, 0, 0}, 
                                  {1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 2, 3, 4, 5, 4, 5, 4, 5, 5, 4, 5},
                                  {2, 3, 4, 5, 4, 5, 4, 5, 5, 4, 5, 4, 4, 5, 4, 5, 5, 4, 5, 4, 5, 4, 3, 2, 3},
                                  {3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2, 3, 2, 1, 0, 1, 0, 0, 1, 2, 3, 4, 5, 4},
                                  {4, 5, 5, 4, 5, 4, 5, 4, 3, 2, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1},
                                  {5, 4, 3, 2, 1, 0, 0, 1, 0, 1, 2, 3, 2, 3, 3, 2, 2, 3, 2, 3, 3, 2, 2, 3, 2}};

        
        ArrayList<String> lead = new ArrayList<String>();

        for (int i=0; i < csm[0].length; i++) {
            char[] row = new char[csm.length];
            for (int j = 0; j < csm.length; j++) {
                row[csm[j][i]] = head.charAt(j);
            }
            lead.add(new String(row));
        }

        return lead;
    }

    public static void print(ArrayList<String> lead) {
        lead.remove(lead.size()-1);
        for (String row : lead) {
            System.out.println(row.replace("", " ").trim());
        }
        System.out.println("------------");
    }

    public static void main(String[] args) {
        print(CSM_lead("123456"));
    }
}
