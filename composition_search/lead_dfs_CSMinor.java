public class lead_dfs_CSMinor {
    public static void main(String[] args) {
        // lead_vertex v = new lead_vertex("12345678", '-', new BitSet(40320));
        // v.generate_lead();

        lead_tree_CSMinor G = new lead_tree_CSMinor("123456");
        
        G.initialisePath();

        G.addToPath("123456");
        
        G.generate_lead(G.root);

        G.updateBitSet();
        //Sysem.out.println(G.root.rung.get(39781));

        G.dynamic_dfs(G.root);
        System.out.println("total number of returning paths = " + G.returns);
    }
}
