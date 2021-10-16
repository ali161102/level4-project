import java.util.ArrayList;

public class adjListVertex {
    public String row;
    public ArrayList<adjListNode> adjList;
    public boolean visited;

    public adjListVertex(String r) {
        row = r;
        adjList = new ArrayList<adjListNode>();
    }

    public String getRow() {
        return row;
    }

    public ArrayList<String> getAdjList() {
        ArrayList<String> al = new ArrayList<String>();
        for (adjListNode n : adjList) {
            al.add(n.getVertexRow());
            al.add(Character.toString(n.transformation));
        }
        return al;
    }

    public void addToAdjList(adjListVertex v, char type) {
        adjList.add(new adjListNode(v, type));
    }

    public void setVisited(boolean b) {
        visited = b;
    }

    public boolean getVisited() {
        return visited;
    }
}
