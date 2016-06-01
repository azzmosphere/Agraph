import azzmosphere.agraph.edge.EdgeUtil;
import azzmosphere.agraph.tranverser.PolyhedronDFS;
import azzmosphere.agraph.tranverser.VerticeSearchDFSImp;
import azzmosphere.agraph.vertices.VerticesFactory;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperImp;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.edge.Axis;
import azzmosphere.agraph.edge.Edge;
import datastructures.GenericVertex3DStructure;
import decider.MockDeciderClass;
import org.junit.Before;
import org.junit.Test;
import vertices.TestClassVerticesMapperImpl;

import java.util.LinkedHashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 *
 * Find faces in a regular triangle. The diagram and vertices and edges are described below.
 *
 *  6          V2
 *  5         / |  \
 *  4  E3   /   |   \  E2
 *  3      /  / V4\   \
 *  2    / /         \  \
 *  1  V1 --------------V3
 *     1   2   3   4  5  6
 *            E1
 *
 *   V1(1,1,1)
 *   V2(3,6,3)
 *   V3(6,1,1)
 *   V4(3,1,6)
 *
 *   E1(V1,V3)
 *   E2(V3,V2)
 *   E3(V1,V2)
 *   E4(V1,V4)
 *   E5(V3,V4)
 *   E6(V4,V2)
 *
 *
 *  The following formula should hold true:
 *
 *  V - E + F = 2
 *
 *  4 - 6 + F = 2
 *
 * Created by aaron.spiteri on 20/05/2016.
 */
public class TestDFSRegularTriangle {
    private VertexInterface v1;
    private VertexInterface v2;
    private VertexInterface v3;
    private VertexInterface v4;

    private Edge e1;
    private Edge e2;
    private Edge e3;
    private Edge e4;
    private Edge e5;
    private Edge e6;

    private PlannerGraph pg;
    private GenericVertex3DStructure data = new GenericVertex3DStructure();
    private VerticesFactory vf = new VerticesFactory(new TestClassVerticesMapperImpl());

    @Before
    public void initilise() throws Exception {
        pg = new PlannerGraph(new PolyhedronDFS(new SubgraphMapperImp()),
                new VerticeSearchDFSImp(new MockDeciderClass()));
        v1 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 1}), "v1"); // V1(1,1,1)
        v2 = pg.attachVertex(vf.createVertex(data, new int[]{3, 6, 3}), "v2"); // V2(3,6,3)
        v3 = pg.attachVertex(vf.createVertex(data, new int[]{6, 1, 1}), "v3"); // V3(6,1,1)
        v4 = pg.attachVertex(vf.createVertex(data, new int[]{1, 3, 6}), "v4"); // V4(3,1,6)

        e1 = pg.createEdge(v1, v3, "e1", Axis.XAXIS); // E1(V1,V3)
        e2 = pg.createEdge(v3, v2, "e2", Axis.YAXIS); // E2(V3,V2)
        e3 = pg.createEdge(v2, v1, "e3", Axis.YAXIS); // E3(V1,V2)
        e4 = pg.createEdge(v1, v4, "e4", Axis.ZAXIS); // E4(V1,V4)
        e5 = pg.createEdge(v3, v4, "e5", Axis.XAXIS); // E5(V3,V4)
        e6 = pg.createEdge(v4, v2, "e6", Axis.ZAXIS); // E6(V4,V2)
    }

    @Test
    public void shouldSetGetProperties() throws Exception {
        assertThat(pg.getAdjacencyMatrixSimple(), is(pg.getTransverser().getAdjacencyMatrix()));
        assertThat(pg.getEdges(), is(pg.getTransverser().getEdges()));
        assertThat(pg.getVertices(), is(pg.getTransverser().getVertices()));
    }

    @Test
    public void shouldFindTrangles() throws Exception {
        LinkedHashSet<SubgraphInterface> faces = pg.getTransverser().findAllSubgraphs(v1);
        assertThat(faces.size(), is(3));
    }

    @Test
    public void shouldFindAllSubgraphs() throws Exception {
        LinkedHashSet<SubgraphInterface> faces = pg.getTransverser().findAllSubgraphs();
        assertThat(faces.size(), is(4));
    }


    @Test
    public void shouldCalculateCorrectLength() throws Exception {
        // E1 = V1(1,1,1) -> V3(6,1,1)
        // sqr((1 - 6)2 + (1 - 1)2 + (1 - 1)2)
        // (-5 * -5) + (0) + (0)
        // 25 + 0 + 0
        // 5

        assertEquals(e1.getLength(), 5, 0);

        // E2 = V3(6,1,1) -> v2(3,6,3)
        // sqr((6 -3)2 + (1 - 6)2 + (1 - 3)2
        // (3 * 3) + (-5 * -5) + (-2 * -2)
        // sqr(9 + 25 + 4)
        // 6.16
        assertEquals(e2.getLength(), 6.16, 1);

        // E3 = V1(1,1,1) -> V2(3,6,3)
        // sqr((1 - 3)2 + (1 - 6)2 + (1 - 3)2)
        // (-2 * -2) + (-5 * -5) + (-2 * -2)
        // sqr(4 + 25 + 4)
        // 5.74
        assertEquals(e3.getLength(), 5.74, 1);
    }

    @Test
    public void shouldCalculateILine() throws Exception {

        double c = EdgeUtil.computeImaginaryEdgeLength(e1, e2);

        assertEquals(c, e3.getLength(), 1);
    }

    @Test
    public void shouldCalculateCorrectAngle() throws Exception {

        double e1l = 5, e2l = 6.15, e3l = 5.74;

        // e3 to e1 = 69.606°
        double angle1 = EdgeUtil.computeAngle(e3l, e2l, e1l);
        assertEquals(angle1, 69.606, 1);

        // e1 to e2 = 60.937°
        double angle2 = EdgeUtil.computeAngle(e1l, e3l, e2l);
        assertEquals(angle2, 60.937, 1);

        // e2 to e3 = 49.588°
        double angle3 = EdgeUtil.computeAngle(e2l, e1l, e3l);
        assertEquals(angle3, 49.588, 1);

        // all angles should be 180
        assertEquals(angle1 + angle2 + angle3, 180, 1);
    }

    /**
     * Very small deviation allowed.
     */
    @Test
    public void shouldGetExactLengths() {

        // e3 to e1
        double angle1 = EdgeUtil.computeAngle(e3.getLength(), e2.getLength(), e1.getLength());

        // e1 to e2
        double angle2 = EdgeUtil.computeAngle(e1.getLength(), e3.getLength(), e2.getLength());

        // e2 to e3
        double angle3 = EdgeUtil.computeAngle(e2.getLength(), e1.getLength(), e3.getLength());

        assertEquals(Math.round(angle1 + angle2 + angle3), 180);
    }


    @Test
    public void shouldComputeDerivedEdge() {
        // e3 to e1
        double angle1 = EdgeUtil.computeAngle(e3, e1);

        // e1 to e2
        double angle2 = EdgeUtil.computeAngle(e1, e2);

        // e2 to e3
        double angle3 = EdgeUtil.computeAngle(e2, e3);

        assertEquals(Math.round(angle1 + angle2 + angle3), 180);
    }

}
