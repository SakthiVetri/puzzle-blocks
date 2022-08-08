package org.example.blocks.graph;

import org.example.blocks.input.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates BlockGraphNodes for the given block with different permutations of how it can be stacked.
 */
public class BlockNodeFactory {

    public List<BlockNode> create(Block block) {
        int[] dimension = new int[] {block.getWidth(), block.getLength(), block.getHeight()};
        Arrays.sort(dimension);

        List<BlockNode> permutationsList = new ArrayList<>();
        permutationsList.add(new BlockNode(block.getBlockId(), dimension[0], dimension[1], dimension[2]));
        if (dimension[0] == dimension[1] && dimension[0] == dimension[2]) {
        } else if (dimension[0] == dimension[1]) {
            permutationsList.add(new BlockNode(block.getBlockId(), dimension[0], dimension[2], dimension[1]));
        } else if (dimension[1] == dimension[2]) {
            permutationsList.add(new BlockNode(block.getBlockId(), dimension[1], dimension[2], dimension[0]));
        } else {
            permutationsList.add(new BlockNode(block.getBlockId(), dimension[0], dimension[2], dimension[1]));
            permutationsList.add(new BlockNode(block.getBlockId(), dimension[1], dimension[2], dimension[0]));
        }
        return  permutationsList;
    }
}
