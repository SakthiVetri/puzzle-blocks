package org.example.blocks.graph3;

public class BlockNodeComparator {
    /**
     * Compares two nodes and returns whether the childNode can be a valid childNode for the given rootNode.
     */
    public boolean isValidChild(BlockNode rootNode, BlockNode childNode) {
        return rootNode.getWidth() <= childNode.getWidth() &&
               rootNode.getLength() <= childNode.getLength() &&
               rootNode.getHeight() <= childNode.getHeight();
    }
}
