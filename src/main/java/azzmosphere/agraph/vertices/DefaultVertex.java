package azzmosphere.agraph.vertices;

/**
 * Created by aaron.spiteri on 15/05/2016.
 */
public class DefaultVertex extends Vertex3D<Object> {
    private Object data;

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }
}
