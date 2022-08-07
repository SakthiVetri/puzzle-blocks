package org.example.block.graph2;

public class BlockGraphNodeComparator {
    /**
     * Compares two nodes and returns whether the childNode can be a valid childNode for the given rootNode.
     */
    public static boolean isValidChild(BlockGraphNode rootNode, BlockGraphNode childNode) {
        return  !rootNode.getBlockId().equals(childNode.getBlockId()) &&
                rootNode.getWidth() <= childNode.getWidth() &&
                rootNode.getLength() <= childNode.getLength() &&
                rootNode.getHeight() <= childNode.getHeight();
    }
}
