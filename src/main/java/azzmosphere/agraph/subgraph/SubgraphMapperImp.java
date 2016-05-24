package azzmosphere.agraph.subgraph;

import azzmosphere.agraph.tranverser.PolyhedronDFS;

/**
 *
 * Created by aaron.spiteri on 19/05/2016.
 *
 * Maps subgraph types to the search types.
 */
public class SubgraphMapperImp implements SubgraphMapperInterface {

    private enum SubgraphTypes implements SubgraphMapperInterface {
        REGULAR_POLYHEDRON() {
            @Override
            public SubgraphInterface getSubGraphObject(String transverserClassName) {
                return new SimpleCycle();
            }

            @Override
            public String toString() {
                return PolyhedronDFS.class.getCanonicalName();
            }
        },

        POLYHEDRON() {
            @Override
            public SubgraphInterface getSubGraphObject(String transverserClassName) {
                return new SimpleCycle();
            }

            @Override
            public String toString() {
                return PolyhedronDFS.class.getCanonicalName();
            }
        };
    }

    @Override
    public SubgraphInterface getSubGraphObject(String transverserClassName) {
        for (SubgraphMapperInterface mapper : SubgraphTypes.values()) {
            if (mapper.toString().equals(transverserClassName)) {
                return mapper.getSubGraphObject(transverserClassName);
            }
        }
        return null;
    }
}
