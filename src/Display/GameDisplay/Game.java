package Display.GameDisplay;

import Game.State;

@FunctionalInterface
public interface Game {
    State makeGame();
}
