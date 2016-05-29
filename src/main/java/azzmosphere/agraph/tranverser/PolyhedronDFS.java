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
    private SubgraphMapperInterface mapper;

    private int POLYGON_ANGLES_SUM = 180;


    public PolyhedronDFS(SubgraphMapperInterface mapper) {
        this.mapper = mapper;
    }

    @Override
    public LinkedHashSet<SubgraphInterface> findAllSubgraphs() throws Exception {

        LinkedHashSet<SubgraphInterface> subgraphs = new LinkedHashSet<>();

        for (VertexInterface v : vertices) {
            subgraphs = dfs(v, v, mapper.getSubGraphObject(getClass().getCanonicalName()), 0, 0, subgraphs, 0);
            if (GraphUtils.isPolyhedron(vertices.size(), subgraphs.size(), edges.size())) {
                break;
            }
        }
        return subgraphs;
    }

    @Override
    public LinkedHashSet<SubgraphInterface> findAllSubgraphs(VertexInterface v) throws Exception {

        return dfs(v, v, mapper.getSubGraphObject(getClass().getCanonicalName()), 0, 0, new LinkedHashSet<>(), 0);
    }

    @Override
    public void setMapper(SubgraphMapperInterface mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean isBalanced() {
        boolean  isPolyhedron = false;
        try {
            isPolyhedron = GraphUtils.isPolyhedron(vertices.size(), edges.size(), findAllSubgraphs().size());
        }
        catch (Exception e) {
            isPolyhedron = false;
        }
        return isPolyhedron;
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

    /*
     * Perform the DFS search.
     *
     */
    private LinkedHashSet<SubgraphInterface>   dfs(VertexInterface currentNode,
                                                   VertexInterface required,
                                                   SubgraphInterface currentPath,
                                                   int transversedEdges,
                                                   double angleSum,
                                                   LinkedHashSet<SubgraphInterface> faces,
                                                   int dimensions) throws Exception {

        if (Math.round(angleSum) > POLYGON_ANGLES_SUM) {
            return faces;
        }

        else if (currentNode.isEqual(required) && currentPath.getEdges().size() > 0) {
            Edge e2 = (Edge) currentPath.getEdges().toArray()[0];
            Edge e1 = (Edge) currentPath.getEdges().toArray()[currentPath.getEdges().size() - 1];
            angleSum += EdgeUtil.computeAngle(e1, e2);

            if (Math.round(angleSum) == POLYGON_ANGLES_SUM) {
                faces.add(currentPath);
            }
            return faces;
        }
        else if (currentNode.isEqual(required)) {
            currentPath.addVertex(currentNode);
        }

        return transverseNode(currentNode, required, currentPath, angleSum, transversedEdges, faces, dimensions);
    }

    /*
     * transverse the current node and recursivly call the dfs search.
     */
    private LinkedHashSet<SubgraphInterface> transverseNode(VertexInterface currentNode,
                                                            VertexInterface required,
                                                            SubgraphInterface currentPath,
                                                            double angleSum, int transversedEdges,
                                                            LinkedHashSet<SubgraphInterface> faces,
                                                            int dimensions)
            throws Exception  {
        for (int vid : GraphUtils.getAdjacentVertices(currentNode.getId(), adjacencyMatrix)) {
            VertexInterface v = vertices.get(vid);
            double newAngleSum = angleSum;
            Edge e1 = findEdge(currentNode, v);
            SubgraphInterface newCurrentPath = currentPath.clone();

            if ((e1.getJoiningAxis().getBitMask() & dimensions) != e1.getJoiningAxis().getBitMask()) {
                if (dimCount(dimensions) <= 2) {
                    dimensions |= e1.getJoiningAxis().getBitMask();
                }
            }
            else {
                return faces;
            }

            if (GraphUtils.isMarked(e1.getId(), transversedEdges)) {
                continue;
            }

            if (!currentNode.isEqual(required)) {

                VertexInterface lastVertex = (VertexInterface) currentPath.getVertices().toArray()[currentPath.getVertices().size() - 1];
                if (lastVertex.getId() == vid) {
                    continue;
                }
            }

            int newTansversedEdges = GraphUtils.markVertex(e1.getId(), transversedEdges);

            if (currentPath.getEdges().size() >= 1) {

                Edge e2 = (Edge) currentPath.getEdges().toArray()[currentPath.getEdges().size() - 1];
                newAngleSum += EdgeUtil.computeAngle(e1, e2);
            }

            newCurrentPath.addEdge(e1);
            newCurrentPath.addVertex(vertices.get(vid));
            faces = dfs(vertices.get(vid),
                    required,
                    newCurrentPath,
                    newTansversedEdges,
                    newAngleSum,
                    faces,
                    dimensions);
        }

        return faces;
    }

    private Edge findEdge(VertexInterface v1, VertexInterface v2) {
        for (Edge e : edges) {
            if (e.containsNodes(v1, v2)) {
                return e;
            }
        }
        return null;
    }

    private static int dimCount(int dimensions) {
        // Java: use >>> instead of >>
        // C or C++: use uint32_t
        dimensions = dimensions - ((dimensions >>> 1) & 0x55555555);
        dimensions = (dimensions & 0x33333333) + ((dimensions >>> 2) & 0x33333333);
        return (((dimensions + (dimensions >>> 4)) & 0x0F0F0F0F) * 0x01010101) >>> 24;
    }

}
