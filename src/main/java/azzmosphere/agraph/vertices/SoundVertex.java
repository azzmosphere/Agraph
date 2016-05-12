package azzmosphere.agraph.vertices;

import javax.sound.sampled.AudioInputStream;

/**
 * Created by aaron.spiteri on 11/05/2016.
 */
public class SoundVertex extends Vertex<AudioInputStream> {
    private AudioInputStream data;

    @Override
    public void setData(AudioInputStream data) {
        this.data = data;
    }

    @Override
    public String getDataClass() {
        return AudioInputStream.class.getName();
    }

    @Override
    public AudioInputStream getData() {
        return data;
    }
}
