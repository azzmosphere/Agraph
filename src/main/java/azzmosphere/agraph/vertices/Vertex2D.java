package azzmosphere.agraph.vertices;

import azzmosphere.agraph.Coordinate;

/**
 * Created by aaron.spiteri on 15/05/2016.
 */
public abstract class Vertex2D<Y> extends Vertex<Y>  {
    private Coordinate x;
    private Coordinate y;

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

    @Override
    public void setCoordinates(Coordinate[] coordinates) {
        setX(coordinates[0]);
        setY(coordinates[1]);
    }

    @Override
    public String toString() {
        return getLabel() + "(" + x + "," + y + ")";
    }

    @Override
    public Coordinate[] getCoordinates() {
        return new Coordinate[] {x, y};
    }
}
