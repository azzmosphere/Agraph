package azzmosphere.agraph.edge;

import azzmosphere.agraph.Coordinate;
import azzmosphere.agraph.plane.GraphUtils;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;

/**
 * Connecting point of two vertices.
 *
 * Created by aaron.spiteri on 10/05/2016.
 */
public class Edge implements Comparable<Edge> {

    private interface BitMask {
        int getBitMask();
    }

    public enum Axis implements BitMask {
        XAXIS{
            @Override
            public int getBitMask() {
                return 0x000000001;
            }
        },
        YAXIS{
            @Override
            public int getBitMask() {
                return 0x000000010;
            }
        },
        ZAXIS{
            @Override
            public int getBitMask() {
                return 0x000000100;
            }
        };
    }

    private VertexInterface tail;
    private VertexInterface head;
    private String label;
    private int id;
    private double length;
    private Axis joiningAxis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VertexInterface getTail() {
        return tail;
    }

    public void setTail(VertexInterface tail) {
        this.tail = tail;
    }

    public VertexInterface getHead() {
        return head;
    }

    public void setHead(VertexInterface head) {
        this.head = head;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Updates the adjacent node matrix with nodes adjacent to this vertex.
     *
     * @param adjacentNodeMatrix Matrix before modification
     * @return adjacentNodes matrix after modification.
     */
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
    public double getLength() {
        return length;
    }

    /**
     * Which axis that the vetices head and tail are joined on.
     *
     * @return the axis that v1 and v2 are joined on.
     */
    public Axis getJoiningAxis() {
        return joiningAxis;
    }

    /**
     * set the axis that v1 and v2 are to be joined on,
     * this will also calculate the length of the edge.
     *
     * @param joiningAxis
     */
    public void setJoiningAxis(Axis joiningAxis) {
        this.joiningAxis = joiningAxis;
        Coordinate[] coordinatesV1 = getHead().getCoordinates();
        Coordinate[] coordinatesV2 = getTail().getCoordinates();


        double[] coordinates = new double[3];
        for (int i = 0; i < 2; i ++) {
            coordinates[i] = coordinatesV1[i].getCoordinate() - coordinatesV2[i].getCoordinate();

            if (coordinates[i] < 0) {
                coordinates[i] = coordinates[i] * -1;
            }
        }

        this.length = EdgeUtil.computeEdgeSize(coordinates[0], coordinates[1], coordinates[2]);
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
        Edge compareTo = (Edge) o;
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
