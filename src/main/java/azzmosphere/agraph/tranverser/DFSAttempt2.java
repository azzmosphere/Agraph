package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.Edge;
import azzmosphere.agraph.face.FaceInterface;
import azzmosphere.agraph.face.SimpleCycle;
import azzmosphere.agraph.plane.GraphUtils;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;

/**
 *
 * implementation of the DFS algorithm
 *
 * Created by aaron.spiteri on 17/05/2016.
 */
public class DFSAttempt2 {
    private ArrayList<VertexInterface> vertices;
    private ArrayList<Integer> adjacencyMatrix;
    private ArrayList<Edge> edges;

    public ArrayList<VertexInterface> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<VertexInterface> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Integer> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(ArrayList<Integer> adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    /**
     * return the next unfound face for vertice v.
     *
     * @param v
     * @return Face (subgraph of path to v).
     */
    public FaceInterface findFace(VertexInterface v) {
        ArrayList<Integer> adjacentNodes = getAdjacentVertices(v.getId());
        ArrayList<Integer> path = new ArrayList<>();
        path.add(v.getId());
        FaceInterface found = null;

        for (int id : adjacentNodes) {
            int foundEdges = 0;
            ArrayList<Integer> currentPath = (ArrayList<Integer>) path.clone();
            ArrayList<Edge> foundEdgesList = new ArrayList<>();
            currentPath.add(id);
            Edge e = findEdge(v, vertices.get(id));
            foundEdges = GraphUtils.markVertex(e.getId(), foundEdges);
            foundEdgesList.add(e);
            ArrayList<Integer> foundPath = transversePath(id, v.getId(), foundEdges, currentPath, foundEdgesList);

            if (foundPath != null) {
                found = buildFace(foundPath, foundEdgesList);
                break;
            }
        }


        return found;
    }


    private FaceInterface buildFace(ArrayList<Integer> path, ArrayList<Edge> foundEdgeList) {
        FaceInterface face = new SimpleCycle();

        for (int nodeId : path) {
            face.addVertex(vertices.get(nodeId));
        }

        for (Edge e : foundEdgeList) {
            face.addEdge(e);
        }

        return face;
    }

    /**
     * Transverse path to find the required node and return that path.
     *
     * @param currentNode node in current path
     * @param required  the node that is being searched for
     * @param foundEdges  edges that have been discovered.
     * @return path as a ordered array of integers.
     */
    private ArrayList<Integer> transversePath(int currentNode, int required, int foundEdges, ArrayList<Integer> path, ArrayList<Edge> foundEdgesList) {
        if (currentNode == required) {
            return path;
        }

        ArrayList<Integer> adjacentNodes = getAdjacentVertices(currentNode);
        for (int node : adjacentNodes) {
            Edge e = findEdge(vertices.get(node), vertices.get(currentNode));

            if (!GraphUtils.isMarked(e.getId(), foundEdges)) {
                ArrayList<Integer> currentPath = (ArrayList<Integer>) path.clone();
                currentPath.add(node);
                foundEdges = GraphUtils.markVertex(e.getId(), foundEdges);
                foundEdgesList.add(e);
                path = transversePath(node, required, foundEdges, currentPath, foundEdgesList);

                if (path != null) {
                    return path;
                }
            }
        }

        // Could not find the vertex
        return null;
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

        for (int i = 0;i < adjacencyMatrix.size(); i ++) {
            if (GraphUtils.isAdjacent(id, i, adjacencyMatrix)) {
                a.add(i);
            }
        }
        return a;
    }
}
