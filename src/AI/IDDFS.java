package AI;

import AI.Search.Solution;
import Game.State;

public class IDDFS extends DLDFS {
    public IDDFS() {
        super(0);
    }

    @Override
    public String toString() {
        return "Iterative Deepening Depth First Search";
    }

    @Override
    public Solution search(State startingState) {
        int depthLimit = 0;
        Solution solution;

        do {
            setDepthLimit(++depthLimit);

            solution = super.search(startingState.duplicate());
        } while(solution == null);

        return solution;
    }
}
