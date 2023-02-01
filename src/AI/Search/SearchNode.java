package AI.Search;

import Game.Action;
import Game.State;

public class SearchNode {
    private final State state;
    private final Action action;
    private final int pathCost;
    private final int depth;
    private final SearchNode parentNode;

    public SearchNode(State state, Action action, int depth, SearchNode parentNode) {
        this.depth = depth;
        this.state = state;
        this.action = action;
        this.parentNode = parentNode;
        this.pathCost = action == null ? 0 : action.getCost();
    }

    public State getState() {
        return state;
    }

    public SearchNode getParentNode() {
        return parentNode;
    }

    public Action getLastAction() {
        return action;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getDepth() {
        return depth;
    }
}
