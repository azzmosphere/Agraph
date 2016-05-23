package azzmosphere.agraph.vertices;

import azzmosphere.agraph.Coordinate;

/**
 * Creates various types of Vertices.
 *
 * Created by aaron.spiteri on 11/05/2016.
 */
public final class VerticesFactory {

    private VerticesMapperInterface mapper;

    public VerticesFactory(VerticesMapperInterface mapper) {
        this.mapper = mapper;
    }

    public VertexInterface createVertex(Object o, int[] coordinates) throws Exception {
        VertexInterface v = mapper.getVertex(o);
        Coordinate[] coordinatesOut = new Coordinate[coordinates.length];

        for (int i = 0; i < coordinates.length; i++) {
            coordinatesOut[i] = new Coordinate(coordinates[i]);
        }

        v.setCoordinates(coordinatesOut);
        return v;
    }

    public VertexInterface createVertex(int id, Object o, int[] coordinates, String label) throws Exception {
        VertexInterface v = createVertex(id, o, coordinates);

        v.setLabel(label);
        return v;
    }

    public VertexInterface createVertex(int id, Object o, int[] coordinates) throws Exception {
        VertexInterface v = createVertex(o, coordinates);
        v.setId(id);
        return v;
    }
}
