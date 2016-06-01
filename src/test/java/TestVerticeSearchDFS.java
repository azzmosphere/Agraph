import azzmosphere.agraph.edge.Axis;
import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperImp;
import azzmosphere.agraph.tranverser.PolyhedronDFS;
import azzmosphere.agraph.tranverser.VerticeSearchDFSImp;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.vertices.VerticesFactory;
import datastructures.GenericVertex3DStructure;
import decider.MockDeciderClass;
import org.junit.Before;
import org.junit.Test;
import vertices.TestClassVerticesMapperImpl;
import vertices.instinct.TestClassForInstinct;

import java.util.LinkedHashSet;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by aaron.spiteri on 1/06/2016.
 *
 * Test vertices population selection given a DFS search.  The test is performed using a ocatgonal prism because it
 * has a large number of nodes, edges and faces and is well enough known to have predictable results.
 *
 * Graph properties are:
 *
 *  f = 10
 *  v = 16
 *  e = 24
 */
public class TestVerticeSearchDFS {
    private VertexInterface v1;
    private VertexInterface v2;
    private VertexInterface v3;
    private VertexInterface v4;
    private VertexInterface v5;
    private VertexInterface v6;
    private VertexInterface v7;
    private VertexInterface v8;
    private VertexInterface v9;
    private VertexInterface v10;
    private VertexInterface v11;
    private VertexInterface v12;
    private VertexInterface v13;
    private VertexInterface v14;
    private VertexInterface v15;
    private VertexInterface v16;

    private Edge e1;
    private Edge e2;
    private Edge e3;
    private Edge e4;
    private Edge e5;
    private Edge e6;
    private Edge e7;
    private Edge e8;
    private Edge e9;
    private Edge e10;
    private Edge e11;
    private Edge e12;
    private Edge e13;
    private Edge e14;
    private Edge e15;
    private Edge e16;
    private Edge e17;
    private Edge e18;
    private Edge e19;
    private Edge e20;
    private Edge e21;
    private Edge e22;
    private Edge e23;
    private Edge e24;

    private PlannerGraph pg;
    private GenericVertex3DStructure data = new GenericVertex3DStructure();
    private VerticesFactory vf = new VerticesFactory(new TestClassVerticesMapperImpl());
    private MockDeciderClass mockDeciderClass = mock(MockDeciderClass.class);
    private TestClassForInstinct instinct = new TestClassForInstinct();

    @Before
    public void initilise() throws Exception {
        pg = new PlannerGraph(new PolyhedronDFS(
                new SubgraphMapperImp()),
                new VerticeSearchDFSImp(mockDeciderClass));
        v1 = pg.attachVertex(vf.createVertex(data, new int[]{1, 3, 3}), "v1");
        v2 = pg.attachVertex(vf.createVertex(data, new int[]{2, 3, 4}), "v2");
        v3 = pg.attachVertex(vf.createVertex(data, new int[]{3, 3, 5}), "v3");
        v4 = pg.attachVertex(vf.createVertex(data, new int[]{4, 3, 4}), "v4");
        v5 = pg.attachVertex(vf.createVertex(data, new int[]{5, 3, 3}), "v5");
        v6 = pg.attachVertex(vf.createVertex(data, new int[]{4, 3, 2}), "v6");
        v7 = pg.attachVertex(vf.createVertex(data, new int[]{3, 3, 1}), "v7");
        v8 = pg.attachVertex(vf.createVertex(data, new int[]{2, 3, 2}), "v8");
        v9 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 3}), "v9");
        v10 = pg.attachVertex(vf.createVertex(data, new int[]{2, 1, 4}), "v10");
        v11 = pg.attachVertex(vf.createVertex(data, new int[]{3, 1, 5}), "v11");
        v12 = pg.attachVertex(vf.createVertex(data, new int[]{4, 1, 4}), "v11");
        v13 = pg.attachVertex(vf.createVertex(data, new int[]{5, 1, 3}), "v13");
        v14 = pg.attachVertex(vf.createVertex(data, new int[]{4, 1, 2}), "v14");
        v15 = pg.attachVertex(vf.createVertex(data, new int[]{3, 1, 1}), "v15");
        v16 = pg.attachVertex(vf.createVertex(data, new int[]{2, 1, 2}), "v16");

        e1 = pg.createEdge(v1, v2, "e1", Axis.ZAXIS);
        e2 = pg.createEdge(v2, v3, "e2", Axis.ZAXIS);
        e3 = pg.createEdge(v3, v4, "e3", Axis.ZAXIS);
        e4 = pg.createEdge(v4, v5, "e4", Axis.ZAXIS);
        e5 = pg.createEdge(v5, v6, "e5", Axis.ZAXIS);
        e6 = pg.createEdge(v6, v7, "e6", Axis.ZAXIS);
        e7 = pg.createEdge(v7, v8, "e7", Axis.ZAXIS);
        e8 = pg.createEdge(v8, v1, "e8", Axis.ZAXIS);

        e9 = pg.createEdge(v9, v10, "e9", Axis.ZAXIS);
        e10 = pg.createEdge(v10, v11, "e10", Axis.ZAXIS);
        e11 = pg.createEdge(v11, v12, "e11", Axis.ZAXIS);
        e12 = pg.createEdge(v12, v13, "e12", Axis.ZAXIS);
        e13 = pg.createEdge(v13, v14, "e13", Axis.ZAXIS);
        e14 = pg.createEdge(v14, v15, "e14", Axis.ZAXIS);
        e15 = pg.createEdge(v15, v16, "e15", Axis.ZAXIS);
        e16 = pg.createEdge(v16, v9, "e16", Axis.ZAXIS);

        e17 = pg.createEdge(v1, v9, "e17", Axis.YAXIS);
        e18 = pg.createEdge(v2, v10, "e18", Axis.YAXIS);
        e19 = pg.createEdge(v3, v11, "e19", Axis.YAXIS);
        e20 = pg.createEdge(v4, v12, "e20", Axis.YAXIS);
        e21 = pg.createEdge(v5, v13, "e21", Axis.YAXIS);

        e22 = pg.createEdge(v14, v6, "e22", Axis.YAXIS);
        e23 = pg.createEdge(v15, v7, "e23", Axis.YAXIS);
        e24 = pg.createEdge(v16, v8, "e24", Axis.YAXIS);
    }

    @Test
    public void shouldBeBalances() throws Exception {
        assertTrue(pg.getTransverser().isBalanced());
    }

    @Test
    public void shouldFindFacesConnectedToInstinct() throws Exception {

        LinkedHashSet<SubgraphInterface> faces = pg.getTransverser().findAllSubgraphs(v16);
        LinkedHashSet<SubgraphInterface> face  = new LinkedHashSet<>();
        face.add((SubgraphInterface) faces.toArray()[0]);

        when(mockDeciderClass.isPopulation(instinct, faces)).thenReturn(
                face
        );

        assertThat(pg.getSearcher().getPopulation(instinct), is(face));
    }
}
