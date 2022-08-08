package org.example.blocks.input;

import static java.util.Objects.requireNonNull;

public class Block {
    private String blockId;
    private int width;
    private int length;
    private int height;

    public Block(String blockId, int width, int length, int height) {
        this.blockId = requireNonNull(blockId);
        this.width = width;
        this.length = length;
        this.height = height;
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

    public String getBlockId() {
        return blockId;
    }

    public String toString() {
        return "Block_" + blockId + "[" + width + "," + length + ", " + height + "]";
    }

    @Override
    public int hashCode() {
        return blockId.hashCode() << 32 + width << 16 + height << 8 + length;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other instanceof  Block) {
            Block otherBlock = (Block) other;
            return blockId.equals(otherBlock.getBlockId()) &&
                    width == otherBlock.getWidth() &&
                    length == otherBlock.length &&
                    height == otherBlock.getHeight();
        } else {
            return false;
        }
    }
}
