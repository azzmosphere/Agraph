package azzmosphere.agraph.vertices.instincts;

import azzmosphere.agraph.vertices.Vertex2D;

/**
 * Created by aaron.spiteri on 1/06/2016.
 *
 * Instincts are input variables that are a setup from a interrupt from the OS to tell the ANN that there is a
 * situation that it must deal with.  This could be something such as low power.  The instinct is then formulated into
 * a circuit and fed back through the ANN in the hope that a solution can be reached.
 *
 * The solution is reached once the threshold level reaches below.
 *
 * The thresholds are set as follows:
 *
 *   fatal: permanent damage has occurred.
 *   critical: value between 0 to 1 that means that the system has reached a point were permanent damage is imminent.
 *   warning: A solution should be sort.
 *   acceptable: In acceptable threshold, this value is implicit
 *
 * Instincts are managed by central process and are fed to the ANN as a circuit.
 *
 */
public abstract class Instinct extends Vertex2D implements  InstinctIface {
    protected double stressLevel;  // Value between 0 to 1 where 1 is always fatal.
    protected double criticalLevel;
    protected double warningLevel;
    protected double fatal;

    protected void setCriticalLevel(double level) {
        this.warningLevel = level;
    }

    protected void setWarningLevel(double level) {
        this.criticalLevel = level;
    }

    protected void setFatal(double fatal) {
        this.fatal = fatal;
    }

    @Override
    public boolean isCritical() {
        if (stressLevel > criticalLevel) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isWarning() {
        if (stressLevel > warningLevel) {
            return true;
        }
        return false;
    }

    @Override
    public double getStressLevel() {
        return stressLevel;
    }

    @Override
    public boolean isFatal() {
        if (stressLevel > fatal) {
            return true;
        }
        return false;
    }

    @Override
    public void setStressLevel(double stressLevel) {
        this.stressLevel = stressLevel;
    }

    @Override
    public boolean isAcceptable() {
        if (isWarning()) {
            return false;
        }
        return true;
    }
}
