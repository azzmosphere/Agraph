package vertices.instinct;

import azzmosphere.agraph.vertices.instincts.Instinct;

/**
 * Created by aaron.spiteri on 1/06/2016.
 *
 * Creates a instinct for testing purposes.
 */
public class TestClassForInstinct<TestClassForInstinct> extends Instinct {

    Double data;

    public TestClassForInstinct() {
        setWarningLevel(0.5);
        setCriticalLevel(0.1);
        setFatal(0.05);
    }

    @Override
    public boolean isEqual(Object object) {
        return false;
    }

    @Override
    public Double getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        data = (Double) data;
    }
}
