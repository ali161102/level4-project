public class main {
    public static void main(String[] args) {

        tree G = new tree("53246");
        G.root.initialisePath();
        G.root.addToPath("53246");
        // System.out.println(G.root.row);
        // System.out.println(G.root.path);
        // v.setSuccessors();
        // vertex[] vs = v.getSuccessors();
        // System.out.println(vs[0].row + " " + vs[1].row + " " + vs[2].row);

        G.dynamic_dfs(G.root);
    }
}
