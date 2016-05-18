package azzmosphere.agraph.face;

import azzmosphere.agraph.Edge;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 *
 * A closed walk consists of a sequence of vertices starting and ending at the same vertex, with each two consecutive
 * vertices in the sequence adjacent to each other in the graph. In a directed graph, each edge must be traversed by the
 * walk consistently with its direction: the edge must be oriented from the earlier of two consecutive vertices to the
 * later of the two vertices in the sequence. The choice of starting vertex is not important: traversing the same cyclic
 * sequence of edges from different starting vertices produces the same closed walk.
 *
 * A simple cycle may be defined either as a closed walk with no repetitions of vertices and edges allowed, other than
 * the repetition of the starting and ending vertex, or as the set of edges in such a walk. The two definitions are
 * equivalent in directed graphs, where simple cycles are also called directed cycles: the cyclic sequence of vertices
 * and edges in a walk is completely determined by the set of edges that it uses. In undirected graphs the set of edges
 * of a cycle can be traversed by a walk in either of two directions, giving two possible directed cycles for every
 * undirected cycle. (For closed walks more generally, in directed or undirected graphs, the multiset of edges does not
 * unambiguously determine the vertex ordering.) A circuit can be a closed walk allowing repetitions of vertices but not
 * edges; however, it can also be a simple cycle, so explicit definition is recommended when it is used.
 *
 * ref( https://en.wikipedia.org/wiki/Cycle_%28graph_theory%29 )
 *
 * Created by aaron.spiteri on 16/05/2016.
 */

public class SimpleCycle implements  FaceInterface {
    private LinkedHashSet<VertexInterface> vertices = new LinkedHashSet<>();
    private LinkedHashSet<Edge> edges = new LinkedHashSet<>();

    /**
     * EDGE_DIRECTION is
     * FORWARD edge is traversing tail to head
     * BACKWARDS edge is traversing head to tail.
     */
    public enum EDGE_DIRECTION {
        FORWARD,
        BACKWARD;
    }

    /**
     * vertices in face in order that they were traversed.
     *
     * @return vertices
     */
    @Override
    public LinkedHashSet<VertexInterface> getVertices() {
        return vertices;
    }

    /**
     * edges within the subgraph in the order that they were transversed
     *
     * @return edges
     */
    @Override
    public LinkedHashSet<Edge> getEdges() {
        return edges;
    }

    /**
     * Adds a edge to the sub graph and consrquence the vertices as well.
     *
     * @param e Edge to add
     * @param direction direction to transverse.
     */
    public void addEdge(Edge e, EDGE_DIRECTION direction) {
        edges.add(e);

        if (direction.equals(EDGE_DIRECTION.BACKWARD)) {
            vertices.add(e.getHead());
            vertices.add(e.getTail());
        }
        else {
            vertices.add(e.getTail());
            vertices.add(e.getHead());
        }
    }

    /**
     * Determines by assuming that v1 -> v2 is the direction that is wanted.
     *
     * @param e  Edge that vertices belong too.
     * @param v1 Start vertex
     * @param v2 End vertex
     */
    @Override
    public void addEdge(Edge e, VertexInterface v1, VertexInterface v2) {
        if (e.getTail().isEqual(v1)) {
            addEdge(e, EDGE_DIRECTION.FORWARD);
        }
        else {
            addEdge(e, EDGE_DIRECTION.BACKWARD);
        }
    }

    @Override
    public void addVertex(VertexInterface v) {
        vertices.add(v);
    }

    @Override
    public void addEdge(Edge e) {
        edges.add(e);
    }
}
