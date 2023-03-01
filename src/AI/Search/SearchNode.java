package AI.Search;

import Game.Action;
import Game.State;

public class SearchNode {
    private final State state;
    private final int pathCost;
    private final int depth;
    private final int eval;

    private SearchNode parentNode;

    public SearchNode(State state, Action action, int depth, SearchNode parentNode, int eval) {
        this.depth = depth;
        this.state = state;
        this.parentNode = parentNode;
        this.pathCost = action.getCost();
        this.eval = eval;
    }

    public SearchNode(State state, int depth, SearchNode parentNode, int eval) {
        this.depth = depth;
        this.state = state;
        this.parentNode = parentNode;
        this.pathCost = 0;
        this.eval = eval;
    }

    public int eval() {
        return eval;
    }

    public State getState() {
        return state;
    }

    public SearchNode getParentNode() {
        return parentNode;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getDepth() {
        return depth;
    }
}
