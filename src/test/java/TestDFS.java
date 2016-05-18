import azzmosphere.agraph.VerticesFactory;
import azzmosphere.agraph.face.FaceInterface;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.tranverser.DFS;
import azzmosphere.agraph.tranverser.DFSAttempt2;
import azzmosphere.agraph.tranverser.TranverserInterface;
import azzmosphere.agraph.vertices.VertexInterface;
import datastructures.GenericVertex3DStructure;
import org.junit.Before;
import org.junit.Test;
import vertices.TestClassVerticesMapperImpl;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * Test Depth First Search implementation.
 *
 * Created by aaron.spiteri on 17/05/2016.
 */
public class TestDFS {
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

    @Before
    public void initialize() throws Exception {
        pg = new PlannerGraph(new DFS());


        // Create three dimensional vertices.
        v1 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 1}), "v0");
        v2 = pg.attachVertex(vf.createVertex(data, new int[]{4, 1, 1}), "v1");
        v3 = pg.attachVertex(vf.createVertex(data, new int[]{4, 4, 1}), "v2");
        v4 = pg.attachVertex(vf.createVertex(data, new int[]{1, 4, 1}), "v3");
        v5 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 4}), "v4");
        v6 = pg.attachVertex(vf.createVertex(data, new int[]{4, 1, 4}), "v5");
        v7 = pg.attachVertex(vf.createVertex(data, new int[]{4, 4, 4}), "v6");
        v8 = pg.attachVertex(vf.createVertex(data, new int[]{1, 4, 4}), "v7");

        pg.createEdge(v1, v4); // e1
        pg.createEdge(v1, v5); // e2

        pg.createEdge(v2, v1); // e3
        pg.createEdge(v2, v8); // e4

        pg.createEdge(v3, v2); // e5
        pg.createEdge(v3, v7); // e6

        pg.createEdge(v4, v6); // e7
        pg.createEdge(v4, v3); // e8

        pg.createEdge(v5, v6); // e9

        pg.createEdge(v6, v7); // e10

        pg.createEdge(v7, v8); // e11

        pg.createEdge(v8, v5); // e12
    }

    /**
     * Test that all 6 faces in a cube are found.
     *
     * @throws Exception
     */
    @Test
    public void shouldGetAllSubgraphs() throws Exception {
        DFS dfs = (DFS) pg.getTransverser();

        //TODO this should become part of the tranvserer interface.
        dfs.setAdjancencyMatrix(pg.getAdjacencyMatrixSimple());
        dfs.setVertices(pg.getVertices());
        dfs.setEdges(pg.getEdges());

        ArrayList<FaceInterface> faces = dfs.findAllFaces();
        assertThat(faces.size(), is(6));
    }

    @Test
    public void shouldFindVertex() throws Exception {
        DFSAttempt2 dfs = new DFSAttempt2();
        dfs.setAdjacencyMatrix(pg.getAdjacencyMatrixSimple());
        dfs.setVertices(pg.getVertices());
        dfs.setEdges(pg.getEdges());

        FaceInterface f = dfs.findFace(v1);
    }
}
