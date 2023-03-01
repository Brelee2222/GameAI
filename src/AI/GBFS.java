package AI;

import AI.Search.Frontier.FrontierPriorityQueue;
import AI.Search.HeuristicSearch;
import AI.Search.SearchNode;
import Game.State;

public class GBFS extends HeuristicSearch {
    public GBFS() {
        super(new FrontierPriorityQueue());
    }

    @Override
    public String toString() {
        return "Greedy-Breadth-First-Search";
    }

    @Override
    public int evalState(State state, SearchNode node) {
        return state.heuristic();
    }
}
