import Game.Action;
import Game.State;

import java.util.ArrayList;
import java.util.List;

public class TwoXTwoCube implements State {
    private long pieces;
    private static final long goalState = 0b11000_10100_10000_01100_01000_00100_00000L; // Only 7 pieces are needed

//    private static HashMap<Long, TwoXTwoCube> visitedStates = new HashMap<>();

    /**
        bit1 is top or bottom layer
        bit2 is depth
        bit3 is right or left

        bits 4 and 5 are for axis (0-2)
     */

    public TwoXTwoCube(long pieces) {
        this.pieces = pieces;
    }

    public TwoXTwoCube() {
        this.pieces = goalState;
    }

    public static long getGoalState() {
        return goalState;
    }

    public void rotateSide(byte axis, boolean prime) {
        int bit1 = 1 << ((axis + (prime ? 1 : 2)) % 3);
        int bit2 = 1 << ((axis + (prime ? 2 : 1)) % 3);

        int tempPiece = rotate(axis, getPiece(0));

        rotateSet(0, bit1, axis);
        rotateSet(bit1, bit1 ^ bit2, axis);
        rotateSet(bit1 ^ bit2, bit2, axis);
        setPiece(bit2, tempPiece);
    }

    private int getPiece(int index) {
        return (int) (pieces >> (index * 5)) & 0b11111;
    }

    private void setPiece(int index, int piece) {
        int displacement = index * 5;

        pieces = (pieces & ~(0b11111L << displacement)) | ((long) piece << displacement);
    }

    private int rotate(byte axis, int piece) {
        // If the white/yellow face aligns with its axis, no rotation is made

        if((piece & 0b11) == axis)
            return piece;

        // switches what axis the piece aligns with
        return piece ^ axis ^ 0b11;
    }

    private void rotateSet(int setIndex, int getIndex, byte axis) {
        setPiece(setIndex, rotate(axis, getPiece(getIndex)));
    }

    @Override
    public boolean equals(Object otherState) {
        if(otherState.getClass() != TwoXTwoCube.class)
            return false;

        return ((TwoXTwoCube) otherState).pieces == pieces;
    }

    @Override
    public List<Action> listActions() {
        return new ArrayList<>(List.of(TurnAction.values()));
    }

    @Override
    public boolean isGoalState() {
        return pieces == goalState;
    }

    @Override
    public void display() {

    }

    @Override
    public State duplicate() {
        return new TwoXTwoCube(pieces);
    }

    @Override
    public State performAction(Action action) {
        TwoXTwoCube cube = new TwoXTwoCube(pieces);
        TurnAction turn = (TurnAction) action;
        cube.rotateSide(turn.axis, turn.prime);
        return cube;
//        if(!visitedStates.containsKey(this.pieces))
//            visitedStates.put(this.pieces, this);
    }

    @Override
    public int heuristic() {
        return 0;
    }

    public enum TurnAction implements Action {
        // _ signifies prime

        U(0, false),
        R(1, false),
        F(2, false),
        U_(0, true),
        R_(1, true),
        F_(2, true);



        private final byte axis;
        private final boolean prime;

        TurnAction(int axis, boolean prime) {
            this.axis = (byte) axis;
            this.prime = prime;
        }

        @Override
        public void display() {

        }

        @Override
        public int getCost() {
            return 1;
        }
    }
}
