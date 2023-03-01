package AI;

import AI.Search.Frontier.FrontierStack;
import AI.Search.SearchNode;
import AI.Search.TreeSearch;

public class DFS extends TreeSearch {
    public DFS() {
        super(new FrontierStack());
    }

    @Override
    public String toString() {
        return "Depth First Search";
    }
}
