package AI.Search.Frontier;

import AI.Search.SearchNode;

import java.util.ArrayList;
import java.util.List;

// can add a special visited states list
public class FrontierPriorityQueue implements Frontier {
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
        int eval = node.eval();
        for(int size = queue.size(), index = 0; index != size; index++) {
            if(queue.get(index).eval() >= eval) {
                queue.add(index, node);
                return;
            }
        }
        queue.add(0, node);
    }

    @Override
    public SearchNode removeNext() {
        return queue.remove(0);
    }
}
