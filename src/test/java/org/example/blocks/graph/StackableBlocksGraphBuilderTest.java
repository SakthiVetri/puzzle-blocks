package org.example.blocks.graph;

import com.google.common.collect.Lists;
import org.example.blocks.Block;
import org.example.blocks.input.BlockPermutationsGenerator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

public class StackableBlocksGraphBuilderTest {

    private StackableBlocksGraphBuilder stackableBlocksGraphBuilder;

    @Before
    public void init() {
        stackableBlocksGraphBuilder = new StackableBlocksGraphBuilder(new BlockPermutationsGenerator());
    }

    @Test
    public void testBuild() {
        // Given
        Block block1 = new Block("1", 45, 50, 20);
        Block block2 = new Block("2", 37, 95, 53);
        Block block3 = new Block("3", 23, 45, 12);

        // When
        StackableBlocksGraph stackableBlocksGraph = stackableBlocksGraphBuilder.buildGraph(Lists.newArrayList(block1, block2, block3));

        // Then
        stackableBlocksGraph.printGraph();
    }
}
