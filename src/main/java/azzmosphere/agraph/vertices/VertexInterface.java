package azzmosphere.agraph.vertices;


import azzmosphere.agraph.Coordinate;
import azzmosphere.agraph.edge.Edge;

import java.util.ArrayList;

/**
 * Interface that all vertices must abide by.
 *
 * T : Represents the consuming class
 * Y : Represents the data object that vertex T represents.
 *
 * Created by aaron.spiteri on 10/05/2016.
 */
public interface VertexInterface<T, Y> extends Comparable<T> {
    ArrayList<VertexInterface> adjacentNodes();
    ArrayList<Edge> getEdges();

    /**
     * set the human name that identifies the vertex
     * @return
     */
    String getLabel();

    /**
     * human name that identifies the vertex.
     *
     * @param label
     */
    void setLabel(String label);

    /**
     * id that is allocated by the VerticesFactory. Represents the position on the adjacent
     * nodes matrix.
     *
     * @param id
     */
    void setId(int id);

    /**
     * Retrieves the allocated slot in the adjacent nodes matrix.
     *
     * @return id
     */
    int getId();

    /**
     * if objects are equal returns true otherwise false.
     *
     * @param object
     * @return isEqual
     */
    boolean isEqual(T object);


    /**
     * data that is stored on node.
     *
     * @return data object
     */
    Y getData();

    /**
     * data to store on node.
     *
     * @param data
     */
    void setData(Y data);

    /**
     * Sets the coordinates to use.
     *
     * @param coordinates
     */
    void setCoordinates(Coordinate[] coordinates);

    Coordinate[] getCoordinates();
}
