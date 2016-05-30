package azzmosphere.agraph.edge;

import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;

/**
 * Connecting point of two vertices.
 *
 * Created by aaron.spiteri on 10/05/2016.
 */
public interface Edge extends Comparable<Edge> {

    int getId();

    void setId(int id);

    VertexInterface getTail();

    void setTail(VertexInterface tail);

    VertexInterface getHead();

    void setHead(VertexInterface head);

    String getLabel();

    void setLabel(String label);

    /**
     * Updates the adjacent node matrix with nodes adjacent to this vertex.
     *
     * @param adjacentNodeMatrix Matrix before modification
     * @return adjacentNodes matrix after modification.
     */
    ArrayList<Integer> adjacentNodes(ArrayList<Integer> adjacentNodeMatrix);


    boolean containsNodes(VertexInterface v1, VertexInterface v2);

    /**
     * The length of the edge in units.
     *
     * @return length of edge.
     */
    double getLength();

    /**
     * Which axis that the vetices head and tail are joined on.
     *
     * @return the axis that v1 and v2 are joined on.
     */
    Axis getJoiningAxis();

    /**
     * set the axis that v1 and v2 are to be joined on,
     * this will also calculate the length of the edge.
     *
     * @param joiningAxis
     */
    void setJoiningAxis(Axis joiningAxis);
}
