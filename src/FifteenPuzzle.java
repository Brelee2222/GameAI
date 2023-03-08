import Game.Action;
import Game.State;

import java.util.*;


public class FifteenPuzzle implements State {
    private final long tiles;
    private final int emptyPos;

    private static final long goalState = 0x0123456789abcdefL;

    public FifteenPuzzle(long tiles, int emptyPos) {//ubwgtodiffwe
        this.tiles = tiles;
        this.emptyPos = emptyPos;
    }

    public FifteenPuzzle(long tiles) {
        this.tiles = tiles;

        int emptyPos = 0;
        while((tiles & 0xf) != 0) {
            tiles >>= 4;
            emptyPos++;
        }
        this.emptyPos = emptyPos;
    }

    public FifteenPuzzle() {
        tiles = goalState;
        emptyPos = 0xf;
    }

    public long swap(int pos) {
        long number = tiles >> (pos << 2) & 0xf;
        return tiles ^ (number << (pos << 2)) | (number << (emptyPos << 2));
    }

    @Override
    public int heuristic() {
        int heuristic = 0;
//        if(this.tiles >> 60 == 1) {
//            for(short tiles = (short) (this.tiles >> 48); tiles != 1; tiles >>= 4) {
//                if((tiles & 0xf) == 4) {
//                    heuristic = 2;
//                    break;
//                }
//            }
//        }

        long tiles = this.tiles;

//        if((tiles & 0xf) != 15) {
//            if((tiles >> 4 & 0xf) == 14) {
//                heuristic += 2;
//            }
//            if((tiles >> 16 & 0xf) == 11) {
//                heuristic += 2;
//            }
//        }
//
//        if((tiles >> 12 & 0xf) != 12) {
//            if((tiles >> 8 & 0xf) == 13) {
//                heuristic += 2;
//            }
//            if((tiles >> 28 & 0xf) == 8) {
//                heuristic += 2;
//            }
//        }
//
//        if((tiles >> 48 & 0xf) != 3) {
//            if((tiles >> 52 & 0xf) == 14) {
//                heuristic += 2;
//            }
//            if((tiles >> 32 & 0xf) == 11) {
//                heuristic += 2;
//            }
//        }

        for(int index = 0xf; tiles != 0; index--, tiles >>= 4) {
            int tile = (int) tiles;

            int distance = Math.abs((index & 0b11) - (tile & 0b11)) + Math.abs(((index >> 2) & 0b11) - ((tile >> 2) & 0b11));
            heuristic += distance;
        }
        return heuristic;
    }

    @Override
    public List<Action> listActions() {
        List<Action> actions = new ActionList();
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
        return tiles == goalState;
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
    public State performAction(Action action) {
        int swapPos = ((TileAction) action).getSwapPos(emptyPos);
        return new FifteenPuzzle(swap(swapPos), swapPos);
    }

    @Override
    public boolean equals(Object otherState) {
        return otherState.getClass() == FifteenPuzzle.class && ((FifteenPuzzle) otherState).tiles == tiles;
    }

    public enum TileAction implements Action {
        LEFT( -0b0001),
        RIGHT(0b0001),
        DOWN(0b0100),
        UP(-0b0100);

        private final int changeSwap;

        TileAction(int changeSwap) {
            this.changeSwap = changeSwap;
        }

        public int getSwapPos(int emptyPos) {
            return emptyPos + changeSwap;
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

    private static class ActionList implements List<Action> {
        private int size = 0;
        private final Action[] actions = new Action[4];

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Action> iterator() {
            return new It();
        }

        private class It implements Iterator<Action> {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index != size;
            }

            @Override
            public Action next() {
                return actions[index++];
            }
        };

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public boolean add(Action o) {
            actions[size++] = o;
            return true;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean addAll(Collection c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Action get(int index) {
            return actions[index];
        }

        @Override
        public Action set(int index, Action element) {
            return null;
        }

        @Override
        public void add(int index, Action element) {

        }

        @Override
        public Action remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Action> listIterator() {
            return null;
        }

        @Override
        public ListIterator listIterator(int index) {
            return null;
        }

        @Override
        public List subList(int fromIndex, int toIndex) {
            return null;
        }

        @Override
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return false;
        }

        @Override
        public Action[] toArray(Object[] a) {
            return actions;
        }
    }
}