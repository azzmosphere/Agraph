import azzmosphere.agraph.edge.EdgeUtil;
import azzmosphere.agraph.subgraph.SubgraphMapperImp;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.vertices.VerticesFactory;
import azzmosphere.agraph.tranverser.PolyhedronDFS;

import datastructures.GenericVertex3DStructure;
import org.junit.Before;
import org.junit.Test;
import vertices.TestClassForVertex2D;
import vertices.TestClassVerticesMapperImpl;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.CoreMatchers.is;

/**
 * Checks the functions of Edge.
 *
 * Created by aaron.spiteri on 17/05/2016.
 */
public class TestEdge {
    private PlannerGraph pg;
    private VertexInterface v1, v2, v3, v4;
    Edge e1, e2;
    private VerticesFactory vf = new VerticesFactory(new TestClassVerticesMapperImpl());

    @Before
    public void initilise() throws Exception {
        pg = new PlannerGraph(new PolyhedronDFS(new SubgraphMapperImp()));

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

    @Test
    public void shouldFindAngle() throws Exception {
        GenericVertex3DStructure data = new GenericVertex3DStructure();

        VertexInterface v1 = vf.createVertex(data, new int[]{1, 1, 1});
        VertexInterface v2 = vf.createVertex(data, new int[]{4, 1, 1});
        VertexInterface v3 = vf.createVertex(data, new int[]{4, 4, 1});
        VertexInterface v4 = vf.createVertex(data, new int[]{1, 4, 1});

        v1.setId(0);
        v2.setId(1);
        v3.setId(2);
        v4.setId(3);

        v1.setLabel("v1");
        v2.setLabel("v2");
        v3.setLabel("v3");
        v4.setLabel("v4");

        Edge e1 = pg.createEdge(v1, v2, "e1", Edge.Axis.XAXIS);
        Edge e2 = pg.createEdge(v2, v3, "e2", Edge.Axis.YAXIS);
        Edge e3 = pg.createEdge(v3, v4, "e3", Edge.Axis.XAXIS);
        Edge e4 = pg.createEdge(v4, v1, "e4", Edge.Axis.YAXIS);

        double angle = EdgeUtil.computeAngle(e1, e2);
        angle += EdgeUtil.computeAngle(e2, e3);
        angle += EdgeUtil.computeAngle(e3, e4);
        angle += EdgeUtil.computeAngle(e4, e1);

        assertThat((int) Math.abs(angle), is(180));
    }

}
