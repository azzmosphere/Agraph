package azzmosphere.agraph.vertices;

import azzmosphere.agraph.edge.Edge;

import java.util.ArrayList;

/**
 * Base class to implement the vertex.
 *
 * Created by aaron.spiteri on 10/05/2016.
 */
public abstract class Vertex<Y> implements VertexInterface<Vertex, Y> {
    private ArrayList<VertexInterface> adjacentNodes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private String label;
    private int id;

    @Override
    public ArrayList<VertexInterface> adjacentNodes() {
        return adjacentNodes;
    }

    @Override
    public ArrayList<Edge> getEdges() {
        return edges;
    }


    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Vertex vertex) {
        if (id == 0 && vertex.getId() != 0) {
            return vertex.getId() * -1;
        }
        return id - vertex.getId();
    }

    @Override
    public boolean isEqual(Vertex v) {
        return compareTo(v) == 0;
    }



}
