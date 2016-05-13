package azzmosphere.agraph;

import azzmosphere.agraph.vertices.Vertex;
import java.util.ArrayList;

/**
 * Connecting point of two vertices.
 *
 * Created by aaron.spiteri on 10/05/2016.
 */
public class Edge {
    private Vertex tail;
    private Vertex head;
    private String label;

    public Vertex getTail() {
        return tail;
    }

    public void setTail(Vertex tail) {
        this.tail = tail;
    }

    public Vertex getHead() {
        return head;
    }

    public void setHead(Vertex head) {
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
     * @param adjacentNodeMatrix
     * @return adjaventNodeMatrix
     */
    public ArrayList<ArrayList<Boolean>> adjacentNodes(ArrayList<ArrayList<Boolean>> adjacentNodeMatrix, Vertex v) {

        int id = v.getId();
        ArrayList<Edge> edges = v.getEdges();
        createSlots(id, adjacentNodeMatrix);

        for (Edge e : edges) {
            int eId = e.getHead().getId();
            createSlots(eId, adjacentNodeMatrix);

            adjacentNodeMatrix.get(id).set(eId, true);
            adjacentNodeMatrix.get(eId).set(id, true);
        }
        return adjacentNodeMatrix;
    }

    /*
     * create a slot for all elements up to "id" and create inner slots so that it formulates
     * a proper matrix.
     */
    private void createSlots(int id, ArrayList<ArrayList<Boolean>> adjacentNodeMatrix) {
        if (adjacentNodeMatrix.size() <= id) {
            for (int n = adjacentNodeMatrix.size(); n < (id + 1); n++) {
                ArrayList<Boolean> a = new ArrayList<>();
                adjacentNodeMatrix.add(a);
                adjacentNodeMatrix.get(id).add(false);

                for (int i = id; i >= 0; i--) {
                    a = adjacentNodeMatrix.get(i);
                    createInnerSlots(id, a);
                }
            }
        }
    }

    private void createInnerSlots(int id, ArrayList<Boolean> adjacentNodes) {
        if (adjacentNodes.size() <= id) {
            for (int n = adjacentNodes.size(); n < (id + 1); n++) {
                adjacentNodes.add(false);
            }
        }
    }
}
