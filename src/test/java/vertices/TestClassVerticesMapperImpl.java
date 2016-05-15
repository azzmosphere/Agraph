package vertices;

import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.vertices.VerticesMapperInterface;
import datastructures.GenericVertex2DStructure;
import datastructures.GenericVertex3DStructure;

/**
 * Created by aaron.spiteri on 15/05/2016.
 */
public class TestClassVerticesMapperImpl implements VerticesMapperInterface {
    public enum VerticesMapper implements VerticesMapperInterface {

        /* default object */
        TEST_VERTEX_2D_STRUCT {
            @Override
            public String toString() {
                return GenericVertex2DStructure.class.getCanonicalName();
            }

            @Override
            public VertexInterface getVertex(Object data) {
                TestClassForVertex2D v = new TestClassForVertex2D();
                v.setData((GenericVertex2DStructure) data);
                return v;
            }
        },

        TEST_VERTEX_3D_STRUCT {
            @Override
            public String toString() {
                return GenericVertex3DStructure.class.getCanonicalName();
            }

            @Override
            public VertexInterface getVertex(Object data) {
                TestClassForVertex3D v = new TestClassForVertex3D();
                v.setData((GenericVertex3DStructure) data);
                return v;
            }
        };

    }

    private VerticesMapper map;

    @Override
    public VertexInterface getVertex(Object o) {
        VertexInterface v = null;
        for (VerticesMapper m : VerticesMapper.values()) {
            if (m.toString().equals(o.getClass().getCanonicalName())) {
                v = m.getVertex(o);
            }
        }
        return v;
    }
}
