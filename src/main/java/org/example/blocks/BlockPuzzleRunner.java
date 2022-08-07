package org.example.blocks;

import org.apache.commons.lang3.tuple.Pair;
import org.example.block.graph.BlockGraphBuilder;
import org.example.block.graph.BlockGraphNode;
import org.example.block.graph.BlockGraphTraverser;
import org.example.blocks.input.Block;
import org.example.blocks.input.BlocksReader;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class BlockPuzzleRunner {

    private final BlocksReader blocksReader;
    private final BlockGraphBuilder blockGraphBuilder;
    private final BlockGraphTraverser blockGraphTraverser;

    public BlockPuzzleRunner(BlocksReader blocksReader,
                             BlockGraphBuilder blockGraphBuilder,
                             BlockGraphTraverser blockGraphTraverser) {
        this.blocksReader = requireNonNull(blocksReader);
        this.blockGraphBuilder = requireNonNull(blockGraphBuilder);
        this.blockGraphTraverser = requireNonNull(blockGraphTraverser);
    }

    public void run() throws IOException {
        List<Block> blockList = blocksReader.readBlocks(System.in);

        System.out.println("Number of blocks read are " + blockList.size() + "\n" + blockList);

        Set<BlockGraphNode> rootNodes = blockGraphBuilder.buildGraph(blockList);

        System.out.println("Number of root Nodes are " + rootNodes.size() + "\n" + rootNodes);

        Pair<Integer, List<BlockGraphNode>> longestPathPair = blockGraphTraverser.getLongestPath(rootNodes);

        System.out.println("Longest path length =" + longestPathPair.getKey() + " Path" + longestPathPair.getValue());
    }
}
