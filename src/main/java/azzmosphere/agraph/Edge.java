package azzmosphere.agraph;

/**
 * Connecting point of two vertices.
 *
 * Created by aaron.spiteri on 10/05/2016.
 */
public class Edge {
    private Vertex tail;
    private Vertex head;

    public Vertex getTail() {
        return tail;
    }

    public void setTail(Vertex tail) {
        this.tail = tail;
    }

    public Vertex getHead() {
        return head;
    }

    public void setHead(Vertex head) {
        this.head = head;
    }
}
