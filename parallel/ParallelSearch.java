import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class ParallelSearch {

       private static int[][] method;

    /* read in file and create method representation */

    public static int[][] readMethod(String filename, int lead_length) {
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

        }
        int[][] method_array = createArray(lead);
        method = method_array;
        return(method_array);
    
    }

    public static int[][] createArray(ArrayList<String> lead) {
        // int[][] method = new int[lead.get(0).length()][lead.size()];
        ArrayList<ArrayList<Integer>> newmethod = new ArrayList<ArrayList<Integer>>();
        int numbells = lead.get(0).length();


        for (int i = 0; i < numbells; i++) {
            newmethod.add(new ArrayList<Integer>());
        }

        for (String row : lead) {
            for (int i = 0; i < numbells; i++) {
                char c = row.toCharArray()[i];
                int bell = Character.getNumericValue(c);
                newmethod.get(bell-1).add(i);
            }
        }


        int[][] method_array = new int[newmethod.size()][newmethod.get(0).size()];

        for (int i = 0; i < newmethod.size(); i++) {
            for (int j = 0; j < newmethod.get(0).size(); j++) {
                method_array[i][j] = newmethod.get(i).get(j);
            }
        }

        return(method_array);
    }

    /* generate the starting data for the threads */
    
    private static ArrayList<String> create_input(int num_paths, int len_paths) {
        ArrayList<String> paths = new ArrayList<String>();

        for (int i = 0; i < num_paths; i++) {
            String str = Integer.toBinaryString(i);
            while (str.length() < len_paths) {
                str = "0" + str;
            }
            paths.add(str.replace('0', 'p').replace('1', 'b'));
            // 0 = plain, 1 = bob

        }
        return(paths);
    }

    private static StartData create_data(String inputcalls) {
        BitSet rung = new BitSet(40320);
        ArrayList<String> path = new ArrayList<String>();
        ArrayList<Character> calls = new ArrayList<Character>();

        // initialise the BitSet and path with the starting lead (rounds)
        path.add("12345678");
        rung = addToBitSet(rung, lead("12345678"));
        
        String current_lh = "12345678"; // starts at rounds

        for (char call : inputcalls.toCharArray()) {
            String next_lh_plain = next_lh_plain(current_lh); // the next lead head if no bob

            if (call == 'p') {
                // plain at end of lead
                calls.add('p');
                current_lh = next_lh_plain;
            } else {
                if (call == 'b') {
                    // bob at end of lead
                    current_lh = "" + next_lh_plain.charAt(0) + next_lh_plain.charAt(3) + next_lh_plain.substring(1, 3) + next_lh_plain.substring(4);

                    // determine what type of bob it is by the position of the tenor bell
                    switch (current_lh.indexOf("8")) {
                        case 5:
                            calls.add('M');
                            break;
                        case 6:
                            calls.add('W');
                            break;
                        case 7:
                            calls.add('H');
                            break;
                        case 2:
                            calls.add('B');
                            break;
                        default:
                            calls.add('x');

                    }
                }
            }

            if (!tenorsTogether(current_lh)) {
                // this pattern of calls is not one that can be explored in the search, so stop here
                return null;
            }

            path.add(current_lh);
            rung = addToBitSet(rung, lead(current_lh));

            if (rung == null) {
                // this pattern of calls leads to internal falseness, which is not included
                // in our search, so stop here
                return null;
            }

        }
        
        //System.out.println(calls + "\n" + path + "\n");
        return(new StartData(calls, path, rung));
    }

    public static ArrayList<String> lead(String lead_head) {
        // doesn't include next lead head

        ArrayList<String> currentlead = new ArrayList<String>();
        for (int i=0; i < method[0].length-1; i++) {
            char[] row = new char[method.length];
            for (int j = 0; j < method.length; j++) {
                row[method[j][i]] = lead_head.charAt(j);
            }
            currentlead.add(new String(row));
        }

        return(currentlead);

    }

    public static BitSet addToBitSet(BitSet rung, ArrayList<String> lead) {

        // temporary array for ints being added in this lead
        ArrayList<Integer> added = new ArrayList<Integer>();

        for (String row : lead) {
            
            int rowInt = rowToInt(row);

            if (rung.get(rowInt)) {
                // one row of this lead was already rung, so return false and set all
                // the previous rows from this lead back to unrung

                for (int i : added) {
                    rung.set(i, false);
                }
                
                return null;
            }
            rung.set(rowInt);
            added.add(rowInt);
        
        }
        return rung;
    }

    public static int rowToInt(String rowstr) {
        int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040}; // lookup table for n!, 0 <= n <= 7
        int numbells = rowstr.length();                     // number of bells in the row
        
        // turn string into array of int
        char[] rowchars = rowstr.toCharArray();
        int[] row = new int[numbells];

        // the "value" of this row as an int, [0, (n!)-1]
        int value = 0;

        // subtract 1 from every digit
        for (int i = 0; i < numbells; i++) {
            row[i] = Character.getNumericValue(rowchars[i]) - 1;
        }

        for (int i = 0; i < numbells; i++) {                // for each digit
            value += row[i] * factorial[numbells - 1 - i];  // multiply by (len-1-index)! and add to total
            for (int j = i+1; j < numbells; j++) { 
                if (row[j] > row[i]) {                      // if any digits to the right are greater
                    row[j]--;                               // subtract 1 from them
                }
            }
        }
        return value;
    }

    public static Boolean tenorsTogether (String lh) {
        // 17864523 -> 8765324
        //             21357642
        String course_order = "" + lh.charAt(2) + lh.charAt(1) + lh.charAt(3) + lh.charAt(5) + lh.charAt(7) + lh.charAt(6) + lh.charAt(4) + lh.charAt(2);
        if (course_order.contains("87")) {
            return true;
        }
        return false;
    }

    public static String next_lh_plain(String lh) {
        char[] row = new char[method.length];
        for (int bell = 0; bell < method.length; bell++) {
            row[method[bell][method[0].length-1]] = lh.charAt(bell);
        }
        return(new String(row));
    }

    public static void main(String[] args) {
        
        /* configuration */
        String method_filename = "../methods/cambridge_surprise_major.txt";
        int lead_length = 32;

        int len_paths = 6; // length of call strings (eg 'ppbp' = 4)
        int num_paths = 32; // number of call strings to generate

        // ****


        readMethod(method_filename, lead_length);

        int num_started = 0;

        for (String path : create_input(num_paths, len_paths)) {
            
            StartData sd = create_data(path);

            if (sd != null) {
                num_started++;
                SearchRunnable sr = new SearchRunnable(num_started, method, sd);
                System.out.println(sr.path);

                Thread t = new Thread(sr); 
                t.start();                
                
            }
            
            
        }

        // SearchRunnable r = new SearchRunnable(null, null, null, null);
        // Thread t = new Thread(r); // pass the Runnable object while new creating Thread object.
        // t.start();

        // for (int i = 0; i < 10; i++) {
        //         System.out.println("Main Thread.");
        // }
    }
}