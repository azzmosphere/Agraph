package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.edge.EdgeUtil;
import azzmosphere.agraph.plane.GraphUtils;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperInterface;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Created by aaron.spiteri on 27/05/2016.
 */
public class PolyhedronDFS implements TranverserInterface {
    private ArrayList<Edge> edges;
    private ArrayList<Integer> adjacencyMatrix;
    private ArrayList<VertexInterface> vertices;
    private SubgraphMapperInterface mapper;

    public PolyhedronDFS(SubgraphMapperInterface mapper) {
        this.mapper = mapper;
    }

    @Override
    public LinkedHashSet<SubgraphInterface> findAllSubgraphs() throws Exception {
        LinkedHashSet<SubgraphInterface> faces = new LinkedHashSet<>();

        for (VertexInterface v : vertices) {
            dfsPrimer(v, faces);

            if (GraphUtils.isPolyhedron(vertices.size(), edges.size(), faces.size())) {
                break;
            }
        }
        return faces;
    }

    @Override
    public LinkedHashSet<SubgraphInterface> findAllSubgraphs(VertexInterface v) throws Exception {
        LinkedHashSet<SubgraphInterface> faces = new LinkedHashSet<>();
        dfsPrimer(v, faces);

        return faces;
    }

    @Override
    public boolean isBalanced() {
        return false;
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

    @Override
    public void setMapper(SubgraphMapperInterface mapper) {
        this.mapper = mapper;
    }

    /*
     * Returns the corresponding edge to v1 and v2.
     */
    private Edge findEdge(VertexInterface v1, VertexInterface v2) {
        for (Edge e : edges) {
            if (e.containsNodes(v1, v2)) {
                return e;
            }
        }
        return null;
    }

    /*
     * Returns how many bits have been set in a bitmask.
     */
    private static int dimCount(int dimensions) {
        // Java: use >>> instead of >>
        // C or C++: use uint32_t
        dimensions = dimensions - ((dimensions >>> 1) & 0x55555555);
        dimensions = (dimensions & 0x33333333) + ((dimensions >>> 2) & 0x33333333);
        return (((dimensions + (dimensions >>> 4)) & 0x0F0F0F0F) * 0x01010101) >>> 24;
    }

    private boolean dfsPrimer(VertexInterface v, LinkedHashSet<SubgraphInterface> faces) throws Exception {
        boolean rv = false;
        for (int nodeId : GraphUtils.getAdjacentVertices(v.getId(), adjacencyMatrix)) {
            SubgraphInterface currentPath = mapper.getSubGraphObject(PolyhedronDFS.class.getCanonicalName());
            VertexInterface v2 = vertices.get(nodeId);
            Edge edge = findEdge(v, v2);
            currentPath.addVertex(v);
            currentPath.addVertex(v2);
            currentPath.addEdge(edge);
            int dimensions = 0 | edge.getJoiningAxis().getBitMask();
            rv = dfs(faces, v, v2, currentPath, dimensions);
        }
        return rv;
    }

    protected boolean dfs(LinkedHashSet<SubgraphInterface> faces,
                        VertexInterface requiredNode,
                        VertexInterface currentNode,
                        SubgraphInterface currentPath,
                        int dimensions) throws Exception {

        for (int nodeId : GraphUtils.getAdjacentVertices(currentNode.getId(), adjacencyMatrix)) {
            if (currentPath.getEdges().contains(findEdge(currentNode, vertices.get(nodeId)))) {
                continue;
            }

            Edge edge = findEdge(currentNode, vertices.get(nodeId));
            if (nodeId == requiredNode.getId()) {
                currentPath.addEdge(edge);
                return checkPolygonProps(faces, currentPath, dimensions);
            }

            if ((edge.getJoiningAxis().getBitMask() & dimensions) != edge.getJoiningAxis().getBitMask()) {
                if (dimCount(dimensions) <= 2) {
                    dimensions |= edge.getJoiningAxis().getBitMask();
                }
                else {
                    return false;
                }
            }

            SubgraphInterface newPath = currentPath.clone();
            VertexInterface nextVertex = vertices.get(nodeId);
            newPath.addEdge(edge);
            newPath.addVertex(nextVertex);
            dfs(faces, requiredNode, nextVertex, newPath, dimensions);
        }
        return false;
    }

    /*
     * Compute the sum of all angles in the graph and if they match the rounded value if 180 then
     * add them to the faces
     */
    private boolean checkPolygonProps(LinkedHashSet<SubgraphInterface> faces,
                               SubgraphInterface currentPath,
                               int dimensions) {

        float angleSum = 0;
        Edge e1, e2 = null;

        // Must have two dimensions
        if (dimCount(dimensions) != 2) {
            return false;
        }

        // Must add up to 180 (triangle). or mutiple. A square is two triangles, so
        // would be 180 * 2 = 360.
        for (int i = 1; i < currentPath.getEdges().size(); i++) {
            e1 = (Edge) currentPath.getEdges().toArray()[i - 1];
            e2 = (Edge) currentPath.getEdges().toArray()[i];

            angleSum += EdgeUtil.computeAngle(e1, e2);
        }

        // Last angle is outside of the loop.
        e1 = (Edge) currentPath.getEdges().toArray()[0];
        angleSum += EdgeUtil.computeAngle(e1, e2);

        if (Math.round(angleSum) == 180) {
            faces.add(currentPath);
            return true;
        }
        return false;
    }
}
