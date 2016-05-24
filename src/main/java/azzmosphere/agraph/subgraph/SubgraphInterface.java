package azzmosphere.agraph.subgraph;

import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.vertices.VertexInterface;
import java.util.LinkedHashSet;

/**
 *
 * Common interface for describing subgraphs.
 *
 * Created by aaron.spiteri on 16/05/2016.
 */
public interface SubgraphInterface {

    LinkedHashSet<VertexInterface> getVertices();
    LinkedHashSet<Edge> getEdges();
    void addEdge(Edge e, VertexInterface v1, VertexInterface v2);
    void addEdge(Edge e);
    void addVertex(VertexInterface v);
    SubgraphInterface clone() throws CloneNotSupportedException;
}
