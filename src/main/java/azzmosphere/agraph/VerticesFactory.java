package azzmosphere.agraph;

import azzmosphere.agraph.vertices.VerticesMapper;

/**
 * Creates various types of Vertices.
 *
 * Created by aaron.spiteri on 11/05/2016.
 */
public final class VerticesFactory {


    public static Vertex createVertex(int id, Object o, int x, int y) throws Exception {
        Vertex v;
        try {
            v = VerticesMapper.valueOf(o.getClass().getName()).getVertex(o);
            v.setId(id);
            v.setX(new Coordinate(x));
            v.setY(new Coordinate(y));
        }
        catch (Exception e) {
            throw new Exception(Object.class.getName() + "is not supported");
        }
        return v;
    }

    public static Vertex createVertex(int id, Object o, int x, int y, String label) throws Exception {
        Vertex v = createVertex(id, o, x, y);
        v.setLabel(label);
        return v;
    }
}
