package org.example.block.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class BlockGraphNode {
    private final String blockId;
    private final int width;
    private final int length;

    private final int height;

    private List<BlockGraphNode> childNodes;
    private List<String> sameSizeNodes;

    public BlockGraphNode(String blockId, int width, int length, int height) {
        this.blockId = blockId;
        this.width = width;
        this.length = length;

        checkArgument(width <= length);
        this.height = height;
        this.childNodes = new ArrayList<>();

        this.sameSizeNodes = new ArrayList<>();
        sameSizeNodes.add(blockId);
    }

    public void addChildNode(BlockGraphNode nextNode) {
        checkArgument(this.height <= nextNode.height && this.length <= nextNode.length && this.width <= nextNode.width);
        childNodes.add(nextNode);
    }

    public void addSameSizeNode(BlockGraphNode nextNode) {
        checkArgument(this.height == nextNode.height && this.length == nextNode.length && this.width == nextNode.width);
        sameSizeNodes.add(nextNode.getBlockId());
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

    public List<BlockGraphNode> getChildNodes() {
        return Collections.unmodifiableList(childNodes);
    }

    public int getSameSizeNodeCount() {
        return sameSizeNodes.size();
    }

    public String getDimension() {
        return width + "_" + length + "_" + height;
    }

    @Override
    public String toString() {
        return "Block_" + sameSizeNodes.size() + "_[" + width + "," + length + "," + height +"]";
    }
}
