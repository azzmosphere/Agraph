package vertices;

import azzmosphere.agraph.vertices.Vertex2D;
import datastructures.GenericVertex3DStructure;

/**
 * Created by aaron.spiteri on 11/05/2016.
 */
public class TestClassForVertex3D extends Vertex2D<GenericVertex3DStructure> {
    private GenericVertex3DStructure data;

    @Override
    public void setData(GenericVertex3DStructure data) {
        this.data = data;
    }


    @Override
    public GenericVertex3DStructure getData() {
        return data;
    }
}
