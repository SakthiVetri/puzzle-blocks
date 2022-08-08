package org.example.blocks;

import org.example.blocks.graph.BlockNode;
import org.example.blocks.graph.BlockNodeFactory;
import org.example.blocks.graph.BlockNodeGraph;
import org.example.blocks.input.Block;
import org.example.blocks.input.BlocksReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class BlockPuzzleRunner {

    private final BlocksReader blocksReader;
    private final BlockNodeFactory blockNodeFactory;
    private final BlockNodeGraph blockNodeGraph;

    public BlockPuzzleRunner(BlocksReader blocksReader,
                             BlockNodeFactory blockNodeFactory,
                             BlockNodeGraph blockNodeGraph) {
        this.blocksReader = requireNonNull(blocksReader);
        this.blockNodeFactory = requireNonNull(blockNodeFactory);
        this.blockNodeGraph = requireNonNull(blockNodeGraph);
    }

    public int run() throws IOException {
        System.out.println("Please enter dimensions as one block per line. \nFor each block enter 3 integers separated by comma, example: 5,2,3  \nType exit to finish entering.");
        List<Block> blockList = blocksReader.readBlocks(new BufferedReader(new InputStreamReader(System.in)));

        for (Block block : blockList) {
            List<BlockNode> blockNodeList = blockNodeFactory.create(block);

            for (BlockNode blockNode : blockNodeList) {
                blockNodeGraph.addNode(blockNode);
            }
        }

        BlockNode maxHeightNode = blockNodeGraph.getMaxHeightChild();
        int maxHeight = maxHeightNode != null ? maxHeightNode.getStackHeight() : 0;

        System.out.println("Maximum path length is " + maxHeight);
        while (maxHeightNode != null) {
            System.out.println(maxHeightNode.toString());
            maxHeightNode = maxHeightNode.getMaxLengthChild();
        }


        return maxHeight;
    }
}
