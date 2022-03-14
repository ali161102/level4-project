public class lead_vertex {
    // class to represent a node of the single-part search tree

    public String lead_head;

    public Boolean visited;
    
    public char call;

    public lead_vertex predecessor, plain, bob;

    public lead_vertex(String row, char c) {
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

    public void setPredecessor(lead_vertex v) {
        predecessor = v;
    }

    public lead_vertex[] getSuccessors() {
        lead_vertex[] successors = { plain, bob };
        return (successors);
    }

}
