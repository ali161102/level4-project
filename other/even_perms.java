import java.util.ArrayList;

public class even_perms {

    // public static ArrayList<String> permutations;

    public static void main(String[] args) {

        
        System.out.println(permutations);

        // String start = "53246"

        // String wrong = init.substring(1, 3) + init.charAt(0) + init.substring(3, 5);
        // String home = init.charAt(0) + init.substring(2, 4) + init.charAt(1) +
        // init.charAt(4);
        // String middle = init.substring(0, 2) + init.substring(3, 5) + init.charAt(2);

        ArrayList<String> perms = permutation("23456");
        swaps("23456");
        for (String perm : perms) {
        break;
        // }
    }

}
