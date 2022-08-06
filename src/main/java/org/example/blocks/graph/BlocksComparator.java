package org.example.blocks.graph;

import org.example.blocks.Block;

public class BlocksComparator {

    public static boolean isStackable(StackableBlocksGraphBuilder.BlockBase top, StackableBlocksGraphBuilder.BlockBase botttom) {
        return top.getWidth() <= botttom.getWidth() && top.getLength() <= botttom.getLength();
    }

    public static boolean isStackable(Block top, Block botttom) {
        return top.getWidth() <= botttom.getWidth() && top.getLength() <= botttom.getLength() && top.getHeight() <= botttom.getHeight();
    }
}
