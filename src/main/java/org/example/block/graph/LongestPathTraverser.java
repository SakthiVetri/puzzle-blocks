package org.example.block.graph;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class LongestPathTraverser {

    private final BlockGraphTraverser blockGraphTraverser;

    public LongestPathTraverser(BlockGraphTraverser blockGraphTraverser) {
        this.blockGraphTraverser = requireNonNull(blockGraphTraverser);
    }

    public Pair<Integer, List<BlockGraphNode>> getLongestPath(Set<BlockGraphNode> rootNodes) {

        int maxHeight = 0;
        List<BlockGraphNode> longestPath = null;

        for (BlockGraphNode blockGraphNode : rootNodes) {
            List<List<BlockGraphNode>> pathsFromRoot = blockGraphTraverser.getAllPaths(blockGraphNode);
            for (List<BlockGraphNode> path : pathsFromRoot) {
                int newHeight = findHeight(path);
                if (newHeight > maxHeight) {
                    maxHeight = newHeight;
                    longestPath = path;
                }
            }
        }
        return Pair.of(maxHeight, longestPath);
    }

    private int findHeight(List<BlockGraphNode> blockGraphNodes) {
        int height = 0;
        for (BlockGraphNode blockGraphNode : blockGraphNodes) {
            height = height + (blockGraphNode.getSameSizeNodeCount() * blockGraphNode.getHeight());
        }
        return height;
    }
}
