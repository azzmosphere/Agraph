package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.Edge;
import azzmosphere.agraph.face.FaceInterface;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;

/**
 *
 * Interface to describe behaviour for finding subgraphs.
 *
 * Created by aaron.spiteri on 16/05/2016.
 */
public interface TranverserInterface {
    /**
     * find all faces for that are connected with vertex matcher.
     * @param matcher the vertex that starts and ends the subgraph
     * @param vertices all vertices in the graph
     * @param adjacencyMatrix adjacency matrix
     * @return faces that are connected with the matcher.
     */
    ArrayList<FaceInterface> findFacesForVertex(VertexInterface matcher, ArrayList<VertexInterface> vertices, ArrayList<Integer> adjacencyMatrix);

    /**
     * Find all subgraphs in the graph.  Graph is assumed to be a perfect graph.
     *
     * @param  vertices all vertices in the graph
     * @param adjacencyMatrix the adjacency matrix
     * @return all faces within the graph.
     */
    ArrayList<FaceInterface> findAllFaces(ArrayList<VertexInterface> vertices, ArrayList<Integer> adjacencyMatrix);
}
