package azzmosphere.agraph;

import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;

/**
 * Connecting point of two vertices.
 *
 * Created by aaron.spiteri on 10/05/2016.
 */
public class Edge {
    private VertexInterface tail;
    private VertexInterface head;
    private String label;

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
            innerSlots = 0x1 << eId | innerSlots;
            adjacentNodeMatrix.set(id, innerSlots);

            createSlots(eId, adjacentNodeMatrix);
            innerSlots = adjacentNodeMatrix.get(eId);
            innerSlots = 0x1 << id | innerSlots;
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
}
