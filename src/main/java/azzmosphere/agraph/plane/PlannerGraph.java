package azzmosphere.agraph.plane;

import java.util.ArrayList;
import azzmosphere.agraph.vertices.Vertex;
import azzmosphere.agraph.VerticesFactory;
import azzmosphere.agraph.Edge;
import azzmosphere.agraph.EdgeFactory;

/**
 * Created by aaron.spiteri on 12/05/2016.
 *
 * In graph theory, a planar graph is a graph that can be embedded in the plane, i.e.,
 * it can be drawn on the plane in such a way that its edges intersect only at their
 * endpoints. In other words, it can be drawn in such a way that no edges cross each other.[1]
 * Such a drawing is called a plane graph or planar embedding of the graph. A plane graph
 * can be defined as a planar graph with a mapping from every node to a point on a plane,
 * and from every edge to a plane curve on that plane, such that the extreme points of each
 * curve are the points mapped from its end nodes, and all curves are disjoint except on
 * their extreme points.
 *
 * Every graph that can be drawn on a plane can be drawn on the sphere as well, and vice versa.
 *
 * Plane graphs can be encoded by combinatorial maps.
 *
 * The equivalence class of topologically equivalent drawings on the sphere is called a planar map.
 * Although a plane graph has an external or unbounded face, none of the faces of a planar map have
 * a particular status.
 *
 * A generalization of planar graphs are graphs which can be drawn on a surface of a given genus.
 * In this terminology, planar graphs have graph genus 0, since the plane (and the sphere) are surfaces
 * of genus 0. See "graph embedding" for other related topics.
 *
 * (2016, https://en.wikipedia.org/wiki/Planar_graph)
 *
 *
 *
 */
public class PlannerGraph {

    /*
     * In graph theory, computer science and application areas such as sociology, an adjacency matrix is a square matrix
     * used to represent a finite graph. The elements of the matrix indicate whether pairs of vertices are adjacent or
     * not in the graph.
     *
     * In the special case of a finite simple graph, the adjacency matrix is a (0,1)-matrix with zeros on its diagonal.
     * If the graph is undirected, the adjacency matrix is symmetric. The relationship between a graph and the
     * eigenvalues and eigenvectors of its adjacency matrix is studied in spectral graph theory.
     *
     * The adjacency matrix should be distinguished from the incidence matrix for a graph, a different matrix
     * representation whose elements indicate whether vertex–edge pairs are incident or not.
     *
     * https://en.wikipedia.org/wiki/Adjacency_matrix
     */

    /**
     * To conserve space the matrix is stored as Long that has the bit's set. This also greatly simplifies the
     * insertion operations done by the Edge class.
     */
    private ArrayList<Integer> adjacencyMatrix = new ArrayList<>();
    private ArrayList<Vertex> vertices = new ArrayList<>();


    /*
     * A planner graph should comply Euler rule which states:
     *
     * v + f - e = 2
     *
     * http://www.mathpuzzle.com/wge123.html
     */

    /**
     * Verify and rebuild the graph to meet planner requirements.
     */
    void verifyAndRebuild() {

    }

    /**
     * Retrieves the matrix with booleans set to true or false adjecent vertices.
     *
     * This implementation is only required for JAVA in lower level languages such C++ or object C this can be done
     * implicitly because a boolean long, etc are equivalent.
     *
     * @return
     */
    public ArrayList<ArrayList<Boolean>> getAdjacencyMatrix() {

        ArrayList<ArrayList<Boolean>> bitsMatrix = new ArrayList<>();

        /* create slots */
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            bitsMatrix.add(new ArrayList<>());
            for (int y = 0; y < adjacencyMatrix.size(); y++) {
                bitsMatrix.get(i).add(false);
            }
        }

        /* convert to booleans */
        for (int y = adjacencyMatrix.size() - 1; y >= 0; y--) {
            for (int i = adjacencyMatrix.size() - 1; i >= 0; i--) {
                bitsMatrix.get(y).set(i, isAdjacent(y, i));
            }
        }
        return bitsMatrix;
    }

    /**
     * Test if v1 has a direct connection to v2.
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean isAdjacent(Vertex v1, Vertex v2) {
        return isAdjacent(v1.getId(), v2.getId());
    }

    /**
     * Test if v1.getId() has a direct connect to v2.getId()
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean isAdjacent(int v1, int v2) {
        return (adjacencyMatrix.get(v1) & 0x1 << v2) != 0;
    }


    /**
     * attaches vertex to graph. The vertex remains unattached to any other vertices until a edge is created. It is
     * therefore better to call this method should not be callled directly for planner graphs and is only included
     * to meet interface requirements for other graph types that allow for unattached node.
     *
     * @param data
     * @param x
     * @param y
     * @return
     * @throws Exception
     */
    public Vertex attachVertex(Object data, int x, int y) throws Exception {
        Vertex v = VerticesFactory.createVertex(vertices.size(), data, x, y);
        vertices.add(v);
        return v;
    }

    public Edge createEdge(Vertex v1, Vertex v2) throws  Exception {
        Edge e = EdgeFactory.createEdge(v1, v2);
        e.adjacentNodes(adjacencyMatrix);

        return e;
    }


}
