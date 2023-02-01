package AI;

import AI.Search.Frontier.FrontierQueue;
import AI.Search.SearchNode;
import AI.Search.TreeSearch;

public class BFS extends TreeSearch {
    public BFS() {
        super(new FrontierQueue());
    }

    @Override
    public String toString() {
        return "Breadth First Search";
    }

    @Override
    public boolean pruneNode(SearchNode node) {
        return false;
    }
}
