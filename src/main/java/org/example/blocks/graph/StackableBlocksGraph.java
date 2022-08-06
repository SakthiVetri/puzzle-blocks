package org.example.blocks.graph;

import org.example.blocks.Block;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

public class StackableBlocksGraph {
    private final Map<Block, List<Block>> adjacencyBlocksMap;
    private final Set<Block> rootBlocks;

    public StackableBlocksGraph() {
        this.adjacencyBlocksMap = new HashMap<>();
        rootBlocks = new HashSet<>();
    }

    public void addEdge(Block from, Block to) {
        checkArgument(adjacencyBlocksMap.containsKey(from), "Block : %s is not present", from);
        checkArgument(adjacencyBlocksMap.containsKey(to), "Block : %s is not present", to);

        List<Block> adjacentBlocksList = adjacencyBlocksMap.get(from);
        adjacentBlocksList.add(to);
        rootBlocks.remove(to);
    }

    public void addNode(Block block) {
        checkArgument(!adjacencyBlocksMap.containsKey(block), "Block : %s is already present", block);

        List<Block> adjacentBlocksList = new ArrayList<>();
        adjacencyBlocksMap.put(block, adjacentBlocksList);

        // Since it is new block, add in the root blocks set as well.
        rootBlocks.add(block);
    }

    public Set<Block> getRootBlocks() {
        return Collections.unmodifiableSet(rootBlocks);
    }

    public List<Block> getAdjacentBlocks(Block from) {
        return Collections.unmodifiableList(adjacencyBlocksMap.get(from));
    }

    public void printGraph() {
        System.out.println("RootBlocks " + rootBlocks);
        for (Map.Entry<Block, List<Block>> adjacentBlocks : adjacencyBlocksMap.entrySet()) {
            System.out.println("AdjacentBlocks of " + adjacentBlocks.getKey() + " is " + adjacentBlocks.getValue());
        }
    }
}
