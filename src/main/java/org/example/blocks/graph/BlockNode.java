package org.example.blocks.graph;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

public class BlockNode {
    private final String blockId;
    private final int width;
    private final int length;
    private final int height;

    private Set<BlockNode> childNodes;

    private int maxChildStackHeight;
    private BlockNode maxLengthChild;

    public BlockNode(String blockId, int width, int length, int height) {
        this.blockId = blockId;
        this.width = width;
        this.length = length;
        checkArgument(width <= length);
        this.height = height;

        this.childNodes = new HashSet<>();
    }

    public String getBlockId() {
        return blockId;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public void addChildNode(BlockNode blockNode) {
        childNodes.add(blockNode);
    }

    public void removeChildNode(BlockNode blockNode) {
        childNodes.remove(blockNode);
    }

    public Set<BlockNode> getChildNodes() {
        return Set.copyOf(childNodes);
    }

    public int getStackHeight() {
        return maxChildStackHeight + height;
    }
    public int getMaxChildStackHeight() {
        return maxChildStackHeight;
    }

    public BlockNode getMaxLengthChild() {
        return maxLengthChild;
    }

    public void updateMaxChildNode(BlockNode blockNode) {
        checkArgument(childNodes.contains(blockNode));
        this.maxChildStackHeight = blockNode.getStackHeight();
        this.maxLengthChild = blockNode;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BlockNode) {
            BlockNode otherNode = (BlockNode) other;
            return this.height == otherNode.height &&
                    this.length == otherNode.length &&
                    this.width == otherNode.width &&
                    this.blockId.equals(otherNode.blockId) &&
                    this.childNodes.equals(otherNode.childNodes);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return blockId.hashCode() << 24 + width << 16 + length << 8 + height;
    }

    @Override
    public String toString() {
        return blockId + "[" + width + "," + length + "," + height + "]";
    }
}
