package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.Edge;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperInterface;
import azzmosphere.agraph.plane.GraphUtils;
import azzmosphere.agraph.vertices.VertexInterface;
import java.util.LinkedHashSet;

import java.util.ArrayList;
import org.apache.commons.lang.mutable.MutableInt;

/**
 * Transverse graph using DFS on a regular polydedron graphed object.
 *
 * Created by aaron.spiteri on 18/05/2016.
 */
public class RegularPolyhedronDFS implements TranverserInterface {
    private ArrayList<VertexInterface> vertices;
    private ArrayList<Integer> adjacencyMatrix;
    private ArrayList<Edge> edges;
    private SubgraphMapperInterface mapper;

    private final int MAX_PATH_SIZE = 10000;

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

    public RegularPolyhedronDFS(SubgraphMapperInterface mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean isBalanced() {
        return GraphUtils.isPolyhedron(vertices.size(), edges.size(), findAllSubgraphs().size());
    }

    /**
     * return the next unfound face on a planner graph for vertex v.
     *
     * @param v the vertex that is being searched for.
     * @return Face (subgraph of path to v).
     */
    @Override
    public LinkedHashSet<SubgraphInterface> findAllSubgraphs(VertexInterface v) {
        MutableInt shortestPathSize = new MutableInt(MAX_PATH_SIZE);
        LinkedHashSet<SubgraphInterface> faces = new LinkedHashSet<>();
        LinkedHashSet<LinkedHashSet<Edge>> foundEdges = new LinkedHashSet<>();
        LinkedHashSet<LinkedHashSet<Integer>> paths = transversePaths(
                v.getId(),
                v.getId(),
                shortestPathSize,
                new LinkedHashSet<>(),
                new LinkedHashSet<>(),
                new LinkedHashSet<>(),
                foundEdges,
                0);

        for (int i = 0; i < paths.size(); i++) {
            LinkedHashSet<Integer>  components = (LinkedHashSet<Integer>) paths.toArray()[i];
            if (components.size() == shortestPathSize.toInteger()) {
                faces.add(buildFace(components, (LinkedHashSet<Edge>) foundEdges.toArray()[i]));
            }
        }

        return faces;
    }

    @Override
    public LinkedHashSet<SubgraphInterface> findAllSubgraphs() {
        int discoveredVertices = 0;
        boolean allVerticesDiscovered = false;
        LinkedHashSet<SubgraphInterface> faces = new LinkedHashSet<>();

        while (!allVerticesDiscovered) {
            allVerticesDiscovered = true;
            for (VertexInterface v : vertices) {
                if (!GraphUtils.isMarked(v.getId(), discoveredVertices)) {
                    LinkedHashSet<SubgraphInterface> newFaces = findAllSubgraphs(v);
                    allVerticesDiscovered = false;

                    for (SubgraphInterface f : newFaces) {
                        for (VertexInterface discoveredNode : f.getVertices()) {
                            discoveredVertices = GraphUtils.markVertex(discoveredNode.getId(), discoveredVertices);
                        }
                    }

                    faces.addAll(newFaces);
                }
            }
        }

        return faces;
    }


    /*
     * Transverse all paths recursivley clockwise (left to right) starting at currentNodeId, return when either
     * required is located or there are no other paths to transverse.
     */
    //CHECKSOFF: CyclomaticComplexity
    private LinkedHashSet<LinkedHashSet<Integer>> transversePaths(int currentNodeId,
                                                                  int required,
                                                                  MutableInt currentShortestPath,
                                                                  LinkedHashSet<Integer> path,
                                                                  LinkedHashSet<LinkedHashSet<Integer>> paths,
                                                                  LinkedHashSet<Edge> edges,
                                                                  LinkedHashSet<LinkedHashSet<Edge>> foundEdges,
                                                                  int discoveredEdges) {
        if (path.size() > currentShortestPath.intValue()) {
            return paths;
        }
        if (path.size() > 0 && currentNodeId == required) {
            paths.add(path);
            foundEdges.add(edges);
            if (path.size() < currentShortestPath.toInteger()) {
                currentShortestPath.setValue(path.size());
            }
            return paths;
        }

        for (int nodeId : getAdjacentVertices(currentNodeId)) {
            Edge e = findEdge(vertices.get(currentNodeId), vertices.get(nodeId));
            if (!GraphUtils.isMarked(e.getId(), discoveredEdges)) {
                LinkedHashSet<Integer> newPath = (LinkedHashSet) path.clone();
                LinkedHashSet<Edge> newEdges = (LinkedHashSet<Edge>) edges.clone();
                newPath.add(nodeId);

                discoveredEdges = GraphUtils.markVertex(e.getId(), discoveredEdges);
                newEdges.add(e);
                paths = transversePaths(nodeId, required, currentShortestPath, newPath, paths, newEdges, foundEdges, discoveredEdges);
            }
        }

        return paths;
    }
    //CHECKSON: CyclomaticComplexity

    /*
     * Builds a face based on a given path.
     */
    private SubgraphInterface buildFace(LinkedHashSet<Integer> path, LinkedHashSet<Edge> foundEdgeList) {
        SubgraphInterface face = mapper.getSubGraphObject(getClass().getCanonicalName());

        for (int nodeId : path) {
            face.addVertex(vertices.get(nodeId));
        }

        for (Edge e : foundEdgeList) {
            face.addEdge(e);
        }

        return face;
    }

    /*
     * Find the edge that connects vertice v1 and v2
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
    * Return the adjacent vertices for node id.
    */
    private ArrayList<Integer> getAdjacentVertices(int id) {
        ArrayList<Integer> a = new ArrayList<>();

        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            if (GraphUtils.isAdjacent(id, i, adjacencyMatrix)) {
                a.add(i);
            }
        }
        return a;
    }
}
