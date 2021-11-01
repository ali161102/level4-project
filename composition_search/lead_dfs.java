public class lead_dfs {
    public static void main(String[] args) {
        // lead_vertex v = new lead_vertex("12345678", '-', new BitSet(40320));
        // v.generate_lead();

        lead_tree G = new lead_tree("12345678");
        
        G.initialisePath();

        G.addToPath("12345678");
        
        G.generate_lead(G.root);

        G.updateBitSet();
        //Sysem.out.println(G.root.rung.get(39781));

        G.dynamic_dfs(G.root);
        System.out.println("total number of returning paths = " + G.returns + "\ntotal number of duplicating paths = "+ G.duplicates);

    }
}
