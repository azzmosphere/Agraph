package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.LinkedHashSet;

/**
 *
 * Interface to describe behaviour for finding subgraphs.
 *
 * Created by aaron.spiteri on 16/05/2016.
 */
public interface TransverserInterface extends TransverserBase {
    LinkedHashSet<SubgraphInterface> findAllSubgraphs() throws Exception;

    boolean isBalanced();

    LinkedHashSet<SubgraphInterface> findAllSubgraphs(VertexInterface v) throws Exception;
}
