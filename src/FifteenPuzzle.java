import Game.Action;
import Game.State;

import java.util.ArrayList;
import java.util.List;

public class FifteenPuzzle implements State {
    private long tiles;
    private int emptyPos;

    public FifteenPuzzle(long tiles, int emptyPos) {
        this.tiles = tiles;
        this.emptyPos = emptyPos;
    }

    public FifteenPuzzle() {
        tiles = 0x0123456789abcdefL;
        emptyPos = 0xf;
    }

    public void swap(int pos) {
        long number = (tiles >> (pos << 2)) & 0xfL;
        tiles |= number << (emptyPos << 2);
        tiles ^= number << (pos << 2);
        emptyPos = pos;
    }

    @Override
    public int heuristic() {
        return 0;
    }

    @Override
    public List<Action> listActions() {
        ArrayList<Action> actions = new ArrayList<>();
        if((emptyPos & 0b11) != 0b11) {
            actions.add(TileAction.RIGHT);
        }

        if((emptyPos & 0b11) != 0b00) {
            actions.add(TileAction.LEFT);
        }

        if((emptyPos & 0b1100) != 0b1100) {
            actions.add(TileAction.DOWN);
        }

        if((emptyPos & 0b1100) != 0b0000) {
            actions.add(TileAction.UP);
        }

        return actions;
    }

    @Override
    public boolean isGoalState() {
        return tiles == 0x0123456789abcdefL;
    }

    @Override
    public void display() {
        long tiles = this.tiles;

        String board = "";

        for(int row = 0; row != 4; row++) {
            for(int column = 0; column != 4; column++) {
                board += Integer.toHexString((int) (tiles & 0xfL)) + " ";

                tiles >>= 4;
            }
            board += "\n";
        }

        System.out.println(board);
    }

    @Override
    public FifteenPuzzle duplicate() {
        return new FifteenPuzzle(tiles, emptyPos);
    }

    @Override
    public void performAction(Action action) {
        swap(((TileAction) action).getSwapPos(emptyPos));
    }

    @Override
    public boolean equals(Object otherState) {
        if(otherState.getClass() != FifteenPuzzle.class)
            return false;

        return ((FifteenPuzzle) otherState).tiles == tiles;
    }

    public enum TileAction implements Action {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        public int getSwapPos(int emptyPos) {
            return switch (this) {
                case LEFT -> emptyPos - 0b0001;
                case RIGHT -> emptyPos + 0b0001;
                case DOWN -> emptyPos + 0b0100;
                case UP -> emptyPos - 0b0100;
            };
        }

        @Override
        public void display() {

        }

        @Override
        public int getCost() {
            return 1;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }
}