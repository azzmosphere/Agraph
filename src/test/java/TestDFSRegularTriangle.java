import azzmosphere.agraph.vertices.VerticesFactory;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperImp;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.tranverser.PolyhedronDFS;
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
 *  5         / | \
 *  4  E3   /   |  \  E2
 *  3      /  / V4\ \
 *  2    / /        \\
 *  1  V1 -----------V3
 *     1   2   3   4  5
 *            E1
 *
 *   V1(1,1,1)
 *   V2(3,5,3)
 *   V3(5,1,1)
 *   V4(3,3,5)
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
        pg = new PlannerGraph(new PolyhedronDFS(new SubgraphMapperImp()));
        v1 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 1}), "v1");
        v2 = pg.attachVertex(vf.createVertex(data, new int[]{3, 6, 3}), "v2");
        v3 = pg.attachVertex(vf.createVertex(data, new int[]{6, 1, 1}), "v3");
        v4 = pg.attachVertex(vf.createVertex(data, new int[]{1, 3, 6}), "v4");

        e1 = pg.createEdge(v1, v3, "e1", Edge.Axis.XAXIS);
        e2 = pg.createEdge(v3, v2, "e2", Edge.Axis.YAXIS);
        e3 = pg.createEdge(v2, v1, "e3", Edge.Axis.YAXIS);
        e4 = pg.createEdge(v1, v4, "e4", Edge.Axis.ZAXIS);
        e5 = pg.createEdge(v4, v2, "e5", Edge.Axis.XAXIS);
        e6 = pg.createEdge(v4, v3, "e6", Edge.Axis.YAXIS);
    }

    @Test
    public void shouldFindTrangles() throws Exception {

        pg.getTransverser().isBalanced();

        LinkedHashSet<SubgraphInterface> faces = pg.getTransverser().findAllSubgraphs(v1);
        assertThat(faces.size(), is(2));
    }
}
