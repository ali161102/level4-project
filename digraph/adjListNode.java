
public class adjListNode {

    public adjListVertex vertex;
    public char transformation;

    public adjListNode(adjListVertex v, char t) {
        vertex = v;
        transformation = t;
    }

    public String getVertexRow() {
        return vertex.row;
    }

}
