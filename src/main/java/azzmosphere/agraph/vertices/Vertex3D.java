package azzmosphere.agraph.vertices;

import azzmosphere.agraph.Coordinate;

/**
 * Representation of a Cartesian using standard x,y,z coordinates.
 *
 * Created by aaron.spiteri on 15/05/2016.
 */
public abstract class Vertex3D<Y> extends  Vertex<Y> {
    private Coordinate x;
    private Coordinate y;
    private Coordinate z;

    public Coordinate getX() {
        return x;
    }

    public void setX(Coordinate x) {
        this.x = x;
    }

    public Coordinate getY() {
        return y;
    }

    public void setY(Coordinate y) {
        this.y = y;
    }

    public Coordinate getZ() {
        return z;
    }

    public void setZ(Coordinate z) {
        this.z = z;
    }

    @Override
    public void setCoordinates(Coordinate[] coordinates) {
        setX(coordinates[0]);
        setY(coordinates[1]);
        setZ(coordinates[2]);
    }

    @Override
    public String toString() {
        return getLabel() + "(" + x + "," + y + "," + z + ")";
    }

    @Override
    public Coordinate[] getCoordinates() {
        return new Coordinate[] {x, y, z};
    }
}
