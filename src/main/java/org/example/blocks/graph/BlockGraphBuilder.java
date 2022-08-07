package org.example.blocks.graph;

import org.example.blocks.input.Block;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class BlockGraphBuilder {

    private final BlockGraphNodeFactory blockGraphNodeFactory;

    public BlockGraphBuilder(BlockGraphNodeFactory blockGraphNodeFactory) {
        this.blockGraphNodeFactory = requireNonNull(blockGraphNodeFactory);
    }

    public Set<BlockGraphNode> buildGraph(List<Block> blockList) {

        // Create unique set of BlockGraph nodes.
        Map<String, BlockGraphNode> blockGraphNodesByDimension = new HashMap<>();
        for (Block block : blockList) {
            List<BlockGraphNode> blockGraphNodes = blockGraphNodeFactory.create(block);
            for (BlockGraphNode blockGraphNode : blockGraphNodes) {
                String dimension = blockGraphNode.getDimension();
                BlockGraphNode existingNode = blockGraphNodesByDimension.get(dimension);
                if (existingNode != null) {
                    existingNode.addSameSizeNode(blockGraphNode);
                } else {
                    blockGraphNodesByDimension.put(dimension, blockGraphNode);
                }
            }
        }

        // System.out.println("All possible blocks are " + blockGraphNodesByDimension.values());

        BlockGraphNode[] uniqueNodes = blockGraphNodesByDimension.values().toArray(new BlockGraphNode[] {});
        for (int i = 0; i < uniqueNodes.length; i++) {
            for (int j = 0; j < uniqueNodes.length; j++) {
                if (i != j) {
                    BlockGraphNode parentNode = uniqueNodes[i];
                    BlockGraphNode childNode = uniqueNodes[j];
                    if (BlockGraphNodeComparator.isValidChild(parentNode, childNode)) {
                        parentNode.addChildNode(childNode);
                    }
                }
            }
        }

        Set<BlockGraphNode> allNodes = Arrays.stream(uniqueNodes).collect(Collectors.toSet());
        Set<BlockGraphNode> childNodes = new HashSet<>();
        for (int i = 0; i < uniqueNodes.length; i++) {
            childNodes.addAll(uniqueNodes[i].getChildNodes());
        }
        allNodes.removeAll(childNodes);
        return allNodes;
    }
}
