package azzmosphere.agraph.edge;

import azzmosphere.agraph.plane.GraphUtils;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;

/**
 * Created by aaron.spiteri on 30/05/2016.
 */
public abstract class EdgeAbstractBase implements Edge {
    private VertexInterface tail;
    private VertexInterface head;
    private String label;
    private int id;
    protected double length;
    protected Axis joiningAxis;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public VertexInterface getTail() {
        return tail;
    }

    @Override
    public void setTail(VertexInterface tail) {
        this.tail = tail;
    }

    @Override
    public VertexInterface getHead() {
        return head;
    }

    @Override
    public void setHead(VertexInterface head) {
        this.head = head;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Updates the adjacent node matrix with nodes adjacent to this vertex.
     *
     * @param adjacentNodeMatrix Matrix before modification
     * @return adjacentNodes matrix after modification.
     */
    @Override
    public ArrayList<Integer> adjacentNodes(ArrayList<Integer> adjacentNodeMatrix) {

        VertexInterface v = getTail();
        int id = v.getId();
        ArrayList<Edge> edges = v.getEdges();
        createSlots(id, adjacentNodeMatrix);

        for (Edge e : edges) {
            int eId = e.getHead().getId();

            int innerSlots = adjacentNodeMatrix.get(id);
            innerSlots = GraphUtils.markVertex(eId, innerSlots);
            adjacentNodeMatrix.set(id, innerSlots);

            createSlots(eId, adjacentNodeMatrix);
            innerSlots = adjacentNodeMatrix.get(eId);
            innerSlots = GraphUtils.markVertex(id, innerSlots);
            adjacentNodeMatrix.set(eId, innerSlots);
        }
        return adjacentNodeMatrix;
    }

    /*
     * create a slot for all elements up to "id" and create inner slots so that it formulates
    * a proper matrix.
    */
    private void createSlots(int id, ArrayList<Integer> adjacentNodeMatrix) {
        if (adjacentNodeMatrix.size() <= id) {
            for (int n = adjacentNodeMatrix.size(); n < (id + 1); n++) {
                adjacentNodeMatrix.add(0);
            }
        }
    }

    @Override
    public boolean containsNodes(VertexInterface v1, VertexInterface v2) {

        if (getHead().isEqual(v1) && getTail().isEqual(v2)) {
            return true;
        }
        if (getTail().isEqual(v1) && getHead().isEqual(v2)) {
            return true;
        }
        return false;
    }

    /**
     * The length of the edge in units.
     *
     * @return length of edge.
     */
    @Override
    public double getLength() {
        return length;
    }

    /**
     * Which axis that the vetices head and tail are joined on.
     *
     * @return the axis that v1 and v2 are joined on.
     */
    @Override
    public Axis getJoiningAxis() {
        return joiningAxis;
    }

    @Override
    public int compareTo(Edge o) {

        if (containsNodes(o.getHead(), o.getTail())) {
            return 0;
        }
        else if (getHead().getId() != o.getHead().getId()) {
            return getHead().getId() - o.getHead().getId();
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        Edge3d compareTo = (Edge3d) o;
        if (compareTo(compareTo) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return getLabel() + "(" + getHead() + ", " + getTail() + ")";
    }

}
