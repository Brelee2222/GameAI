package AI;

import AI.Search.Frontier.FrontierPriorityQueue;
import AI.Search.HeuristicSearch;
import AI.Search.SearchNode;
import Game.State;

public class UCS extends HeuristicSearch {

    public UCS() {
        super(new FrontierPriorityQueue());
    }

    @Override
    public String toString() {
        return "Uniform Cost Search";
    }

    @Override
    public int evalState(State state, SearchNode node) {
        return node.getPathCost();
    }
}
