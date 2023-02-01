package AI.Search.Frontier;

import AI.Search.SearchNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FrontierQueue implements Frontier {
    Queue<SearchNode> queue = new LinkedList<>();

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void insert(SearchNode node) {
        queue.add(node);
    }

    @Override
    public SearchNode removeNext() {
        return queue.remove();
    }
}
