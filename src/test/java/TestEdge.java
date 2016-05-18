import azzmosphere.agraph.tranverser.DFS;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.Edge;
import azzmosphere.agraph.plane.PlannerGraph;

import org.junit.Before;
import org.junit.Test;
import vertices.TestClassForVertex2D;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Checks the functions of Edge.
 *
 * Created by aaron.spiteri on 17/05/2016.
 */
public class TestEdge {
    private PlannerGraph pg;
    private VertexInterface v1, v2, v3, v4;
    Edge e1, e2;

    @Before
    public void initilise() throws Exception {
        pg = new PlannerGraph(new DFS());

        v1 = pg.attachVertex(new TestClassForVertex2D());
        v2 = pg.attachVertex(new TestClassForVertex2D());
        v3 = pg.attachVertex(new TestClassForVertex2D());
        v4 = pg.attachVertex(new TestClassForVertex2D());

        e1 = pg.createEdge(v1, v2);
        e2 = pg.createEdge(v2, v4);
    }


    @Test
    public void shouldReturnContainNodes() throws Exception {
        assertTrue(e1.containsNodes(v1, v2));
        assertFalse(e1.containsNodes(v1, v3));
    }

    @Test
    public void shouldFindEdge() throws Exception {
        assertFalse(e1.equals(e2));
        assertTrue(e1.equals(e1));
    }

}
