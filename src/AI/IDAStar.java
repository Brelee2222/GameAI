package AI;

import AI.Search.SearchNode;
import AI.Search.Solution;
import Game.State;

public class IDAStar extends AStar {
    int depthLimit;

    public IDAStar() {

    }

    @Override
    public String toString() {
        return "Iterative Deepening A*";
    }

    @Override
    public Solution search(State startingState) {
        depthLimit = 0;
        Solution solution;

        do {
            depthLimit++;
            solution = super.search(startingState.duplicate());
        } while(solution == null);

        return solution;
    }

    public boolean pruneNode(SearchNode node) {
        return node.getDepth() > depthLimit || super.pruneNode(node);
    }
}
