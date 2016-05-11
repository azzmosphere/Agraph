package azzmosphere.agraph;

/**
 * Single cooridinate.
 *
 * Created by aaron.spiteri on 10/05/2016.
 */
public class Coordinate implements Comparable<Coordinate> {
    int coordinate;

    public Coordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return new Integer(coordinate).toString();
    }

    public boolean isEqual(Coordinate coordinate) {
        return this.coordinate == coordinate.getCoordinate();
    }

    public int getCoordinate() {
        return coordinate;
    }

    @Override
    public int compareTo(Coordinate coordinate) {
        return getCoordinate() - coordinate.getCoordinate();
    }
}
