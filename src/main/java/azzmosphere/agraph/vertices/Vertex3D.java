package azzmosphere.agraph.vertices;

import azzmosphere.agraph.Coordinate;

/**
 * Representation of a Cartesian using standard x,y,z coordinates.
 *
 * Created by aaron.spiteri on 15/05/2016.
 */
public class Vertex3D {
    private Coordinate z;

    public Coordinate getZ() {
        return z;
    }

    public void setZ(Coordinate z) {
        this.z = z;
    }
}
