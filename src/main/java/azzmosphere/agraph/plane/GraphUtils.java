package azzmosphere.agraph.plane;

import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;

/**
 * General utility functions that are useful to several classes.
 *
 * Created by aaron.spiteri on 16/05/2016.
 */
public class GraphUtils {

    /**
     * Test if v1 has a direct connection to v2.
     *
     * @param v1
     * @param v2
     * @return
     */
    public static boolean isAdjacent(VertexInterface v1, VertexInterface v2, ArrayList<Integer> adjacencyMatrix) {
        return isAdjacent(v1.getId(), v2.getId(), adjacencyMatrix);
    }

    /**
     * Test if v1.getId() has a direct connect to v2.getId()
     *
     * @param v1
     * @param v2
     * @return
     */
    public static boolean isAdjacent(int v1, int v2, ArrayList<Integer> adjacencyMatrix) {
        return (adjacencyMatrix.get(v1) & 0x1 << v2) != 0;
    }

    /**
     * Given  a vertex array mark member at poistion id as true.  The array is a single word (as in a integer) that will
     * have the bit value at position id set to '1'.
     *
     * @param id position of vertex
     * @param array current vertices
     * @return array vertices after id has been marked as true.
     */
    public static int markVertex(int id, int array) {
        array = 0x1 << id | array;
        return array;
    }

    public static boolean isMarked(int id, int adjacentVertices) {
        return (adjacentVertices & 0x1 << id) != 0;
    }
}
