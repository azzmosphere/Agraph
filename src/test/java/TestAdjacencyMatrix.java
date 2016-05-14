import azzmosphere.agraph.datastructures.UltraSonicSensor;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.vertices.Vertex;
import org.junit.Test;
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
        PlannerGraph pg = new PlannerGraph();
        UltraSonicSensor data = new UltraSonicSensor();

        Vertex v1 = pg.attachVertex(data, 1, 1);
        Vertex v2 = pg.attachVertex(data, 4, 1);
        Vertex v3 = pg.attachVertex(data, 4, 4);
        Vertex v4 = pg.attachVertex(data, 1, 4);

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

    }
}
