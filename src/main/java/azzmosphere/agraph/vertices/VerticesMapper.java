package azzmosphere.agraph.vertices;

import azzmosphere.agraph.Vertex;
import azzmosphere.agraph.datastructures.UltraSonicSensor;

import javax.sound.sampled.AudioInputStream;

/**
 * Created by aaron.spiteri on 11/05/2016.
 *
 * Mapping class  for Vertices
 */
public enum VerticesMapper implements  VerticesMapperInterface {

    /* Data delivered from a Ultra Sonic Sensor */
    ULTRA_SONIC_SENSOR_CNAME {
        @Override
        public String toString() {
            return UltraSonicSensor.class.getName();
        }

        @Override
        public Vertex getVertex(Object data) {
            UltraSonicSensorVertex v = new UltraSonicSensorVertex();
            v.setData((UltraSonicSensor) data);
            return v;
        }
    },

    /* Audio stream */
    AUDIO_INPUT_STREAM_CNAME{
        @Override
        public String toString() {
            return AudioInputStream.class.getName();
        }

        @Override
        public Vertex getVertex(Object data) {
            SoundVertex v = new SoundVertex();

            v.setData((AudioInputStream) data);
            return v;
        }
    };

}
