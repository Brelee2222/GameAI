package AI.Search;

import AI.Search.Frontier.Frontier;
import Game.Action;
import Game.State;

import java.util.ArrayList;
import java.util.List;

// I copy and pasted TreeSearch
public abstract class HeuristicSearch implements Search {
    private final Frontier frontier;
    public HeuristicSearch(Frontier frontier) {
        this.frontier = frontier;
    }

    public boolean pruneNode(SearchNode node) {
//        if(node.getParentNode() != null) {
//            SearchNode grandparent = node.getParentNode().getParentNode();
//            return grandparent != null && node.getState().equals(grandparent.getState());
//        }
        return false;
    }

    @Override
    public Solution search(State startingState) {
        Frontier frontier = this.frontier;
        frontier.insert(new SearchNode(startingState, 0, null, evalState(startingState, null)));
        while(!frontier.isEmpty()) {
            SearchNode searchNode = frontier.removeNext();
            State state = searchNode.getState();

            if(state.isGoalState()) {
                frontier.clear();
                return getSolution(searchNode, startingState);
            }

            for(Action action : state.listActions()) {
                State childState = state.performAction(action);

                int depth = searchNode.getDepth()+1;

                SearchNode childNode = new SearchNode(childState, action, depth, searchNode, evalState(childState, searchNode));

                if(!pruneNode(childNode))
                    frontier.insert(childNode);
            }
        }
        return null;
    }

    // This is here because Search is an interface, and SearchNode isn't abstract
    public abstract int evalState(State state, SearchNode node);

    public Solution getSolution(SearchNode searchNode, State startingState) {
        List<SearchNode> path = new ArrayList<>();

        State finalState = searchNode.getState();

        while(searchNode != null) {
            path.add(0, searchNode);
            searchNode = searchNode.getParentNode();
        }

        return new Solution(startingState, finalState, path);
    }
}
