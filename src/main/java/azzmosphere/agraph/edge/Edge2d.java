package azzmosphere.agraph.edge;

import azzmosphere.agraph.Coordinate;

/**
 * Created by aaron.spiteri on 30/05/2016.
 */
public class Edge2d extends EdgeAbstractBase implements Edge {
    /**
     * set the axis that v1 and v2 are to be joined on,
     * this will also calculate the length of the edge.
     *
     * @param joiningAxis
     */
    @Override
    public void setJoiningAxis(Axis joiningAxis) {
        this.joiningAxis = joiningAxis;
        Coordinate[] coordinatesV1 = getHead().getCoordinates();
        Coordinate[] coordinatesV2 = getTail().getCoordinates();


        double[] coordinates = new double[2];
        for (int i = 0; i < 2; i++) {
            coordinates[i] = coordinatesV1[i].getCoordinate() - coordinatesV2[i].getCoordinate();

            if (coordinates[i] < 0) {
                coordinates[i] = coordinates[i] * -1;
            }
        }

        this.length = EdgeUtil.computeEdgeSize(coordinates[0], coordinates[1]);
    }
}
