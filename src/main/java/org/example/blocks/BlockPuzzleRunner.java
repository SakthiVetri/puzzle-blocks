package org.example.blocks;

import org.apache.commons.lang3.tuple.Pair;
import org.example.blocks.graph.BlockGraphBuilder;
import org.example.blocks.graph.BlockGraphNode;
import org.example.blocks.graph.LongestPathTraverser;
import org.example.blocks.input.Block;
import org.example.blocks.input.BlocksReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class BlockPuzzleRunner {

    private final BlocksReader blocksReader;
    private final BlockGraphBuilder blockGraphBuilder;
    private final LongestPathTraverser longestPathTraverser;

    public BlockPuzzleRunner(BlocksReader blocksReader,
                             BlockGraphBuilder blockGraphBuilder,
                             LongestPathTraverser longestPathTraverser) {
        this.blocksReader = requireNonNull(blocksReader);
        this.blockGraphBuilder = requireNonNull(blockGraphBuilder);
        this.longestPathTraverser = requireNonNull(longestPathTraverser);
    }

    public int run() throws IOException {
        System.out.println("Please enter dimensions as one block per line. Please enter the values as 3 comma separated integers (1,2,3) for each block.  Type exit to end.");
        List<Block> blockList = blocksReader.readBlocks(new BufferedReader(new InputStreamReader(System.in)));

        System.out.println("Number of blocks read are " + blockList.size() + "\n" + blockList);

        Set<BlockGraphNode> rootNodes = blockGraphBuilder.buildGraph(blockList);

        // System.out.println("Number of root Nodes are " + rootNodes.size() + "\n" + rootNodes);

        Pair<Integer, List<BlockGraphNode>> longestPathPair = longestPathTraverser.getLongestPath(rootNodes);

        System.out.println("Longest path length =" + longestPathPair.getKey() + "\nPath = " + longestPathPair.getValue());

        return longestPathPair.getKey();
    }
}
