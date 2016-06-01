import azzmosphere.agraph.edge.Axis;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperImp;
import azzmosphere.agraph.tranverser.PolyhedronDFS;
import azzmosphere.agraph.tranverser.VerticeSearchDFSImp;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.vertices.VerticesFactory;
import datastructures.GenericVertex3DStructure;
import decider.MockDeciderClass;
import org.junit.Before;
import org.junit.Test;
import vertices.TestClassVerticesMapperImpl;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * Tests on a pyramid graph that is shaped like follows:
 *
 *  6           ^
 *  5         /   \  \
 *  4       /      \   \
 *  3     /         \   /
 *  2   /            \/
 *  1  ---------------
 *
 *     1  2  3  4  5  6
 *
 *  Vertices are:
 *
 *  V1(1,1,1)
 *  V2(6,1,1)
 *  V3(6,1,6)
 *  V4(3,6,3)
 *  V5(1,1,6)
 *
 * Created by aaron.spiteri on 31/05/2016.
 */
public class TestDFSPyramid {
    private VertexInterface v1;
    private VertexInterface v2;
    private VertexInterface v3;
    private VertexInterface v4;
    private VertexInterface v5;

    private Edge e1;
    private Edge e2;
    private Edge e3;
    private Edge e4;
    private Edge e5;
    private Edge e6;
    private Edge e7;
    private Edge e8;

    private PlannerGraph pg;
    private GenericVertex3DStructure data = new GenericVertex3DStructure();
    private VerticesFactory vf = new VerticesFactory(new TestClassVerticesMapperImpl());

    @Before
    public void initilize() throws Exception {
        pg = new PlannerGraph(new PolyhedronDFS(
                new SubgraphMapperImp()),
                new VerticeSearchDFSImp(new MockDeciderClass()));
        v1 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 1}), "v1");
        v2 = pg.attachVertex(vf.createVertex(data, new int[]{6, 1, 1}), "v2");
        v3 = pg.attachVertex(vf.createVertex(data, new int[]{6, 1, 6}), "v3");
        v4 = pg.attachVertex(vf.createVertex(data, new int[]{3, 6, 3}), "v4");
        v5 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 6}), "v5");

        e1 = pg.createEdge(v1, v4, "e1", Axis.YAXIS);
        e2 = pg.createEdge(v4, v3, "e2", Axis.YAXIS);
        e3 = pg.createEdge(v3, v2, "e3", Axis.ZAXIS);
        e4 = pg.createEdge(v2, v1, "e4", Axis.XAXIS);
        e5 = pg.createEdge(v1, v5, "e5", Axis.ZAXIS);
        e6 = pg.createEdge(v5, v3, "e6", Axis.XAXIS);
        e7 = pg.createEdge(v5, v4, "e7", Axis.YAXIS);
        e8 = pg.createEdge(v2, v4, "e8", Axis.YAXIS);
    }

    @Test
    public void shouldFindAllSubgraphs() throws Exception {
        LinkedHashSet<SubgraphInterface> faces = pg.getTransverser().findAllSubgraphs();

        assertEquals(faces.size(), 5);
        assertTrue(pg.getTransverser().isBalanced());
    }

    @Test
    public void shouldFindAllSubgraphsForV1() throws Exception {
        LinkedHashSet<SubgraphInterface> faces = pg.getTransverser().findAllSubgraphs(v1);
        assertEquals(faces.size(), 3);
    }

    @Test
    public void shouldFindAllSubgraphsForV4() throws Exception {
        LinkedHashSet<SubgraphInterface> faces = pg.getTransverser().findAllSubgraphs(v4);
        assertEquals(faces.size(), 4);
    }
}
