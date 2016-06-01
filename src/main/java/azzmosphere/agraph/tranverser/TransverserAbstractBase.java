package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.subgraph.SubgraphMapperInterface;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;

/**
 * Created by aaron.spiteri on 31/05/2016.
 */
public abstract class TransverserAbstractBase implements TransverserBase {

    protected ArrayList<Integer> adjacencyMatrix;
    protected SubgraphMapperInterface mapper;
    protected ArrayList<VertexInterface> vertices;
    protected ArrayList<Edge> edges;

    @Override
    public ArrayList<VertexInterface> getVertices() {
        return vertices;
    }

    @Override
    public void setVertices(ArrayList<VertexInterface> vertices) {
        this.vertices = vertices;
    }


    @Override
    public ArrayList<Integer> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    @Override
    public void setAdjacencyMatrix(ArrayList<Integer> adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }


    @Override
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    @Override
    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public void setMapper(SubgraphMapperInterface mapper) {
        this.mapper = mapper;
    }

    /*
    * Returns the corresponding edge to v1 and v2.
    */
    protected Edge findEdge(VertexInterface v1, VertexInterface v2) {
        for (Edge e : edges) {
            if (e.containsNodes(v1, v2)) {
                return e;
            }
        }
        return null;
    }
}
