package azzmosphere.agraph.edge;

import azzmosphere.agraph.vertices.VertexInterface;

/**
 *
 * Utility class to compute the angles for a connected edges.
 *
 * Created by aaron.spiteri on 22/05/2016.
 */
public class EdgeUtil {

    /**
     * compute the angle between two endges.
     *
     * @param a adjacent length
     * @param b opposite length
     * @return angle in degrees
     */
    public static double computeAngle(double a, double b) {
        double c = pythagoreanTheorem(a, b);
        return computeAngle(a, b, c);
    }

    public static double computeAngle(double x, double y, double z) {
        return acosToDegress((sqr(x) + sqr(z) - sqr(y)) / (2 * x * z));
    }

    public static double sqr(double x) {
        return x * x;
    }

    public static double computeAngle(Edge e1, Edge e2) {

        return computeAngle(e1.getLength(), computeImaginaryEdgeLength(e1, e2), e2.getLength());
    }

    public static double acosToDegress(double x) {
        return Math.acos(x) * (180 / Math.PI);
    }

    public static double pythagoreanTheorem(double a, double b) {
        return Math.sqrt((a * a) + (b * b));
    }

    /**
     * compute the size of the edge in three dimensional space, formula retrieved form
     * http://www.intmath.com/vectors/6-3-dimensional-space.php
     *
     * @param x coordinate size
     * @param y coordiante size
     * @param z coordinate size
     * @return length
     */
    public static double computeEdgeSize(double x, double y, double z) {
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    /**
     * Create a imaginary edge between two vertices so that the length can be calculated.
     *
     * @param e1 edge1
     * @param e2 edge2
     * @return length of imaginary edge
     */
    public static double computeImaginaryEdgeLength(Edge e1, Edge e2) {
        VertexInterface joiningVertex = null;

        if (e1.getHead().isEqual(e2.getTail())) {
            joiningVertex = e1.getHead();
        }
        else if (e1.getTail().isEqual(e2.getTail())) {
            joiningVertex = e1.getTail();
        }
        else if (e1.getHead().isEqual(e2.getHead())) {
            joiningVertex = e1.getHead();
        }
        else {
            joiningVertex = e1.getTail();
        }

        VertexInterface v1 = findOppositeVertex(e1, joiningVertex);
        VertexInterface v2 = findOppositeVertex(e2, joiningVertex);

        Edge e = EdgeFactory.createEdge(v1, v2);
        e.setJoiningAxis(e2.getJoiningAxis());
        return e.getLength();

    }

    public static VertexInterface findOppositeVertex(Edge e, VertexInterface v) {
        if (e.getHead().isEqual(v)) {
            return e.getTail();
        }

        return e.getHead();
    }

}
