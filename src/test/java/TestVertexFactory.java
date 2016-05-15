import azzmosphere.agraph.VerticesFactory;


import azzmosphere.agraph.vertices.VertexInterface;
import datastructures.GenericVertex2DStructure;
import org.junit.Test;
import vertices.TestClassForVertex2D;
import vertices.TestClassVerticesMapperImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by aaron.spiteri on 11/05/2016.
 */
public class TestVertexFactory {
    @Test
    public void shouldCreateGenericVertex2D() throws Exception {
        GenericVertex2DStructure s = new GenericVertex2DStructure();
        VerticesFactory vf = new VerticesFactory(new TestClassVerticesMapperImpl());
        VertexInterface v = vf.createVertex(0, s, new int[]{1, 2}, "test");

        assertThat(v.getClass().getCanonicalName(), is(TestClassForVertex2D.class.getCanonicalName()));
    }

}
