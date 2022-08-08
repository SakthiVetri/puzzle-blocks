package org.example.blocks.graph;

import java.util.Set;

public class BlockNodeComparator {
    /**
     * Compares two nodes and returns whether the childNode can be a valid childNode for the given rootNode.
     */
    public boolean isValidChild(BlockNode rootNode, BlockNode childNode) {
        return rootNode.getWidth() <= childNode.getWidth() &&
               rootNode.getLength() <= childNode.getLength() &&
               rootNode.getHeight() <= childNode.getHeight();
    }

    public BlockNode findMaxHeightNode(Set<BlockNode> blockNodeSet) {
        BlockNode maxHeightNode = null;
        for (BlockNode node : blockNodeSet) {
            if (maxHeightNode == null) {
                maxHeightNode = node;
            } else if (maxHeightNode.getStackHeight() < node.getStackHeight()) {
                maxHeightNode = node;
            }
        }
        return maxHeightNode;
    }
}
