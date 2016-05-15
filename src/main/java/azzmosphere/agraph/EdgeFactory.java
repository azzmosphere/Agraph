package azzmosphere.agraph;

import azzmosphere.agraph.vertices.VertexInterface;

/**
 * Create a edge between two nodes.
 *
 * Created by aaron.spiteri on 12/05/2016.
 */
public class EdgeFactory {
    public static Edge createEdge(VertexInterface v1, VertexInterface v2) {
        Edge e = new Edge();

        e.setTail(v1);
        e.setHead(v2);
        v1.getEdges().add(e);

        return e;
    }
}
