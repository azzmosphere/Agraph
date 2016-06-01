package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.deciders.DeciderIface;
import azzmosphere.agraph.plane.GraphUtils;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.vertices.VertexInterface;
import azzmosphere.agraph.vertices.instincts.InstinctIface;
import org.apache.commons.lang.mutable.MutableInt;

import java.util.LinkedHashSet;

/**
 * Created by aaron.spiteri on 31/05/2016.
 *
 *
 * For a given a vertex return all sub graphs relating to that vertex. It can work on feed forward and
 * feed back operations.
 *
 */
public class VerticeSearchDFSImp extends TransverserAbstractBase implements VerticeSearchIface {

    private DeciderIface decider;
    private TransverserInterface transverser;

    public VerticeSearchDFSImp(DeciderIface decider) {
        this.decider = decider;
    }

    @Override
    public LinkedHashSet<SubgraphInterface> getPopulation(InstinctIface instinct) throws Exception {
        LinkedHashSet<SubgraphInterface> found = new LinkedHashSet<>();
        if (vertices.size() <= 0) {
            return null;
        }

        VertexInterface v = vertices.get(0);
        int startNode = v.getId();
        MutableInt verticesTransversed = new MutableInt(GraphUtils.markVertex(startNode, 0));

        // Check the first vertex. If that is a match then put it.
        LinkedHashSet<SubgraphInterface> matched = decider.isPopulation(instinct, transverser.findAllSubgraphs(v));
        if (matched != null) {
            found.addAll(matched);
        }

        for (int nodeId : GraphUtils.getAdjacentVertices(startNode, adjacencyMatrix)) {
            dfs(found, nodeId, startNode, verticesTransversed, instinct);
        }

        return found;
    }

    @Override
    public void setDecider(DeciderIface decider) {
        this.decider = decider;
    }

    @Override
    public DeciderIface getDecider() {
        return decider;
    }

    @Override
    public void setTransverser(TransverserInterface transverser) {
        this.transverser = transverser;
    }

    @Override
    public TransverserInterface getTrasverser() {
        return transverser;
    }

    /**
     *
     * Deprated method see getPopulation()
     *
     * @return all subgraphs
     * @throws Exception any
     * @deprecated This routine should not be used for population searching as it does not link to a instinct. It is
     * here at the moment for compliance with the interface but will be removed.
     */
    @Deprecated
    @Override
    public LinkedHashSet<SubgraphInterface> findAllSubgraphs() throws Exception {
        LinkedHashSet<SubgraphInterface> matched = new LinkedHashSet<>();
        return matched;
    }

    /*
     * Transverse each path until last Node is reached using the DFS algorithm.
     */
    private void dfs(LinkedHashSet<SubgraphInterface> found,
                      int currentNode,
                      int lastNode,
                      MutableInt verticesTransversed,
                      InstinctIface instinct) throws Exception {

        if (currentNode == lastNode || GraphUtils.isMarked(currentNode, verticesTransversed.toInteger())) {
            return;
        }
        verticesTransversed.setValue(GraphUtils.markVertex(currentNode, verticesTransversed.toInteger()));

        LinkedHashSet<SubgraphInterface> matched = decider.isPopulation(instinct, transverser.findAllSubgraphs(vertices.get(currentNode)));
        if (matched != null) {
            found.addAll(matched);
        }

        for (int nodeId : GraphUtils.getAdjacentVertices(currentNode, adjacencyMatrix)) {
            dfs(found, nodeId, lastNode, verticesTransversed, instinct);
        }

    }
}
