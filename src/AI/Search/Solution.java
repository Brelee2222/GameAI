package AI.Search;

import Game.State;

import java.util.List;

public class Solution {
    public final State startState, finalState;
    public final List<SearchNode> path;

    public Solution (State startState, State finalState, List<SearchNode> path) {
        this.startState = startState;
        this.finalState = finalState;
        this.path = path;
    }
}
