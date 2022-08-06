package org.example.blocks;

import org.apache.commons.lang3.tuple.Pair;
import org.example.blocks.graph.StackableBlocksGraph;
import org.example.blocks.graph.StackableBlocksGraphBuilder;
import org.example.blocks.graph.StackableBlocksGraphTraverser;
import org.example.blocks.input.BlockPermutationsGenerator;
import org.example.blocks.input.BlocksReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class BlockPuzzleRunner {

    private final BlocksReader blocksReader;
    private final StackableBlocksGraphBuilder stackableBlocksGraphBuilder;
    private final StackableBlocksGraphTraverser stackableBlocksGraphTraverser;

    public BlockPuzzleRunner(BlocksReader blocksReader,
                             StackableBlocksGraphBuilder stackableBlocksGraphBuilder,
                             StackableBlocksGraphTraverser stackableBlocksGraphTraverser) {
        this.blocksReader = requireNonNull(blocksReader);
        this.stackableBlocksGraphBuilder = requireNonNull(stackableBlocksGraphBuilder);
        this.stackableBlocksGraphTraverser = requireNonNull(stackableBlocksGraphTraverser);
    }

    public void run() throws IOException {
        List<Block> blockList = blocksReader.readBlocks(System.in);

        StackableBlocksGraph stackableBlocksGraph = stackableBlocksGraphBuilder.buildGraph(blockList);
        stackableBlocksGraph.printGraph();

        Pair<Integer, List<Block>> longestPath = stackableBlocksGraphTraverser.getLongestPath(stackableBlocksGraph);
        System.out.println("Longest path length =" + longestPath.getKey() + " path: " + longestPath.getValue());
    }
}
