public class lead_dfs {
    public static void main(String[] args) {
        // lead_vertex v = new lead_vertex("12345678", '-', new BitSet(40320));
        // v.generate_lead();

        lead_tree G = new lead_tree("12345678");
        
        G.root.initialiseRoot();

        G.root.addToPath("12345678");
        
        G.root.generate_lead();
        G.root.updateBitSet();
        //Sysem.out.println(G.root.rung.get(39781));

        G.dynamic_dfs(G.root);
        System.out.println("total number of returning paths = " + G.returns + "\ntotal number of duplicating paths = "+ G.duplicates);

    }
}
