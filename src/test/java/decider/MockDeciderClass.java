package decider;

import azzmosphere.agraph.deciders.DeciderIface;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.vertices.instincts.InstinctIface;

import java.util.LinkedHashSet;

/**
 * Created by aaron.spiteri on 1/06/2016.
 *
 * For test cases mock out the isPopulation() routine to return the desired result for the test case.
 *
 */
public class MockDeciderClass implements DeciderIface {
    @Override
    public LinkedHashSet<SubgraphInterface> isPopulation(InstinctIface instinctIface, LinkedHashSet<SubgraphInterface> subgraph) {
        return subgraph;
    }
}
