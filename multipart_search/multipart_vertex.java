import java.util.ArrayList;
import java.util.BitSet;

public class multipart_vertex {
    
    // store all lead heads, one for each part
    public ArrayList<String> lead_heads;

    public Boolean visited;

    public char call;

    public multipart_vertex predecessor, plain, bob;

    public multipart_vertex(ArrayList<String> rows, char c) {
        lead_heads = rows;
        visited = false;
        call = c;
    }

    public void setVisited(boolean b) {
        visited = b;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setPredecessor(multipart_vertex v) {
        predecessor = v;
    }

    public multipart_vertex[] getSuccessors() {
        multipart_vertex[] successors = { plain, bob };
        return (successors);
    }

}
