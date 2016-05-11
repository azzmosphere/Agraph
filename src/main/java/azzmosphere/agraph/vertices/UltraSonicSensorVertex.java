package azzmosphere.agraph.vertices;

import azzmosphere.agraph.Vertex;
import azzmosphere.agraph.datastructures.UltraSonicSensor;

/**
 * Node representation of UltraSonicSensorVertex.
 *
 * Created by aaron.spiteri on 11/05/2016.
 */
public class UltraSonicSensorVertex extends Vertex<UltraSonicSensor> {
    private UltraSonicSensor data;

    @Override
    public UltraSonicSensor getData() {
        return data;
    }

    @Override
    public void setData(UltraSonicSensor data) {
        this.data = data;
    }

    @Override
    public String getDataClass() {
        return UltraSonicSensor.class.getName();
    }
}
