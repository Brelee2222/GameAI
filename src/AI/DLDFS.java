package AI;

import AI.Search.Frontier.FrontierQueue;
import AI.Search.SearchNode;
import AI.Search.TreeSearch;

public class DLDFS extends TreeSearch {
    private int depthLimit;

    public void setDepthLimit(int depthLimit) {
        this.depthLimit = depthLimit;
    }

    public int getDepthLimit() {
        return depthLimit;
    }

    public DLDFS(int depthLimit) {
        super(new FrontierQueue());
        this.depthLimit = depthLimit;
    }

    @Override
    public String toString() {
        return "Depth Limited Depth First Search";
    }

    @Override
    public boolean pruneNode(SearchNode node) {
        return node.getDepth() > depthLimit;
    }
}
