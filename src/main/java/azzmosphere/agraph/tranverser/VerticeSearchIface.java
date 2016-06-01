package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.deciders.DeciderIface;
import azzmosphere.agraph.subgraph.SubgraphInterface;
import azzmosphere.agraph.vertices.instincts.InstinctIface;

import java.util.LinkedHashSet;

/**
 * Created by aaron.spiteri on 31/05/2016.
 *
 * Allows for a Gentics Algorithms (GA) to decide if a vertex is a match to another. Two factors are used to decide
 * this the Probabilities Crossover and the Probabilities Mutation.
 *
 * The GA is responsible for creating, manipulating and extending the graph.  This interface allows for transversal
 * of the network and allows for the network to be build.
 *
 * The classification and problem solving is done by something that can understand vertices, such as a
 * https://en.wikipedia.org/wiki/Naive_Bayes_classifier
 *
 * At this level the algorithm is not concerned with what is going on AI wise, it uses a depth first search to retrieve
 * matching vertices.
 *
 */
public interface VerticeSearchIface extends TransverserBase {

    /**
     * The decider is a non deterministic algorithm that is used for population selection and possibly to solve the
     * objective of the algorithm.
     *
     * @param decider algorithm
     */
    void setDecider(DeciderIface decider);

    DeciderIface getDecider();

    /**
     * The transverser is used to retrive subgraphs in a deterministic manner, this is based on the assumption that
     * the decider has not manipulated the graph,
     *
     * @param transverser to use
     */
    void setTransverser(TransverserInterface transverser);

    TransverserInterface getTrasverser();

    /**
     * Retruns the population for the ANN for a given instinct (problem) that needs to be solved.
     *
     * @param instinct - the problem that needs to be solved
     * @return population vertices in the form of subgraphs
     */
    LinkedHashSet<SubgraphInterface> getPopulation(InstinctIface instinct) throws Exception;
}
