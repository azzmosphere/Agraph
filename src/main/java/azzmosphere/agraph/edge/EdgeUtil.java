package azzmosphere.agraph.edge;

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

        return acosToDegress(((b * b) + (c * c) - (a * a)) / (2 * b * c));
    }

    public static double computeAngle(Edge e1, Edge e2) {

        return computeAngle(e1.getLength(), e2.getLength());
    }

    public static double acosToDegress(double x) {
        return Math.acos(x) * (180 / Math.PI);
    }

    public static double pythagoreanTheorem(double a, double b) {
        return Math.sqrt((a * a) + (b * b));
    }
}
