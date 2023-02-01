package AI.Search;

import Game.State;

public interface Search {
    Solution search(State startState);
    String toString();
}
