import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.CharacterCodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

public class multipart_tree {

    public multipart_vertex root;
    public int duplicates;
    public int returns;

    public static int[][] method; // the ringing method used to search
    public static ArrayList<Character> allowed_calls; // eg B,M, H

    public BitSet rung;

    // store one lead for each part
    public ArrayList<ArrayList<String>> leads;

    public ArrayList<Character> calls;

    // store the path of lead heads for each part
    public ArrayList<ArrayList<String>> paths;

    public multipart_tree(ArrayList<String> rows) {
        root = new multipart_vertex(rows, '-');
        duplicates = 0;
        returns = 0;
    }

    public static int[][] readMethod(String filename, int lead_length) {
        ArrayList<String> lead = new ArrayList<String>();
        try {
            File rows = new File(filename);
            Scanner myReader = new Scanner(rows);
            for (int i = 0; i < lead_length + 1; i++) {
                String row = myReader.nextLine();
                lead.add(row);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("no such file found");

        }
        int[][] method_array = createArray(lead);
        method = method_array;
        return (method_array);

    }

    public void setMethod(int[][] method_array) {
        method = method_array;
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
                newmethod.get(bell - 1).add(i);
            }
        }

        int[][] method_array = new int[newmethod.size()][newmethod.get(0).size()];

        for (int i = 0; i < newmethod.size(); i++) {
            for (int j = 0; j < newmethod.get(0).size(); j++) {
                method_array[i][j] = newmethod.get(i).get(j);
            }
        }

        return (method_array);
    }

    public static void setAllowedCalls(char[] calls) {
        for (char call : calls) {
            allowed_calls.add(call);
        }

    }

    public static void printLead(String leadhead, int[][] method) {
        // converter from a leadhead to its respective lead (cambridge surprise major)
        // includes next lead head

        ArrayList<String> currentlead = new ArrayList<String>();

        for (int i = 0; i < method[0].length; i++) {
            char[] row = new char[method.length];
            for (int j = 0; j < method.length; j++) {
                row[method[j][i]] = leadhead.charAt(j);
            }
            currentlead.add(new String(row));
        }
        System.out.println("printLead:________");
        for (String row : currentlead) {
            System.out.println(row.replace("", " ").trim());
        }
        System.out.println("------------");
    }

    public ArrayList<Character> condensePlains(ArrayList<Character> calls) {

        int plains = 0;
        ArrayList<Character> finalcalls = new ArrayList<Character>();

        for (char c : calls) {
            if (c == 'p') {
                plains++;
            } else {
                if (plains > 0) {
                    char plainschar = (char) ('0' + plains);

                    // plainschar = '-';
                    // finalcalls.add(plainschar);
                }
                plains = 0;
                finalcalls.add(c);
            }
        }

        return (finalcalls);

    }

    public void debugprint(lead_vertex v) {
        System.out.println("current lh = " + v.lead_head);
        System.out.println(paths);
        System.out.println(calls);

    }

    public static Boolean tenorsTogether(String lh) {
        // 17864523 -> 8765324
        // 21357642
        String course_order = "" + lh.charAt(2) + lh.charAt(1) + lh.charAt(3) + lh.charAt(5) + lh.charAt(7)
                + lh.charAt(6) + lh.charAt(4) + lh.charAt(2);
        if (course_order.contains("87")) {
            return true;
        }
        return false;
    }

    public ArrayList<String> lead(String lead_head) {
        // doesn't include next lead head

        ArrayList<String> currentlead = new ArrayList<String>();
        for (int i = 0; i < method[0].length - 1; i++) {
            char[] row = new char[method.length];
            for (int j = 0; j < method.length; j++) {
                row[method[j][i]] = lead_head.charAt(j);
            }
            currentlead.add(new String(row));
        }

        return (currentlead);

    }

    public void generate_leads(multipart_vertex v) {

        System.out.println("starting generate_leads with vertex" + v.lead_heads);
        // includes next lead head

        ArrayList<String> currentlead = new ArrayList<String>();
        ArrayList<ArrayList<String>> currentleads = new ArrayList<ArrayList<String>>();

        // all next lead heads (one per part) if the next call is plain/bob
        ArrayList<String> next_lhs_plain = new ArrayList<String>();
        ArrayList<String> next_lhs_bob = new ArrayList<String>();

        // for each lead head in this vertex's lead_heads array
        for (String lead_head : v.lead_heads) {
            System.out.println("for " + lead_head + " in " + v.lead_heads);
            // generate the respective lead
            for (int i = 0; i < method[0].length; i++) {
                char[] row = new char[method.length];
                for (int j = 0; j < method.length; j++) {
                    row[method[j][i]] = lead_head.charAt(j);
                }
                currentlead.add(new String(row));
                System.out.println("added to currentlead - " + currentlead.get(currentlead.size() - 1));
            }
            // currentlead has the whole lead + next leadhead of a plain

            String next_lh_plain = currentlead.get(currentlead.size() - 1).toString();
            System.out.println("next_lh_plain = " + next_lh_plain);

            next_lhs_plain.add(next_lh_plain);
            next_lhs_bob.add("" + next_lh_plain.charAt(0) + next_lh_plain.charAt(3) + next_lh_plain.substring(1, 3)
                    + next_lh_plain.substring(4));

            // remove the next lead head from currentleads
            currentlead.remove(currentlead.size() - 1);

            currentleads.add(currentlead);

        }

        char call = 'b';

        // work out what type of call the bob is
        switch (next_lhs_bob.get(0).indexOf("8")) {
            case 5:
                call = 'M';
                break;
            case 6:
                call = 'W';
                break;
            case 7:
                call = 'H';
                break;
            case 2:
                call = 'B';
                break;
            default:
                call = 'x';

        }

        // create successor vertices
        v.plain = new multipart_vertex(next_lhs_plain, 'p');
        v.bob = new multipart_vertex(next_lhs_bob, call);

        // temporarily exclude Before calls here
        if (call == 'B') {
            v.bob.setVisited(true);
        }

        // if (!tenorsTogether(next_lh_bob)) {
        // v.bob.setVisited(true);
        // }

        // System.out.println(next_lh_plain + " " + next_lh_bob);
        leads = currentleads;

    }

    public static int rowToInt(String rowstr) {
        int[] factorial = { 1, 1, 2, 6, 24, 120, 720, 5040 }; // lookup table for n!, 0 <= n <= 7
        int numbells = rowstr.length(); // number of bells in the row

        // turn string into array of int
        char[] rowchars = rowstr.toCharArray();
        int[] row = new int[numbells];

        // the "value" of this row as an int, [0, (n!)-1]
        int value = 0;

        // subtract 1 from every digit
        for (int i = 0; i < numbells; i++) {
            row[i] = Character.getNumericValue(rowchars[i]) - 1;
        }

        for (int i = 0; i < numbells; i++) { // for each digit
            value += row[i] * factorial[numbells - 1 - i]; // multiply by (len-1-index)! and add to total
            for (int j = i + 1; j < numbells; j++) {
                if (row[j] > row[i]) { // if any digits to the right are greater
                    row[j]--; // subtract 1 from them
                }
            }
        }
        return value;
    }

    public boolean updateBitSet(ArrayList<ArrayList<String>> leads) {
        // temporary array for ints being added in this lead
        ArrayList<Integer> added = new ArrayList<Integer>();

        for (ArrayList<String> lead : leads) {
            for (String row : lead) {

                int rowInt = rowToInt(row);

                if (rung.get(rowInt)) {
                    // one row of this lead was already rung, so return false and set all
                    // the previous rows that were added back to false

                    for (int i : added) {
                        rung.set(i, false);
                    }

                    return false;

                }
                rung.set(rowInt);
                added.add(rowInt);

            }

        }
        return true;
    }

    public void removeFromBitSet(String lead_head) {
        ArrayList<String> lead = lead(lead_head);
        for (String row : lead) {
            rung.set(rowToInt(row), false);
        }
    }

    public void initialisePaths(ArrayList<String> part_heads) {
        paths = new ArrayList<ArrayList<String>>();
        for (String part_head : part_heads) {
            ArrayList<String> path = new ArrayList<String>();
            path.add(part_head);
            paths.add(path);
        }
        calls = new ArrayList<Character>();
        rung = new BitSet(40320);
    }

    public void addToPaths(ArrayList<String> r) {
        // System.out.println(r);
        // System.out.println(paths);
        for (int i = 0; i < r.size(); i++) {
            paths.get(i).add(r.get(i));
        }
    }

    public void updateForPred() {
        for (ArrayList<String> lead : leads) {
            if (!lead.get(0).equals("12345678")) {
                for (String row : lead) {
                    rung.set(rowToInt(row), false);
                    // rungstr.remove(row);
                }
            }
        }
        // calls.remove(calls.size()-1);
        // path.remove(path.size()-1);
    }

    private void outputComposition(char lastcall) {

        int numrows = ((paths.get(0).size()) * 32);

        // length is between 5000 and 5600 inclusive, so return path
        // if ((5000 <= numrows) && (numrows <= 5600)) {
        if (numrows >= 5000) {
            returns++;

            // formatting path and calls output
            ArrayList<String> output = new ArrayList<String>();
            String stroutput = new String();
            this.calls.add(lastcall);

            for (int i = 0; i < paths.get(0).size(); i++) {

                output.add(paths.get(0).get(i));
                output.add(calls.get(i).toString());
                stroutput = String.join(" ", output);
            }

            // System.out.println(stroutput + " 12345678");
            // System.out.println(" ");

            System.out.println(
                    "composition " + returns + " is of length " + (paths.get(0).size() - 1) + " leads = " + numrows
                            + " rows");

            ArrayList<Character> changes = condensePlains(calls);

            outputCalls(changes);

            System.out.println("----\n");

            // path.remove(path.size()-1);
            calls.remove(calls.size() - 1);
        }
    }

    private void outputCalls(ArrayList<Character> calls) {
        ArrayList<Character> calling = (ArrayList<Character>) calls.clone();
        ArrayList<String> rows = new ArrayList<String>();
        boolean hasBefore = false;
        if (calling.contains('B')) {
            hasBefore = true;
        }
        int middles = 0;
        int wrongs = 0;
        int homes = 0;
        int befores = 0;
        if (calling.size() == 0) {
            System.out.println("plain course");
            return;
        }
        while (calling.size() > 0) {
            while ((calling.size() > 0) && (calling.get(0).equals('B'))) {
                befores++;
                calling.remove(0);
            }
            while ((calling.size() > 0) && (calling.get(0).equals('M'))) {
                middles++;
                calling.remove(0);
            }
            while ((calling.size() > 0) && (calling.get(0).equals('W'))) {
                wrongs++;
                calling.remove(0);
            }
            while ((calling.size() > 0) && (calling.get(0).equals('H'))) {
                homes++;
                calling.remove(0);
            }

            String row = "";
            if (hasBefore) {
                row = ("" + String.valueOf(befores) + String.valueOf(middles) + String.valueOf(wrongs)
                        + String.valueOf(homes)).replace('1', '-').replace('0', ' ');
            } else {
                row = ("" + String.valueOf(middles) + String.valueOf(wrongs) + String.valueOf(homes)).replace('1', '-')
                        .replace('0', ' ');
            }
            middles = 0;
            wrongs = 0;
            homes = 0;
            befores = 0;
            rows.add(row);
        }

        if (hasBefore) {
            System.out.println("B  M  W  H");
            for (String row : rows) {
                System.out.println(row.charAt(0) + "  " + row.charAt(1) + "  " + row.charAt(2) + "  " + row.charAt(3));
            }
        } else {
            System.out.println("M  W  H");
            for (String row : rows) {
                System.out.println(row.charAt(0) + "  " + row.charAt(1) + "  " + row.charAt(2));
            }
        }

        return;
    }

    private int dynamic_visit(multipart_vertex v, multipart_vertex p) {

        // boolean to indicate whether next step will be to go back up one level to the
        // predecessor
        boolean backToPred = false;

        v.setVisited(true); // update as now visited
        v.setPredecessor(p); // set predecessor vertex

        generate_leads(v); // creates lead and successors for vertex
        multipart_vertex[] successors = v.getSuccessors();

        // updates the current paths and call path at this node
        for (int i = 0; i < v.lead_heads.size(); i++) {
            paths.get(i).add(v.lead_heads.get(i));
        }

        /// path.add(v.lead_head);
        calls.add(v.call);

        // check if tenors together, if not then set successors as visited to not
        // continue search there
        for (String lead_head_tt : v.lead_heads) {
            if (!tenorsTogether(lead_head_tt)) {
                for (multipart_vertex s : successors) {
                    s.setVisited(true);
                }
            }
            backToPred = true;
        }

        // checks for repetition using bitset
        if (!leads.get(0).get(0).equals("12345678") && !updateBitSet(leads)) {
            duplicates++;

            for (multipart_vertex s : successors) {
                s.setVisited(true);
            }
        }

        // indicates next step is to go up one level to predecessor
        if (backToPred) {
            updateForPred();
            // returns the path, calls etc to their state before this node visited
        }

        for (multipart_vertex s : successors) { // go through all successor vertices

            // last lead head in the "main" path
            String lastInPath = paths.get(0).get(paths.get(0).size() - 1);

            // loop for "going up" the tree the correct number of levels
            while (!(lastInPath.equals(v.lead_heads.get(0)))) {

                removeFromBitSet(lastInPath);
                for (ArrayList<String> path : paths) {
                    path.remove(path.size() - 1);
                }

                calls.remove(calls.size() - 1);
                lastInPath = paths.get(0).get(paths.get(0).size() - 1);

            }

            for (String successor_lead_head : s.lead_heads) {
                if (successor_lead_head.equals("12345678")) {
                    // indicates that we have a true composition
                    s.setVisited(true);
                    outputComposition(s.call);
                }

                // prevents visiting a lead where the lead head has already been rung (false
                // composition)
                if ((rung.get(rowToInt(successor_lead_head))) && (!successor_lead_head.equals("12345678"))) {
                    s.setVisited(true);
                }

                for (String next_row : lead(successor_lead_head)) {
                    if (rung.get(rowToInt(next_row))) {
                        s.setVisited(true);
                    }
                }

                // prevents visiting leads where the tenors are not together
                if (!tenorsTogether(successor_lead_head)) {
                    s.setVisited(true);
                }
            }

            if (!s.getVisited()) {// if vertex has not been visited

                dynamic_visit(s, v); // continue dfs search from it

            }

        }

        return (returns);
    }

    /** carry out a depth first search/traversal of the graph */
    public void dynamic_dfs(multipart_vertex rt) {
        generate_leads(rt);
        multipart_vertex[] L = rt.getSuccessors();

        for (multipart_vertex v : L) {
            if (!v.getVisited()) {
                dynamic_visit(v, rt);
            }
        }
    }

    public static multipart_tree initialiseRoot(ArrayList<String> part_heads) {

        System.out.println("initialiseRoot " + part_heads);
        // lead_tree G = new lead_tree("12345678");
        // G.initialisePath();
        // G.addToPath("12345678");
        // G.generate_lead(G.root);
        multipart_tree G = new multipart_tree(part_heads);

        G.initialisePaths(part_heads);
        // G.addToPaths(part_heads);
        G.generate_leads(G.root);
        G.updateBitSet(G.leads);

        return (G);
    }

    public static void main(String[] args) {

        // single part composition:
        ArrayList<String> part_heads = new ArrayList<String>(Arrays.asList("12345678", "12534678", "12453678"));
        String method_filename = "methods/cambridge_surprise_major.txt";
        // char[] allowed_calls = {'M', 'W', 'H'};

        readMethod(method_filename, 32);

        multipart_tree G = initialiseRoot(part_heads);

        G.dynamic_dfs(G.root);
        System.out.println("total number of compositions = " + G.returns);

    }
}
