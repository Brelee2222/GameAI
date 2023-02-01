package AI.Search.Frontier;

import AI.Search.SearchNode;

import java.util.ArrayList;
import java.util.List;

public class FrontierStack implements Frontier {
    List<SearchNode> queue = new ArrayList<>();

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
        return queue.remove(queue.size()-1);
    }
}
