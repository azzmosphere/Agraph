import azzmosphere.agraph.VerticesFactory;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.subgraph.SubgraphMapperImp;
import azzmosphere.agraph.tranverser.RegularPolyhedronDFS;
import azzmosphere.agraph.vertices.VertexInterface;
import datastructures.GenericVertex2DStructure;
import org.junit.Test;
import vertices.TestClassVerticesMapperImpl;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by aaron.spiteri on 12/05/2016.
 *
 * Test Adjacency matrix creation and manipulation.
 */
public class TestAdjacencyMatrix {

    /*
     * The graph that this is testing should be something like this:
     *     1     2     3    4
     *  1 v1--------------->v4
     *     ^  \             |
     *  2  |      \         |
     *     |        \       |
     *  3  |           \    |
     *     |               \V
     *  4 V2<---------------v3
     */
    @Test
    public void shouldCreateCorrectAdjencyMatrix() throws Exception {
        PlannerGraph pg = new PlannerGraph(new RegularPolyhedronDFS(new SubgraphMapperImp()));
        GenericVertex2DStructure data = new GenericVertex2DStructure();
        VerticesFactory vf = new VerticesFactory(new TestClassVerticesMapperImpl());

        // Create two dimensional vertices.
        VertexInterface v1 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1}), "v1");
        VertexInterface v2 = pg.attachVertex(vf.createVertex(data, new int[]{4, 1}), "v2");
        VertexInterface v3 = pg.attachVertex(vf.createVertex(data, new int[]{4, 4}), "v3");
        VertexInterface v4 = pg.attachVertex(vf.createVertex(data, new int[]{1, 4}), "v4");

        pg.createEdge(v1, v4); //e1
        pg.createEdge(v4, v3); //e2
        pg.createEdge(v3, v1); //e3
        pg.createEdge(v3, v2); //e4
        pg.createEdge(v2, v1); //e5

        ArrayList<ArrayList<Boolean>> adjacentMatrix = pg.getAdjacencyMatrix();

        /*
         * Adjacency matrix should look like this:
         * [false, true, true, true]
         * [true, false, true, false]
         * [true, true, false, true]
         * [true, false, true, false]
         */


        // V1
        assertThat(adjacentMatrix.get(0).get(0), is(false));
        assertThat(adjacentMatrix.get(0).get(1), is(true));
        assertThat(adjacentMatrix.get(0).get(2), is(true));
        assertThat(adjacentMatrix.get(0).get(3), is(true));

        // V2
        assertThat(adjacentMatrix.get(1).get(0), is(true));
        assertThat(adjacentMatrix.get(1).get(1), is(false));
        assertThat(adjacentMatrix.get(1).get(2), is(true));
        assertThat(adjacentMatrix.get(1).get(3), is(false));

        // V3
        assertThat(adjacentMatrix.get(2).get(0), is(true));
        assertThat(adjacentMatrix.get(2).get(1), is(true));
        assertThat(adjacentMatrix.get(2).get(2), is(false));
        assertThat(adjacentMatrix.get(2).get(3), is(true));

        // V4
        assertThat(adjacentMatrix.get(3).get(0), is(true));
        assertThat(adjacentMatrix.get(3).get(1), is(false));
        assertThat(adjacentMatrix.get(3).get(2), is(true));
        assertThat(adjacentMatrix.get(3).get(3), is(false));

        for (ArrayList<Boolean> i : adjacentMatrix) {
            System.out.println(i);
        }

        System.out.println(3 & 4);

    }

}
