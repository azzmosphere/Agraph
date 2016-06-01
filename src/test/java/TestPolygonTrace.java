import azzmosphere.agraph.edge.Edge;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperImp;
import azzmosphere.agraph.tranverser.TransverserInterface;
import azzmosphere.agraph.subgraph.SubgraphMapperInterface;
import azzmosphere.agraph.tranverser.VerticeSearchDFSImp;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.vertices.VerticesFactory;
import azzmosphere.agraph.vertices.VerticesMapperInterface;
import azzmosphere.agraph.tranverser.PolyhedronDFS;
import azzmosphere.agraph.edge.Axis;
import datastructures.GenericVertex2DStructure;
import decider.MockDeciderClass;
import org.junit.Before;
import org.junit.Test;
import vertices.TestClassVerticesMapperImpl;

import java.util.LinkedHashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test that the DFS search can work on a standard polygon.
 *
 * The structure is a regular square with the following coordinates.
 *
 *  5 V3---------V4
 *  4  |    E3    |
 *  3  | E4    E2 |
 *  2  |    E1    |
 *  1 V1---------V2
 *    1  2  3  4  5
 *
 * Created by aaron.spiteri on 24/05/2016.
 */
public class TestPolygonTrace {
    private PlannerGraph pg;
    private SubgraphMapperInterface circuitMapper = new SubgraphMapperImp();
    private TransverserInterface dfs = new PolyhedronDFS(circuitMapper);
    private GenericVertex2DStructure data = new GenericVertex2DStructure();
    private VerticesMapperInterface vmapper = new TestClassVerticesMapperImpl();
    private VerticesFactory vf = new VerticesFactory(vmapper);

    private VertexInterface v1;
    private VertexInterface v2;
    private VertexInterface v3;
    private VertexInterface v4;

    private Edge e1;
    private Edge e2;
    private Edge e3;
    private Edge e4;


    @Before
    public void initilise() throws Exception {
        pg = new PlannerGraph(dfs,  new VerticeSearchDFSImp(new MockDeciderClass()));
        v1 = pg.attachVertex(vf.createVertex(data, new int[] {1, 1}), "v1");
        v2 = pg.attachVertex(vf.createVertex(data, new int[] {5, 1}), "v2");
        v3 = pg.attachVertex(vf.createVertex(data, new int[] {1, 5}), "v3");
        v4 = pg.attachVertex(vf.createVertex(data, new int[] {5, 5}), "v4");

        e1 = pg.createEdge(v1, v2, "e1", Axis.XAXIS);
        e2 = pg.createEdge(v2, v4, "e2", Axis.YAXIS);
        e3 = pg.createEdge(v4, v3, "e3", Axis.XAXIS);
        e4 = pg.createEdge(v3, v1, "e4", Axis.YAXIS);
    }

    @Test
    public void shouldGetFace() throws Exception {
        LinkedHashSet<SubgraphInterface> faces = dfs.findAllSubgraphs(v1);

        assertThat(faces.size(), is(1));
        SubgraphInterface face = (SubgraphInterface) faces.toArray()[0];

        assertThat(face.getEdges().size(), is(4));
        assertThat(face.getVertices().size(), is(4));

        assertThat(face.getVertices().toArray(), is(new VertexInterface[]{v1, v2, v4, v3}));
    }
}
