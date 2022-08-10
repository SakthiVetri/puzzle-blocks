package org.example.blocks;

import org.example.blocks.graph.BlockNode;
import org.example.blocks.graph.BlockNodeFactory;
import org.example.blocks.graph.BlockNodeGraphHelper;
import org.example.blocks.input.Block;
import org.example.blocks.input.BlocksReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class BlockPuzzleRunner {

    private final BlocksReader blocksReader;
    private final BlockNodeFactory blockNodeFactory;
    private final BlockNodeGraphHelper blockNodeGraphHelper;

    public BlockPuzzleRunner(BlocksReader blocksReader,
                             BlockNodeFactory blockNodeFactory,
                             BlockNodeGraphHelper blockNodeGraphHelper) {
        this.blocksReader = requireNonNull(blocksReader);
        this.blockNodeFactory = requireNonNull(blockNodeFactory);
        this.blockNodeGraphHelper = requireNonNull(blockNodeGraphHelper);
    }

    public int run() throws IOException {
        System.out.println("Please enter dimensions as one block per line. \nFor each block enter 3 integers separated by comma, example: 5,2,3  \nType exit to finish entering.");
        List<Block> blockList = blocksReader.readBlocks(new BufferedReader(new InputStreamReader(System.in)));

        BlockNode root = new BlockNode("Root", 0, 0, 0);
        for (Block block : blockList) {
            List<BlockNode> blockNodeList = blockNodeFactory.create(block);

            for (BlockNode blockNode : blockNodeList) {
                blockNodeGraphHelper.addAsChildNode(root, blockNode);
            }
        }

        System.out.println("Maximum path length is " + root.getStackHeight());

        BlockNode blockNode = root;
        while (blockNode != null) {
            System.out.println(blockNode.toString());
            blockNode = blockNode.getMaxLengthChild();
        }

        return root.getStackHeight();
    }
}
