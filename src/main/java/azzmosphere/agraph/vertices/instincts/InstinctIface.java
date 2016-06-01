package azzmosphere.agraph.vertices.instincts;

/**
 * Created by aaron.spiteri on 1/06/2016.
 */
public interface InstinctIface {
    boolean isCritical();
    boolean isWarning();
    boolean isFatal();
    boolean isAcceptable();
    double getStressLevel();
    void setStressLevel(double stressLevel);
}
