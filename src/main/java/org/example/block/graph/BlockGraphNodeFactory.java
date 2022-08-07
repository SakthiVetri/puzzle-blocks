package org.example.block.graph;

import org.example.blocks.input.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockGraphNodeFactory {

    public List<BlockGraphNode> create(Block block) {
        int[] dimension = new int[] {block.getWidth(), block.getLength(), block.getHeight()};
        Arrays.sort(dimension);

        List<BlockGraphNode> permutationsList = new ArrayList<>();
        permutationsList.add(new BlockGraphNode(block.getBlockId(), dimension[0], dimension[1], dimension[2]));
        if (dimension[0] == dimension[1] && dimension[0] == dimension[2]) {
        } else if (dimension[0] == dimension[1]) {
            permutationsList.add(new BlockGraphNode(block.getBlockId(), dimension[0], dimension[2], dimension[1]));
        } else if (dimension[1] == dimension[2]) {
            permutationsList.add(new BlockGraphNode(block.getBlockId(), dimension[1], dimension[2], dimension[0]));
        } else {
            permutationsList.add(new BlockGraphNode(block.getBlockId(), dimension[0], dimension[2], dimension[1]));
            permutationsList.add(new BlockGraphNode(block.getBlockId(), dimension[1], dimension[2], dimension[0]));
        }
        return  permutationsList;
    }
}
