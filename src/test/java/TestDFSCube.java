import azzmosphere.agraph.tranverser.VerticeSearchDFSImp;
import azzmosphere.agraph.vertices.VerticesFactory;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.subgraph.SubgraphMapperImp;
import azzmosphere.agraph.tranverser.TransverserInterface;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.tranverser.PolyhedronDFS;
import azzmosphere.agraph.edge.Axis;
import datastructures.GenericVertex3DStructure;
import decider.MockDeciderClass;
import org.junit.Before;
import org.junit.Test;
import vertices.TestClassVerticesMapperImpl;
import java.util.LinkedHashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * Test Depth First Search implementation.  The graph is a cube within a planner graph that has three
 * diminisions to describe it. x,y and z
 *
 *                  E6
 *           V5+-----------+V6
 *           / |         / |
 *      E5 /   |E9     /E7 |
 *       /     |  E1  /    | E8
 *   V1+----------+V2      |
 *     |     / V7--|-------+ V8
 *     |    /      | E10  /
 *  E4 |  /  E12   |    / E11
 *     | /         |  /
 *   V4+-----------+V3
 *          E3
 *
 * Created by aaron.spiteri on 17/05/2016.
 */
public class TestDFSCube {
    private PlannerGraph pg;
    private GenericVertex3DStructure data = new GenericVertex3DStructure();
    private VerticesFactory vf = new VerticesFactory(new TestClassVerticesMapperImpl());

    // Create three dimensional vertices.
    private VertexInterface v1;
    private VertexInterface v2;
    private VertexInterface v3;
    private VertexInterface v4;
    private VertexInterface v5;
    private VertexInterface v6;
    private VertexInterface v7;
    private VertexInterface v8;

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


    @Before
    public void initialize() throws Exception {
        pg = new PlannerGraph(new PolyhedronDFS(new SubgraphMapperImp()),
                new VerticeSearchDFSImp(new MockDeciderClass()));


        // Create three dimensional vertices.
        v1 = pg.attachVertex(vf.createVertex(data, new int[]{1, 4, 1}), "v1");
        v2 = pg.attachVertex(vf.createVertex(data, new int[]{4, 4, 1}), "v2");
        v3 = pg.attachVertex(vf.createVertex(data, new int[]{4, 1, 1}), "v3");
        v4 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 1}), "v4");

        v5 = pg.attachVertex(vf.createVertex(data, new int[]{1, 4, 4}), "v5");
        v6 = pg.attachVertex(vf.createVertex(data, new int[]{4, 4, 4}), "v6");
        v7 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 4}), "v7");
        v8 = pg.attachVertex(vf.createVertex(data, new int[]{4, 1, 4}), "v8");

        e1 = pg.createEdge(v1, v2, "e1", Axis.XAXIS); // e1
        e2 = pg.createEdge(v2, v3, "e2", Axis.YAXIS); // e2

        e3 = pg.createEdge(v3, v4, "e3", Axis.XAXIS); // e3
        e4 = pg.createEdge(v4, v1, "e4", Axis.YAXIS); // e4

        e5 = pg.createEdge(v1, v5, "e5", Axis.ZAXIS); // e5
        e6 = pg.createEdge(v5, v6, "e6", Axis.XAXIS); // e6

        e7 = pg.createEdge(v2, v6, "e7", Axis.ZAXIS); // e7
        e8 = pg.createEdge(v6, v8, "e8", Axis.YAXIS); // e8

        e9 = pg.createEdge(v5, v7, "v9", Axis.YAXIS); // e9

        e10 = pg.createEdge(v7, v8, "e10", Axis.XAXIS); // e10

        e11 = pg.createEdge(v3, v8, "e11", Axis.ZAXIS); // e11

        e12 = pg.createEdge(v4, v7, "e12", Axis.ZAXIS); // e12
    }

    @Test
    public void shouldFindVertex() throws Exception {
        TransverserInterface dfs = pg.getTransverser();

        SubgraphInterface f = (SubgraphInterface) dfs.findAllSubgraphs(v1).toArray()[0];

        assertThat(f.getEdges().size(), is(4));
        assertThat(f.getVertices().size(), is(4));

        // The first face should be v1, v2, v3, v4, v1 - Note that the first element is dropped because we started
        // the search on that one.
        assertThat(f.getVertices().toArray()[0].equals(v1), is(true)); //V1
        assertThat(f.getVertices().toArray()[1].equals(v2), is(true)); //V2
        assertThat(f.getVertices().toArray()[2].equals(v3), is(true)); //V3
        assertThat(f.getVertices().toArray()[3].equals(v4), is(true)); //V4

        // Follow edges e1, e2, e3, e4
        assertThat(f.getEdges().toArray()[0].equals(e1), is(true));
        assertThat(f.getEdges().toArray()[1].equals(e2), is(true));
        assertThat(f.getEdges().toArray()[2].equals(e3), is(true));
        assertThat(f.getEdges().toArray()[3].equals(e4), is(true));
    }

    @Test
    public void shouldFindAllFacesForVertex() throws Exception {
        TransverserInterface dfs = pg.getTransverser();

        LinkedHashSet<SubgraphInterface> faces = dfs.findAllSubgraphs(v1);

        // On a cube there is allways three faces.
        assertThat(faces.size(), is(3));

        // Check the faces.
        SubgraphInterface f = (SubgraphInterface) faces.toArray()[0];
        assertThat(f.getEdges().toArray()[0].equals(e1), is(true));
        assertThat(f.getEdges().toArray()[1].equals(e2), is(true));
        assertThat(f.getEdges().toArray()[2].equals(e3), is(true));
        assertThat(f.getEdges().toArray()[3].equals(e4), is(true));

        f = (SubgraphInterface) faces.toArray()[1];
        assertThat(f.getEdges().toArray()[0].equals(e1), is(true));
        assertThat(f.getEdges().toArray()[1].equals(e7), is(true));
        assertThat(f.getEdges().toArray()[2].equals(e6), is(true));
        assertThat(f.getEdges().toArray()[3].equals(e5), is(true));

        f = (SubgraphInterface) faces.toArray()[2];
        assertThat(f.getEdges().toArray()[0].equals(e4), is(true));
        assertThat(f.getEdges().toArray()[1].equals(e12), is(true));
        assertThat(f.getEdges().toArray()[2].equals(e9), is(true));
        assertThat(f.getEdges().toArray()[3].equals(e5), is(true));
    }

    @Test
    public void shouldFindAllFacesInCube() throws Exception {


        TransverserInterface dfs = pg.getTransverser();
        LinkedHashSet<SubgraphInterface> faces = dfs.findAllSubgraphs();

        assertThat(pg.getVertices().size(), is(8));
        assertThat(pg.getEdges().size(), is(12));
        assertThat(faces.size(), is(6));

        assertThat(dfs.isBalanced(), is(true));
    }
}
