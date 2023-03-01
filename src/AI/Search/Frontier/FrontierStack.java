package AI.Search.Frontier;

import AI.Search.SearchNode;

import java.util.ArrayList;
import java.util.List;

public class FrontierStack implements Frontier {
    List<SearchNode> stack = new ArrayList<>();

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void insert(SearchNode node) {
        stack.add(node);
    }

    @Override
    public SearchNode removeNext() {
        return stack.remove(stack.size()-1);
    }
}
