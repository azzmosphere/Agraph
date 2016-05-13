import azzmosphere.agraph.datastructures.UltraSonicSensor;
import azzmosphere.agraph.plane.PlannerGraph;
import azzmosphere.agraph.vertices.Vertex;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by aaron.spiteri on 12/05/2016.
 */
public class TestAdjacencyMatrix {
    @Test
    public void shouldCreateCorrectAdjencyMatrix() throws Exception {
        PlannerGraph pg = new PlannerGraph();
        UltraSonicSensor data = new UltraSonicSensor();

        Vertex v1 = pg.attachVertex(data, 1, 1);
        Vertex v2 = pg.attachVertex(data, 4, 1);
        Vertex v3 = pg.attachVertex(data, 4, 4);
        Vertex v4 = pg.attachVertex(data, 1, 4);

        pg.createEdge(v1, v2); //e1
        pg.createEdge(v2, v3); //e2
        pg.createEdge(v3, v1); //e3
        pg.createEdge(v3, v4); //e4
        pg.createEdge(v4, v1); //e5

        ArrayList<ArrayList<Boolean>> adjacentMatrix = pg.getAdjacencyMatrix();




        for (ArrayList<Boolean> i : adjacentMatrix) {
            System.out.println(i);
        }

        //long x = 0x1 << 4 - 1;
        //System.out.println("x = " + x);
    }
}
