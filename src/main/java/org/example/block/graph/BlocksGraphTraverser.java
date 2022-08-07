package org.example.block.graph;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class BlocksGraphTraverser {

    public Pair<Integer, List<BlockGraphNode>> getLongestPath(Set<BlockGraphNode> rootNodes) {

        int maxHeight = 0;
        List<BlockGraphNode> longestPath = null;

        for (BlockGraphNode blockGraphNode : rootNodes) {
            List<List<BlockGraphNode>> pathsFromRoot = getAllPaths(blockGraphNode);
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


    private List<List<BlockGraphNode>> getAllPaths(BlockGraphNode rootNode) {
        List<List<BlockGraphNode>> allPaths = new LinkedList<>();

        Deque<Pair<BlockGraphNode, Integer>> blockNodesDequeWithDepth = new LinkedList<>();
        Deque<BlockGraphNode> currentPath = new LinkedList<>();
        Set<String> currentPathBlockIds = new HashSet<>();

        blockNodesDequeWithDepth.push(Pair.of(rootNode, 0));

        while (!blockNodesDequeWithDepth.isEmpty()) {
            Pair<BlockGraphNode, Integer> blockGraphNodeDepthPair = blockNodesDequeWithDepth.pop();
            BlockGraphNode blockGraphNode = blockGraphNodeDepthPair.getKey();
            int depth = blockGraphNodeDepthPair.getValue();

            while (currentPath.size() != depth) {
                BlockGraphNode blockRemoved = currentPath.pop();
                currentPathBlockIds.remove(blockRemoved.getBlockId());
            }

            currentPath.push(blockGraphNode);
            currentPathBlockIds.add(blockGraphNode.getBlockId());

            List<BlockGraphNode> childNodes = blockGraphNode.getChildNodes();
            if (childNodes.size() == 0) {
                // Terminal node, add current path to list of paths.
                allPaths.add(Lists.newLinkedList(currentPath));
            } else {
                int newDepth = depth + 1;
                for (BlockGraphNode childNode : childNodes) {
                    if (! currentPathBlockIds.contains(childNode.getBlockId())) {
                        blockNodesDequeWithDepth.push(Pair.of(childNode, newDepth));
                    }
                }
            }
        }
        return allPaths;
    }
}
