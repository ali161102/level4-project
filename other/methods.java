import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class methods {
    /* class for generating leads and courses based on methods */

    public static void addMethod(String filename, int lead_length) {
        ArrayList<String> lead = new ArrayList<String>();
        try {
            File rows = new File(filename);
            Scanner myReader = new Scanner(rows);
            for (int i = 0; i < lead_length+1; i++) {
                String row = myReader.nextLine();
                lead.add(row);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("no such file found");
      // e.printStackTrace();
        }
        createArray(lead);
        // print(lead);
    }

    public static void createArray(ArrayList<String> lead) {
        // int[][] method = new int[lead.get(0).length()][lead.size()];
        ArrayList<ArrayList<Integer>> method = new ArrayList<ArrayList<Integer>>();
        System.out.println(lead);
        // System.out.println(method);

        for (int i = 0; i < 6; i++) {
            method.add(new ArrayList<Integer>());
        }

        for (String row : lead) {
            for (int i = 0; i < row.length(); i++) {
                char c = row.toCharArray()[i];
                int bell = Character.getNumericValue(c);
                method.get(bell-1).add(i);
            }
        }
        String method_str = method.toString();
        String replacedBrackets = method_str.replace("[", "{").replace("]", "}");

        System.out.println(replacedBrackets);
    }

    
    public static ArrayList<String> lead(String head) {
        int[][] method = new int[][]{{0, 1, 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 5, 4, 5, 4, 3, 2, 3, 2, 1, 0, 1, 0, 0}, {1, 0, 1, 0, 0, 1, 1, 0, 1, 2, 3, 3, 4, 5, 4, 5, 5, 4, 5, 4, 5, 5, 4, 5, 4}, {2, 2, 3, 4, 5, 4, 5, 4, 3, 3, 2, 2, 1, 0, 1, 2, 2, 3, 2, 3, 3, 4, 5, 4, 5}, {3, 3, 2, 2, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 2, 2, 3, 3, 2}, {4, 5, 4, 3, 3, 2, 3, 2, 2, 1, 0, 1, 2, 2, 3, 3, 4, 5, 4, 5, 4, 3, 2, 2, 3}, {5, 4, 5, 5, 4, 5, 4, 5, 5, 4, 5, 4, 3, 3, 2, 1, 0, 1, 1, 0, 0, 
            1, 0, 1, 1}};

        ArrayList<String> lead = new ArrayList<String>();

        for (int i=0; i < method[0].length; i++) {
            char[] row = new char[method.length];
            for (int j = 0; j < method.length; j++) {
                row[method[j][i]] = head.charAt(j);
            }
            lead.add(new String(row));
        }

        return lead;
    }

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

    public static void printCourse(String head) {
        String lead_end = new String();
        String lead_head = head;
        while (!lead_end.equals(head)) {
            ArrayList<String> lead = lead(lead_head);
            lead_end = lead.get(lead.size()-1);
            print(lead);
            lead_head = lead_end;
        } 
    }

    public static void print(ArrayList<String> lead) {
        lead.remove(lead.size()-1);
        for (String row : lead) {
            System.out.println(row.replace("", " ").trim());
        }
        System.out.println("------------");
    }

    public static void main(String[] args) {

        // addMethod("NSM_rows.txt", 24);

        // print(lead("164523"));
        printCourse("123456");

    }
}
