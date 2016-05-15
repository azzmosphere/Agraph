package vertices;

import azzmosphere.agraph.vertices.Vertex2D;
import datastructures.GenericVertex2DStructure;

/**
 * Node representation of UltraSonicSensorVertex.
 *
 * Created by aaron.spiteri on 11/05/2016.
 */
public class TestClassForVertex2D extends Vertex2D<GenericVertex2DStructure> {
    private GenericVertex2DStructure data;

    @Override
    public GenericVertex2DStructure getData() {
        return data;
    }

    @Override
    public void setData(GenericVertex2DStructure data) {
        this.data = data;
    }

}
