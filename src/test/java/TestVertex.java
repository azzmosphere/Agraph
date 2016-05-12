/**
 * Created by aaron.spiteri on 10/05/2016.
 *
 * Test vertex.
 */

import azzmosphere.agraph.Coordinate;
import azzmosphere.agraph.vertices.Vertex;
import org.junit.Test;
import java.util.TreeSet;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

public class TestVertex {
    class TestVertexImp extends Vertex<Object> {
        TestVertexImp(int id, String label, int x, int y) {
            setId(id);
            setLabel(label);
            setX(new Coordinate(x));
            setY(new Coordinate(y));
        }

        @Override
        public Object getData() {
            return null;
        }

        @Override
        public void setData(Object data) {

        }

        @Override
        public String getDataClass() {
            return null;
        }
    }

    @Test
    public void shouldAssignVertex() {
        TestVertexImp testVertexImp = new TestVertexImp(0, "v1", 1, 2);
        assertThat(testVertexImp.getLabel(), is("v1"));
        assertThat(testVertexImp.getX().getCoordinate(), is(1));
        assertThat(testVertexImp.getY().getCoordinate(), is(2));
    }

    @Test
    public void shouldSortCorrectly() {
        TestVertexImp v1 = new TestVertexImp(0, "v1", 1, 3);
        TestVertexImp v2 = new TestVertexImp(2, "v2", 1, 2);
        TestVertexImp v3 = new TestVertexImp(3, "v3", 2, 2);
        TestVertexImp v4 = new TestVertexImp(1, "v4", 3, 2);
        TestVertexImp v5 = new TestVertexImp(5, "v5", 0, 2);
        TestVertexImp v6 = new TestVertexImp(4, "v6", 0, 1);

        TreeSet<TestVertexImp> vertices = new TreeSet<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);

        assertThat(vertices.toArray()[0], is(v1));
        assertThat(vertices.toArray()[1], is(v4));
        assertThat(vertices.toArray()[2], is(v2));
        assertThat(vertices.toArray()[3], is(v3));
        assertThat(vertices.toArray()[4], is(v6));
        assertThat(vertices.toArray()[5], is(v5));

        assertTrue(v1.isEqual(v1));
    }
}
