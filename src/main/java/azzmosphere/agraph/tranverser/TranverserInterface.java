package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 *
 * Interface to describe behaviour for finding subgraphs.
 *
 * Created by aaron.spiteri on 16/05/2016.
 */
public interface TranverserInterface {
    LinkedHashSet<SubgraphInterface> findAllSubgraphs();

    LinkedHashSet<SubgraphInterface> findAllSubgraphs(VertexInterface v);

    boolean isBalanced();

    ArrayList<VertexInterface> getVertices();

    void setVertices(ArrayList<VertexInterface> vertices);

    ArrayList<Integer> getAdjacencyMatrix();

    void setAdjacencyMatrix(ArrayList<Integer> adjacencyMatrix);

    ArrayList<Edge> getEdges();

    void setEdges(ArrayList<Edge> edges);

}
