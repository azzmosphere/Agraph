package azzmosphere.agraph.vertices;


/**
 * Created by aaron.spiteri on 11/05/2016.
 *
 * Mapping class  for Vertices
 */
public class VerticesMapperImpl implements VerticesMapperInterface {
    public enum VerticesMapper implements VerticesMapperInterface {

        /* default object */
        DEFAULT_OBJECT_CNAME {
            @Override
            public String toString() {
                return Object.class.getCanonicalName();
            }

            @Override
            public VertexInterface getVertex(Object data) {
                DefaultVertex v = new DefaultVertex();
                v.setData(data);
                return v;
            }
        };

    }

    private VerticesMapper map;

    @Override
    public VertexInterface getVertex(Object o) {
        VertexInterface v = null;
        for (VerticesMapper m : VerticesMapper.values()) {
            if (m.toString().equals(o.getClass().getCanonicalName())) {
                v = m.getVertex(o);
            }
        }
        return v;
    }

}
