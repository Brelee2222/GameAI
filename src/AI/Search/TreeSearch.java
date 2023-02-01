package AI.Search;

import AI.Search.Frontier.Frontier;
import Game.Action;
import Game.State;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeSearch implements Search {

    private final Frontier frontier;

    public TreeSearch(Frontier frontier) {
        this.frontier = frontier;
    }

    public abstract boolean pruneNode(SearchNode node);

    public Solution search(State startingState) {
        Frontier frontier = this.frontier;
        SearchNode searchNode = new SearchNode(startingState, null, 0, null);
        for(int depth = 1; !searchNode.getState().isGoalState(); searchNode = frontier.removeNext()) {
            State state = searchNode.getState();

            for(Action action : state.listActions()) {
                State childState = state.duplicate();
                childState.performAction(action);

                SearchNode childNode = new SearchNode(childState, action, depth, searchNode);
                if(!pruneNode(childNode)) {
                    frontier.insert(childNode);
                }
            }

            if(frontier.isEmpty())
                return null;
        }

//        System.out.println("done");

        List<SearchNode> path = new ArrayList<>();

        State finalState = searchNode.getState();

        while(searchNode != null) {
            path.add(0, searchNode);
            searchNode = searchNode.getParentNode();
        }

        return new Solution(startingState, finalState, path);
    }
}
