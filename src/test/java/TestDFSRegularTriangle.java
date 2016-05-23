import azzmosphere.agraph.vertices.VerticesFactory;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperImp;
import azzmosphere.agraph.tranverser.RegularPolyhedronDFS;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.edge.Edge;
import datastructures.GenericVertex3DStructure;
import org.junit.Before;
import org.junit.Test;
import vertices.TestClassVerticesMapperImpl;

import java.util.LinkedHashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * Find faces in a regular triangle. The diagram and vertices and edges are described below.
 *
 *             V2
 *  4         / | \
 *  3  E3   /   |  \  E2
 *  2      /  / V4\ \
 *  1    / /        \\
 *  0  V1 -----------V3
 *     0   1   2   3  4
 *            E1
 *
 *   V1(0,0,0)
 *   V2(2,4,2)
 *   V3(4,0,0)
 *   V4(2,2,4)
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
        pg = new PlannerGraph(new RegularPolyhedronDFS(new SubgraphMapperImp()));
        v1 = pg.attachVertex(vf.createVertex(data, new int[]{0,0,0}), "v1");
        v2 = pg.attachVertex(vf.createVertex(data, new int[]{2,4,2}), "v2");
        v3 = pg.attachVertex(vf.createVertex(data, new int[]{4,0,0}), "v3");
        v4 = pg.attachVertex(vf.createVertex(data, new int[]{2,2,4}), "v4");

        e1 = pg.createEdge(v1, v3, "e1");
        e2 = pg.createEdge(v3, v2, "e2");
        e3 = pg.createEdge(v1, v2, "e3");
        e4 = pg.createEdge(v1, v4, "e4");
        e5 = pg.createEdge(v3, v4, "e5");
        e6 = pg.createEdge(v4, v2, "e2");
    }

    @Test
    public void shouldBeBalances() {

        pg.getTransverser().isBalanced();

        LinkedHashSet<SubgraphInterface> faces = pg.getTransverser().findAllSubgraphs(v1);

        //assertThat(pg.getTransverser().isBalanced(), is(true));
    }
}
