import azzmosphere.agraph.VerticesFactory;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.vertices.VertexInterface;
import datastructures.GenericVertex3DStructure;
import org.junit.Test;
import vertices.TestClassVerticesMapperImpl;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by aaron.spiteri on 15/05/2016.
 */
public class TestAdjacencyMatrix3D {
    /*
     * The graph that this is testing should be something like this:
     *
     *           v5--------------->v6
     *         3 /              /  |
     *       2 /               /   |
     *     1 /   2     3    4 /    |
     *  1 v1--------------->v4     | v7   (v8: is the intersection of v2 -> v8,  v8 -> v5,  v7 ->v8)
     *     ^                |     /
     *  2  |                |    /
     *     |                |   /
     *  3  |                |  /
     *     |                V/
     *  4 V2<---------------v3
     *
     * The aim here is to graph a cube.
     */

    @Test
    public void shouldCreateCorrectAdjacencyMatrix() throws Exception {
        PlannerGraph pg = new PlannerGraph();
        GenericVertex3DStructure data = new GenericVertex3DStructure();
        VerticesFactory vf = new VerticesFactory(new TestClassVerticesMapperImpl());

        // Create three dimensional vertices.
        VertexInterface v1 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 1}), "v1");
        VertexInterface v2 = pg.attachVertex(vf.createVertex(data, new int[]{4, 1, 1}), "v2");
        VertexInterface v3 = pg.attachVertex(vf.createVertex(data, new int[]{4, 4, 1}), "v3");
        VertexInterface v4 = pg.attachVertex(vf.createVertex(data, new int[]{1, 4, 1}), "v4");
        VertexInterface v5 = pg.attachVertex(vf.createVertex(data, new int[]{1, 1, 4}), "v5");
        VertexInterface v6 = pg.attachVertex(vf.createVertex(data, new int[]{4, 1, 4}), "v6");
        VertexInterface v7 = pg.attachVertex(vf.createVertex(data, new int[]{4, 4, 4}), "v7");
        VertexInterface v8 = pg.attachVertex(vf.createVertex(data, new int[]{1, 4, 4}), "v8");

                /*
         * Adjacency matrix should look like this:
         *   [false, true, false, true, true,  false, false, false]
         *   [true, false, true, false, false, false, false, true]
         *   [false,true, false, true,  false, false, true,  false]
         *   [true, false, true, false, false, true,  false, false]
         *   [true, false, false, false, false, true, false, true]
         *   [false, false, false, true, true,  false, true, false]
         *   [false, false, true, false, false, true, false, true]
         *   [false, true,  false,false, true,  false, true, false]
         */

        pg.createEdge(v1, v4);
        pg.createEdge(v1, v5);

        pg.createEdge(v2, v1);
        pg.createEdge(v2, v8);

        pg.createEdge(v3, v2);
        pg.createEdge(v3, v7);

        pg.createEdge(v4, v6);
        pg.createEdge(v4, v3);

        pg.createEdge(v5, v6);

        pg.createEdge(v6, v7);

        pg.createEdge(v7, v8);

        pg.createEdge(v8, v5);



        ArrayList<ArrayList<Boolean>> adjacentMatrix = pg.getAdjacencyMatrix();
        // V1
        assertThat(adjacentMatrix.get(0).get(0), is(false));
        assertThat(adjacentMatrix.get(0).get(1), is(true));
        assertThat(adjacentMatrix.get(0).get(2), is(false));
        assertThat(adjacentMatrix.get(0).get(3), is(true));
        assertThat(adjacentMatrix.get(0).get(4), is(true));
        assertThat(adjacentMatrix.get(0).get(5), is(false));
        assertThat(adjacentMatrix.get(0).get(6), is(false));
        assertThat(adjacentMatrix.get(0).get(7), is(false));

        // V2
        assertThat(adjacentMatrix.get(1).get(0), is(true));
        assertThat(adjacentMatrix.get(1).get(1), is(false));
        assertThat(adjacentMatrix.get(1).get(2), is(true));
        assertThat(adjacentMatrix.get(1).get(3), is(false));
        assertThat(adjacentMatrix.get(1).get(4), is(false));
        assertThat(adjacentMatrix.get(1).get(5), is(false));
        assertThat(adjacentMatrix.get(1).get(6), is(false));
        assertThat(adjacentMatrix.get(1).get(7), is(true));

        // V3
        assertThat(adjacentMatrix.get(2).get(0), is(false));
        assertThat(adjacentMatrix.get(2).get(1), is(true));
        assertThat(adjacentMatrix.get(2).get(2), is(false));
        assertThat(adjacentMatrix.get(2).get(3), is(true));
        assertThat(adjacentMatrix.get(2).get(4), is(false));
        assertThat(adjacentMatrix.get(2).get(5), is(false));
        assertThat(adjacentMatrix.get(2).get(6), is(true));
        assertThat(adjacentMatrix.get(2).get(7), is(false));

        // V4
        assertThat(adjacentMatrix.get(3).get(0), is(true));
        assertThat(adjacentMatrix.get(3).get(1), is(false));
        assertThat(adjacentMatrix.get(3).get(2), is(true));
        assertThat(adjacentMatrix.get(3).get(3), is(false));
        assertThat(adjacentMatrix.get(3).get(4), is(false));
        assertThat(adjacentMatrix.get(3).get(5), is(true));
        assertThat(adjacentMatrix.get(3).get(6), is(false));
        assertThat(adjacentMatrix.get(3).get(7), is(false));

        // V5
        assertThat(adjacentMatrix.get(4).get(0), is(true));
        assertThat(adjacentMatrix.get(4).get(1), is(false));
        assertThat(adjacentMatrix.get(4).get(2), is(false));
        assertThat(adjacentMatrix.get(4).get(3), is(false));
        assertThat(adjacentMatrix.get(4).get(4), is(false));
        assertThat(adjacentMatrix.get(4).get(5), is(true));
        assertThat(adjacentMatrix.get(4).get(6), is(false));
        assertThat(adjacentMatrix.get(4).get(7), is(true));

        // V6
        assertThat(adjacentMatrix.get(5).get(0), is(false));
        assertThat(adjacentMatrix.get(5).get(1), is(false));
        assertThat(adjacentMatrix.get(5).get(2), is(false));
        assertThat(adjacentMatrix.get(5).get(3), is(true));
        assertThat(adjacentMatrix.get(5).get(4), is(true));
        assertThat(adjacentMatrix.get(5).get(5), is(false));
        assertThat(adjacentMatrix.get(5).get(6), is(true));
        assertThat(adjacentMatrix.get(5).get(7), is(false));

        // V7
        assertThat(adjacentMatrix.get(6).get(0), is(false));
        assertThat(adjacentMatrix.get(6).get(1), is(false));
        assertThat(adjacentMatrix.get(6).get(2), is(true));
        assertThat(adjacentMatrix.get(6).get(3), is(false));
        assertThat(adjacentMatrix.get(6).get(4), is(false));
        assertThat(adjacentMatrix.get(6).get(5), is(true));
        assertThat(adjacentMatrix.get(6).get(6), is(false));
        assertThat(adjacentMatrix.get(6).get(7), is(true));

        // V8
        assertThat(adjacentMatrix.get(7).get(0), is(false));
        assertThat(adjacentMatrix.get(7).get(1), is(true));
        assertThat(adjacentMatrix.get(7).get(2), is(false));
        assertThat(adjacentMatrix.get(7).get(3), is(false));
        assertThat(adjacentMatrix.get(7).get(4), is(true));
        assertThat(adjacentMatrix.get(7).get(5), is(false));
        assertThat(adjacentMatrix.get(7).get(6), is(true));
        assertThat(adjacentMatrix.get(7).get(7), is(false));

        for (ArrayList<Boolean> i : adjacentMatrix) {
            System.out.println(i);
        }
    }
}