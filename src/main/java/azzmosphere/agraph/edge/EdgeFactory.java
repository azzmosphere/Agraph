package azzmosphere.agraph.edge;

import azzmosphere.agraph.vertices.Vertex3D;
import azzmosphere.agraph.vertices.Vertex2D;
import azzmosphere.agraph.vertices.VertexInterface;

/**
 * Create a edge between two nodes.
 *
 * Created by aaron.spiteri on 12/05/2016.
 */
public class EdgeFactory {

    private interface EdgeMapperIface {
        Edge getEdge();
        boolean isEdge(VertexInterface v);
    }

    private enum EdgeMapper implements EdgeMapperIface {
        EDGE_3D {

            @Override
            public Edge getEdge() {
                return new Edge3d();
            }

            @Override
            public boolean isEdge(VertexInterface v) {
                if (Vertex3D.class.isInstance(v)) {
                    return true;
                }
                return false;
            }
        },

        EDGE_2D {
            @Override
            public Edge getEdge() {
                return new Edge2d();
            }

            @Override
            public boolean isEdge(VertexInterface v) {
                if (Vertex2D.class.isInstance(v)) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Edge createEdge(VertexInterface v1, VertexInterface v2) {
        Edge e = null;

        for (EdgeMapper mapper : EdgeMapper.values()) {
            if (mapper.isEdge(v1)) {
                e = mapper.getEdge();
            }
        }

        e.setTail(v1);
        e.setHead(v2);
        v1.getEdges().add(e);

        return e;
    }
}
