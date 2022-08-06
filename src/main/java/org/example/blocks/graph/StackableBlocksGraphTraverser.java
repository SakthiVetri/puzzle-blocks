package org.example.blocks.graph;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;
import org.example.blocks.Block;

import java.util.*;
import java.util.stream.Collectors;

public class StackableBlocksGraphTraverser {

    public Pair<Integer, List<Block>> getLongestPath(StackableBlocksGraph stackableBlocksGraph) {
        Set<Block> rootBlocks = stackableBlocksGraph.getRootBlocks();

        Pair<Integer, List<Block>> longestPath = null;

        for (Block rootBlock : rootBlocks) {
            List<List<Block>> allPaths = getAllPaths(stackableBlocksGraph, rootBlock);
            for (List<Block> path : allPaths) {
                System.out.println("Path from " + rootBlock + " is " + path);
            }

            for (List<Block> path : allPaths) {
                int length = findLength(path);
                if (longestPath == null || longestPath.getKey() < length) {
                    longestPath = Pair.of(length, path);
                }
            }
        }
        return  longestPath;
    }

    private int findLength(List<Block> blockList) {
        return blockList.stream().collect(Collectors.summingInt(Block::getHeight));
    }

    private List<List<Block>> getAllPaths(StackableBlocksGraph stackableBlocksGraph, Block rootBlock) {
        List<List<Block>> allPaths = new LinkedList<>();

        Deque<Pair<Block, Integer>> blockDequeWithDepth = new LinkedList<>();
        Deque<Block> currentPath = new LinkedList<>();
        Set<String> currentPathBlockIds = new HashSet<>();

        blockDequeWithDepth.push(Pair.of(rootBlock, 0));
//        currentPath.push(rootBlock);
//        currentPathBlockIds.add(rootBlock.getBlockId());

        while (!blockDequeWithDepth.isEmpty()) {
            Pair<Block, Integer> blockDepth = blockDequeWithDepth.pop();
            Block block = blockDepth.getKey();
            int depth = blockDepth.getValue();

            while (currentPath.size() != depth) {
                Block blockRemoved = currentPath.pop();
                currentPathBlockIds.remove(blockRemoved.getBlockId());
            }

            currentPath.push(block);
            currentPathBlockIds.add(block.getBlockId());

            List<Block> adjacentBlocks = stackableBlocksGraph.getAdjacentBlocks(block);
            if (adjacentBlocks.size() == 0) {
                // Terminal node check for path Length changes
                allPaths.add(Lists.newLinkedList(currentPath));
            } else {
                int newDepth = depth + 1;
                for (Block adjacentBlock : adjacentBlocks) {
                    if (! currentPathBlockIds.contains(adjacentBlock.getBlockId())) {
                        blockDequeWithDepth.push(Pair.of(adjacentBlock, newDepth));
                    }
                }
            }
        }
        return allPaths;
    }
}
