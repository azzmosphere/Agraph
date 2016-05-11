import azzmosphere.agraph.Vertex;
import azzmosphere.agraph.VerticesFactory;

import javax.sound.sampled.AudioInputStream;

import azzmosphere.agraph.datastructures.UltraSonicSensor;
import azzmosphere.agraph.vertices.UltraSonicSensorVertex;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.mock;
/**
 * Created by aaron.spiteri on 11/05/2016.
 */
public class TestVertexFactory {
    @Test
    public void shouldCreateUltraSonicSensorVertex() throws Exception {
        UltraSonicSensor s = new UltraSonicSensor();
        Vertex v = VerticesFactory.createVertex(0, s, 1, 2, "test");

        assertThat(v.getClass().getCanonicalName(), is(UltraSonicSensorVertex.class.getCanonicalName()));
    }

}
