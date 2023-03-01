package AI;

import AI.Search.Frontier.Frontier;
import AI.Search.Frontier.FrontierPriorityQueue;
import AI.Search.HeuristicSearch;
import AI.Search.SearchNode;
import Game.State;

public class AStar extends HeuristicSearch {
    public AStar() {
        super(new FrontierPriorityQueue());
    }

    @Override
    public int evalState(State state, SearchNode node) {
        return node == null ? state.heuristic() : state.heuristic() + node.getPathCost();
    }
}
