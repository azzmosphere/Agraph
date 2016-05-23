package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.plane.GraphUtils;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 *
 * Detect polygons within a polyhedron graph.
 *
 *
 * TODO: This is a work in progress.
 *
 * The way it is planned to work is that it will use a DFS search to transverse a path, as it is doing this it computes
 * the inner edge of each angle in the polygon it is trying to detect.
 *
 * If it:
 *   a) Reaches the required vertex and the sum of the angles is not 180 degrees; or
 *   b) The sum of the angles before it reaches the required vertex is greater the the absolute value of 180
 *
 * the path is ignored because it is not a polygon.
 *
 * it transverses every connected edge to the starting vertex in order to do this. To detect all faces it does this
 * for every vertex on the graph and ignores any duplicated faces.  The space required for this is:
 *
 *  O(e) and the complexity is O(e log e) where e is the number of edges.
 *
 *
 * Created by aaron.spiteri on 21/05/2016.
 */
public class PolyhedronDFS implements TranverserInterface {
    private ArrayList<Edge> edges;
    private ArrayList<Integer> adjacencyMatrix;
    private ArrayList<VertexInterface> vertices;

    @Override
    public LinkedHashSet<SubgraphInterface> findAllSubgraphs() {
        return null;
    }

    @Override
    public LinkedHashSet<SubgraphInterface> findAllSubgraphs(VertexInterface v) {
        return null;
    }

    @Override
    public boolean isBalanced() {
        return GraphUtils.isPolyhedron(vertices.size(), edges.size(), findAllSubgraphs().size());
    }

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
}
