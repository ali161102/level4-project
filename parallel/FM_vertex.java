public class FM_vertex {
    // class to represent a node of the single-part search tree (for full monty search)

    public String lead_head;

    public Boolean visited;
    
    public char call;

    public FM_vertex predecessor, plain, bob, single;

    public FM_vertex(String row, char c) {
        lead_head = row;
        visited = false;
        call = c;
    }

    public void setVisited(boolean b) {
        visited = b;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setPredecessor(FM_vertex v) {
        predecessor = v;
    }

    public FM_vertex[] getSuccessors() {
        FM_vertex[] successors = { plain, bob, single };
        return (successors);
    }

}
