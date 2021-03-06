package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperInterface;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Created by aaron.spiteri on 31/05/2016.
 */
public interface TransverserBase {


    ArrayList<VertexInterface> getVertices();

    void setVertices(ArrayList<VertexInterface> vertices);

    ArrayList<Integer> getAdjacencyMatrix();

    void setAdjacencyMatrix(ArrayList<Integer> adjacencyMatrix);

    ArrayList<Edge> getEdges();

    void setEdges(ArrayList<Edge> edges);

    void setMapper(SubgraphMapperInterface mapper);

    LinkedHashSet<SubgraphInterface> findAllSubgraphs() throws Exception;
}
