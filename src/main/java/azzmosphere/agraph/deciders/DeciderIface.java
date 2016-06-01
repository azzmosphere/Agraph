package azzmosphere.agraph.deciders;

import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.vertices.instincts.InstinctIface;

import java.util.LinkedHashSet;

/**
 * Created by aaron.spiteri on 31/05/2016.
 */
public interface DeciderIface {

    /**
     * Given a subgraph the ANN decides if it is part of the ANN part of the population for the objective it is trying
     * to achieve.
     *
     * @param instinct the instinct to determine if population matches.
     * @param testCircuit the circuit that is currently getting tested.
     * @return members of the subgraph that are considered part of the population
     */
    LinkedHashSet<SubgraphInterface> isPopulation(InstinctIface instinct, LinkedHashSet<SubgraphInterface> testCircuit);
}
