import AI.IDDFS;
import AI.Search.SearchNode;
import AI.Search.TreeSearch;
import Game.Action;
import Game.State;

import java.util.List;

public class Main {
    public static void main(String... args) {
        State puzzle = new TwoXTwoCube();

        for(int i = 0; i != 11; i++) {
            List<Action> actions = puzzle.listActions();

            Action action = actions.get((int) (Math.random() * actions.size()));
            System.out.println(i + ". " + action.toString());
            puzzle.performAction(action);
        }

        System.out.println("scrambled");

        TreeSearch search = new AI.DLDFS(11);
//        System.out.println(search.search(puzzle));

        for(SearchNode node : search.search(puzzle).path)
            if(node.getLastAction() != null)
                System.out.println(node.getLastAction().toString() + " " + node.getState().isGoalState());
    }
}
