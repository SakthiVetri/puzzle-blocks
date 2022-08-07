package org.example.block.graph2;

import org.example.blocks.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockGraphNodeFactory {

    public List<BlockGraphNode> create(Block block) {
        List<BlockGraphNode> permutationsList = new ArrayList<>();

        permutationsList.add(new BlockGraphNode(block.getBlockId(), block.getWidth(), block.getLength(), block.getHeight()));

        if (block.getWidth() == block.getLength() && block.getWidth() == block.getHeight()) {
            return permutationsList;
        } else if (block.getWidth() == block.getLength()) {
            int width = block.getHeight();
            int length = block.getLength();
            int height = block.getWidth();
            if (width > length) {
                permutationsList.add(new BlockGraphNode(block.getBlockId(), length, width, height));
            } else {
                permutationsList.add(new BlockGraphNode(block.getBlockId(), width, length, height));
            }
        } else {
            if (block.getWidth() != block.getHeight()) {
                int width = block.getHeight();
                int height = block.getWidth();
                int length = block.getLength();
                if (width > length) {
                    permutationsList.add(new BlockGraphNode(block.getBlockId(), length, width, height));
                } else {
                    permutationsList.add(new BlockGraphNode(block.getBlockId(), width, length, height));
                }
            }

            if (block.getLength() != block.getHeight()) {
                int width = block.getWidth();
                int length = block.getHeight();
                int height = block.getLength();

                if (width > length) {
                    permutationsList.add(new BlockGraphNode(block.getBlockId(), length, width, height));
                } else {
                    permutationsList.add(new BlockGraphNode(block.getBlockId(), width, length, height));
                }
            }
        }
        return permutationsList;
    }
}
