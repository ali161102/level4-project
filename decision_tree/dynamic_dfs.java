public class dynamic_dfs {
    public static void main(String[] args) {

        tree G = new tree("53246");
        
        G.root.initialisePath();
        G.root.addToPath("53246");

        G.dynamic_dfs(G.root);
        System.out.println("total number of returning paths = " + G.returns + "\ntotal number of duplicating paths = "+ G.duplicates);
    }
}
